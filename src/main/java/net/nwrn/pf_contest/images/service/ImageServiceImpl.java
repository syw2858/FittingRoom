package net.nwrn.pf_contest.images.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.clothes.entity.BottomEntity;
import net.nwrn.pf_contest.clothes.entity.TopEntity;
import net.nwrn.pf_contest.clothes.repository.BottomRepository;
import net.nwrn.pf_contest.clothes.repository.TopRepository;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import net.nwrn.pf_contest.images.entity.ImageEntity;
import net.nwrn.pf_contest.images.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import static net.nwrn.pf_contest.clothes.entity.QBottomEntity.bottomEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ExceptionService exceptionService;
    private final ImageRepository imageRepository;
    private final TopRepository topRepository;
    private final BottomRepository bottomRepository;

    private final AmazonS3 amazonS3;

    @Value("${properties.awsBucketName}")
    private String awsBucketName;

    @Value("${properties.cloudfrontDomain}")
    private String cloudfrontDomain;

    private String generateUrl(String path, String filename) {
        return new StringBuilder()
                .append(cloudfrontDomain).append("/").append(path).append(filename).toString();
    }

    public String combineUrl(String repoName, Long objectId, String filename) {
        String path = new StringBuilder().append(repoName).append("/").append(objectId).append("/").toString();
        return generateUrl(path, filename);
    }


    public String uploadTopImageToS3AndGetUrl(MultipartFile Image) {

        String filename = Base64.getEncoder().encodeToString(Image.getOriginalFilename().getBytes());

        TopEntity topEntity = new TopEntity();
        topRepository.save(topEntity);

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(filename);
        imageEntity.setRepoName("top");
        imageRepository.save(imageEntity);
        Long objectId = topEntity.getTopId();
        imageEntity.setObjectId(objectId);

        String path = new StringBuilder().append("top").append("/").append(objectId).append("/").toString();

        byte[] bytes;
        try {
            bytes = Image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);

        try {
            amazonS3.putObject(new PutObjectRequest(awsBucketName + path, filename, byteArrayInputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException("amazonS3에 putObject하는데 오류 발생");
        }

        return generateUrl(path, filename);
    }

    public String uploadBottomImageToS3AndGetUrl(MultipartFile Image) {

        String filename = Base64.getEncoder().encodeToString(Image.getOriginalFilename().getBytes());

        BottomEntity bottomEntity = new BottomEntity();
        bottomRepository.save(bottomEntity);

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(filename);
        imageEntity.setRepoName("bottom");
        imageRepository.save(imageEntity);
        Long objectId = bottomEntity.getBottomId();
        imageEntity.setObjectId(objectId);

        String path = new StringBuilder().append("top").append("/").append(objectId).append("/").toString();

        byte[] bytes;
        try {
            bytes = Image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);

        try {
            amazonS3.putObject(new PutObjectRequest(awsBucketName + path, filename, byteArrayInputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException("amazonS3에 putObject하는데 오류 발생");
        }

        return generateUrl(path, filename);
    }



//
//    public String finishMakingTopEntityAndReturnUrl(MultipartFile file, String repoName) {
//        Map<Long, String> urlMap = this.uploadTopImageToS3AndGetUrlMap(file, repoName);
//        if (urlMap.isEmpty()) {
//            throw new ApiException("TopEntity에서 Id를 가져와 object Id에 넣지 못해, url을 생성하지 못했습니다.");
//        } else {
//            Iterator<Map.Entry<Long, String>> iterator = urlMap.entrySet().iterator();
//            Map.Entry<Long, String> entry = iterator.next();
//            Long objectId = entry.getKey();
//            String urlToSave = entry.getValue();
//            try {
//                topRepository.findById(objectId).ifPresent(topEntity -> {
//                    topEntity.setTopUrl(urlToSave);
//                });
//            } catch (Exception e) {
//                log.error(exceptionService.generateMessage(), e);
//                throw new ApiException("topRepository에서 Id로 엔터티를 불러와 url을 저장하는데 실패하였습니다.");
//            }
//            return urlToSave;
//        }
//    }

    public String uploadPersonImageToS3AndGetUrl(MultipartFile Image, String repoName) {

        String filename = Base64.getEncoder().encodeToString(Image.getOriginalFilename().getBytes());

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(filename);
        imageEntity.setRepoName("person");
        imageRepository.save(imageEntity);
        Long objectId = imageEntity.getId();
        imageEntity.setObjectId(objectId);
        String path = new StringBuilder().append(repoName).append("/").append(objectId).append("/").toString();

        byte[] bytes;
        try {
            bytes = Image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);

        try {
            amazonS3.putObject(new PutObjectRequest(awsBucketName, path+filename, byteArrayInputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException("amazonS3에 putObject하는데 오류 발생");
        }

        return generateUrl(path, filename);

    }
}
