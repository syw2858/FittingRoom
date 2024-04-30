package net.nwrn.pf_contest.origin_images.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
    @Value("${properties.awsBucketName}")
    private String awsBucketName;
    @Value("${properties.cloudfrontDomain}")
    private String cloudfrontDomain;
    @Value("${properties.defaultImageUrl}")
    private String defaultImageUrl;

    private final AmazonS3 amazonS3;
    private final ExceptionService exceptionService;
    private final ObjectMapper objectMapper;

    private String generateUrl(String path, String filename) {
        return new StringBuilder()
                .append(cloudfrontDomain).append(path).append("/").append(filename).toString();
    }

    @Override
    public String upload(MultipartFile image, ImageCategory category, Long sn) {
        String path = new StringBuilder()
                .append("/").append(category.getName()).append("/").append(sn).toString();

        byte[] bytes;
        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        String filename = UrlService.encode(image.getOriginalFilename());
        String doubleEncode = UrlService.encode(filename);
        try {
            amazonS3.putObject(new PutObjectRequest(awsBucketName + path, filename, byteArrayInputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "amazonS3에 putObject하는데 오류 발생");
        }
        return generateUrl(path, doubleEncode);
    }

    @Override
    public List<String> uploadList(List<MultipartFile> images, ImageCategory category, Long sn, boolean def, String defUrl) {
        List<String> result = new ArrayList<>();
        for (MultipartFile image : images) {
            result.add(upload(image, category, sn));
        }
        if (def) {
            if (result.isEmpty()) {
                if (defUrl == null)
                    result.add(defaultImageUrl);
                else
                    result.add(defUrl);
            }
        }
        return result;
    }

    private String[] splitUrl(String imageUrl) {
        try {
            int idx1 = imageUrl.indexOf("/", 8);
            int idx2 = imageUrl.indexOf("/", idx1 + 1);
            int idx3 = imageUrl.lastIndexOf("/");

            return new String[] {imageUrl.substring(idx2, idx3), imageUrl.substring(idx3 + 1)};
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "imageUrl이 잘못되었습니다: " + imageUrl);
        }
    }

    @Override
    public void delete(String imageUrl) {
        if (imageUrl.equals(defaultImageUrl))
            return;
        String[] pathAndFilename = splitUrl(imageUrl);

        try {
            amazonS3.deleteObject(new DeleteObjectRequest(awsBucketName + pathAndFilename[0], UrlService.decode(pathAndFilename[1])));
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "amazonS3에 deleteObject하는데 오류 발생");
        }
    }

    @Override
    public void deleteAll(List<String> imageUrlList) {
        for (String imageUrl : imageUrlList) {
            delete(imageUrl);
        }
    }

    @Override
    public List<String> jsonToImageUrlList(String json) {
        if (json == null)
            return List.of(defaultImageUrl);

        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            throw new ApiException("해당 content의 imageUrlList가 손상되었습니다");
        }
    }
}
