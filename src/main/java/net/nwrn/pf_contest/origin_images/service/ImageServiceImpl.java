package net.nwrn.pf_contest.origin_images.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.dto.req.ClothesRequestImageDTO;
import net.nwrn.pf_contest.origin_images.entity.ClothesImageEntity;
import net.nwrn.pf_contest.origin_images.repository.ClothesImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;


@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ClothesImageRepository clothesImageRepository;
    private final ExceptionService exceptionService;
    private AmazonS3 amazonS3;
    private String awsBucketName;
    private String cloudfrontDomain;

    private String generateUrl(String path, String filename) {
        return new StringBuilder()
                .append(cloudfrontDomain).append(path).append("/").append(filename).toString();
    }

    public String uploadImageToS3AndGetUrl(MultipartFile Image, String repoName, Long ImageSn) {

        String path = new StringBuilder().append("/").append(repoName).append("/").append(ImageSn).toString();

        byte[] bytes;
        try {
            bytes = Image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        String filename = URLEncoder.encode(Image.getOriginalFilename());
        String doubleEncode = URLEncoder.encode(filename);
        try {
            amazonS3.putObject(new PutObjectRequest(awsBucketName + path, filename, byteArrayInputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException("amazonS3에 putObject하는데 오류 발생");
        }

        return generateUrl(path, doubleEncode);

    }

    @Override
    public Long newClothesImageAndGetId(Category category, Color color) {

        // ClothesRequestImageDTO를 사용하여 요청 정보를 담은 객체를 생성
        ClothesRequestImageDTO clothesRequestImageDTO = new ClothesRequestImageDTO();
        clothesRequestImageDTO.setClothesCategory(category);
        clothesRequestImageDTO.setClothesColor(color);

        // ClothesImageEntity를 생성하여 엔터티를 저장하거나 반환하는 등의 작업을 수행
        ClothesImageEntity clothesImageEntity = new ClothesImageEntity(
                clothesRequestImageDTO.getClothesCategory(),
                clothesRequestImageDTO.getClothesColor()
        );

        // save 후 반환되는 엔터티에서 id 값 추출
        Long id = clothesImageRepository.save(clothesImageEntity).getClothesImageSn();
        System.out.println(id);
        return id;
    }

    public String uploadNewClothesImage(MultipartFile Image, Category category, Color color) {
        Long id = newClothesImageAndGetId(category, color);
        String url = uploadImageToS3AndGetUrl(Image, "clothes", id);
        return url;
    }

    public String uploadNewPersonImage(MultipartFile Image) {
        Long id = 1L;
        String url = uploadImageToS3AndGetUrl(Image, "person", id);
        return url;
    }

}
