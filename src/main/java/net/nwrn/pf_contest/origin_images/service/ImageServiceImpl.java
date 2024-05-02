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
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

    // 클라우드에 있는 파일의 경로 추출하기
    private String generateUrl(String path, String filename) {
        return new StringBuilder()
                .append(cloudfrontDomain).append(path).append("/").append(filename).toString();
    }

    // 클라우드에 멀티파트 이미지를 올리면서 인코딩된 경로 추출하기
    @Override
    public String upload(MultipartFile image, String repo_name, Long sn) {

        String path = new StringBuilder()
                .append("/").append(repo_name).append("/").append(sn).toString();

        byte[] bytes;
        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);

        String filename = URLEncoder.encode(image.getOriginalFilename());
        String doubleEncode = URLEncoder.encode(filename);

        try {
            amazonS3.putObject(new PutObjectRequest(awsBucketName + path, filename, byteArrayInputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException("amazonS3에 putObject하는데 오류 발생");
        }
        return generateUrl(path, doubleEncode);
    }

    // 클라우드에 멀티파트 이미지 묶음리스트를 올리면서 각각에 대한 경로를 리스트로 받아오기
    @Override
    public List<String> uploadList(List<MultipartFile> images, String repo_name, Long sn, boolean def, String defUrl) {

        List<String> result = new ArrayList<>();
        for (MultipartFile image : images) {
            result.add(upload(image, repo_name, sn));
        }

        // 결과를 반환받을 리스트가 비어있으면 기본 이미지 url 추가해주기
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

    // cloudfrontdomain, path, repo_name 부분과 순수 이미지 Url 분리 추출
    public String[] splitUrl(String imageUrl) {
        try {
            int idx1 = imageUrl.indexOf("/", 8);
            int idx2 = imageUrl.indexOf("/", idx1 + 1);
            int idx3 = imageUrl.lastIndexOf("/");

            return new String[] {imageUrl.substring(idx2, idx3), imageUrl.substring(idx3 + 1)};
        } catch (Exception e) {
            throw new ApiException("imageUrl이 잘못되었습니다: " + imageUrl);
        }
    }

    // 클라우드에서 이미지 URL로 검색하여 해당 멀티파트 이미지 삭제하기
    @Override
    public void delete(String imageUrl) {
        if (imageUrl.equals(defaultImageUrl))
            return;
        String[] pathAndFilename = splitUrl(imageUrl);

        try {
            amazonS3.deleteObject(new DeleteObjectRequest(awsBucketName + pathAndFilename[0], URLDecoder.decode(pathAndFilename[1]))); // 인코드 두번 했는데 디코드 한번만 해도 되나?
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException("amazonS3에 deleteObject하는데 오류 발생");
        }
    }

    // 삭제할 이미지URL 리스트로 가져와서 클라우드에서 삭제하기
    @Override
    public void deleteAll(List<String> imageUrlList) {
        for (String imageUrl : imageUrlList) {
            delete(imageUrl);
        }
    }

    // json 문자열로 가져온 정보에서 ImageUrlList 추출
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
