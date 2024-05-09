package net.nwrn.pf_contest.compose.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.clothes.entity.BottomEntity;
import net.nwrn.pf_contest.clothes.entity.TopEntity;
import net.nwrn.pf_contest.clothes.repository.BottomRepository;
import net.nwrn.pf_contest.clothes.repository.TopRepository;
import net.nwrn.pf_contest.compose.dto.res.ComposeBottomResponseDTO;
import net.nwrn.pf_contest.compose.dto.res.ComposeTopResponseDTO;
import net.nwrn.pf_contest.images.entity.ImageEntity;
import net.nwrn.pf_contest.images.repository.ImageRepository;
import net.nwrn.pf_contest.images.service.ImageService;
import net.nwrn.pf_contest.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    private final TopRepository topRepository;
    private final BottomRepository bottomRepository;
    private final PersonRepository personRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @Value("${properties.awsBucketName}")
    private String awsBucketName;

    @Value("${properties.cloudfrontDomain}")
    private String cloudfrontDomain;

    @Value("${properties.defaultImageUrl}")
    private String defaultImageUrl;

    @Override
    public String uploadPerson(MultipartFile personImage) {

        String personImageUrl = imageService.uploadPersonImageToS3AndGetUrl(personImage, "person");

//        System.out.println(personImage.getSize());
//        return "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg";

        return personImageUrl;
    }

    @Override
    public String uploadTop(MultipartFile topImage) {
        String topImageUrl = imageService.uploadTopImageToS3AndGetUrl(topImage);

        return topImageUrl;
    }


    @Override
    public String uploadBottom(MultipartFile bottomImage) {
        String bottomImageUrl = imageService.uploadBottomImageToS3AndGetUrl(bottomImage);

        return bottomImageUrl;
    }



    public List<ComposeTopResponseDTO> getTopList() {
        List<TopEntity> topEntityList = topRepository.findAll();
        List<ComposeTopResponseDTO> topContentList = new ArrayList<>();

        for (TopEntity topEntity : topEntityList) {
            Long topId = topEntity.getTopId();
            List<ImageEntity> imageEntityList = imageRepository.findByRepoNameAndObjectId("top", topId);
            String topUrl;
            Timestamp topRegisterDt = topEntity.getTopRegisterDt();

            if (imageEntityList.isEmpty()) {
                topUrl = defaultImageUrl;
            }
            else {
                ImageEntity imageEntity = imageEntityList.get(0);
                String repoName = imageEntity.getRepoName();
                Long objectId = imageEntity.getObjectId();
                String fileName = imageEntity.getFileName();
                topUrl = imageService.combineUrl(repoName, objectId, fileName);
            }

            ComposeTopResponseDTO topResponseDTO = new ComposeTopResponseDTO();
            topResponseDTO.setTopId(topId);
            topResponseDTO.setTopImageUrl(topUrl);
            topResponseDTO.setTopRegisterDt(topRegisterDt);

            topContentList.add(topResponseDTO);
        }

        return topContentList;
    }

    public List<ComposeBottomResponseDTO> getBottomList() {
        List<BottomEntity> bottomEntityList = bottomRepository.findAll();
        List<ComposeBottomResponseDTO> bottomContentList = new ArrayList<>();

        for (BottomEntity bottomEntity : bottomEntityList) {
            Long bottomId = bottomEntity.getBottomId();
            List<ImageEntity> imageEntityList = imageRepository.findByRepoNameAndObjectId("bottom", bottomId);
            String bottomUrl;
            Timestamp bottomRegisterDt = bottomEntity.getBottomRegisterDt();

            if (imageEntityList.isEmpty()) {
                bottomUrl = defaultImageUrl;
            }
            else {
                ImageEntity imageEntity = imageEntityList.get(0);
                String repoName = imageEntity.getRepoName();
                Long objectId = imageEntity.getObjectId();
                String fileName = imageEntity.getFileName();
                bottomUrl = imageService.combineUrl(repoName, objectId, fileName);
            }

            ComposeBottomResponseDTO bottomResponseDTO = new ComposeBottomResponseDTO();
            bottomResponseDTO.setBottomId(bottomId);
            bottomResponseDTO.setBottomImageUrl(bottomUrl);
            bottomResponseDTO.setBottomRegisterDt(bottomRegisterDt);

            bottomContentList.add(bottomResponseDTO);
        }

        return bottomContentList;
    }



//    @Override
//    public Page<ComposePageClothesResponseDTO> getClothesList(Category category, Color color, Integer page, Integer size) {
//
//
//        // 페이지네이션으로 엔터티 가져오기
//        Page<ClothesEntity> clothesEntityPage = clothesRepository.getClothes(category, color, page, size);
//
////        Page<ComposePageClothesResponseDTO> pageClothesResponseDTOs = new PageImpl<>(
////                new ArrayList<>(), clothesEntityPage.getPageable(), clothesEntityPage.getTotalElements()
////        );
//
//        List<ComposePageClothesResponseDTO> content = new ArrayList<>();
//
//
//        for (ClothesEntity clothesEntity : clothesEntityPage) {
//
//            String url;
//            Long clothesId = clothesEntity.getId();
//            List<ImageEntity> imageEntityList = imageRepository.findByRepoNameAndObjectId("clothes", clothesId);
//            if (imageEntityList.isEmpty()) url = defaultImageUrl;
//
//            else if (imageEntityList.size() >=2 ) throw new ApiException("데이터베이스 오류입니다.");
//
//            else {
//                ImageEntity imageEntity = imageEntityList.get(0);
//                String repoName = imageEntity.getRepoName();
//                Long objectId = imageEntity.getObjectId();
//                String fileName = imageEntity.getFileName();
//                url = imageService.combineUrl(repoName, objectId, fileName);
//            }
//
//            ComposePageClothesResponseDTO clothesResponseDTO = new ComposePageClothesResponseDTO();
//            clothesResponseDTO.setClothesColor(clothesEntity.getColor());
//            clothesResponseDTO.setClothesCategory(clothesEntity.getCategory());
//            clothesResponseDTO.setClothesId(clothesId);
//            clothesResponseDTO.setClothesImageUrl(url);
//
//            content.add(clothesResponseDTO);
//        }
//
//        return new PageImpl<>(content, clothesEntityPage.getPageable(), clothesEntityPage.getTotalElements()) ;
//
//    }
}
