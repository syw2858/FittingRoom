package net.nwrn.pf_contest.compose.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.clothes.entity.ClothesEntity;
import net.nwrn.pf_contest.clothes.repository.ClothesRepository;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.images.entity.ImageEntity;
import net.nwrn.pf_contest.images.repository.ImageRepository;
import net.nwrn.pf_contest.images.service.ImageService;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.dto.res.ComposePageClothesResponseDTO;
import net.nwrn.pf_contest.origin_images.entity.PersonImageEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    private final ClothesRepository clothesRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @Value("${properties.awsBucketName}")
    private String awsBucketName;

    @Value("${properties.cloudfrontDomain}")
    private String cloudfrontDomain;

    @Value("${properties.defaultImageUrl}")
    private String defaultImageUrl;

    @Override
    public String upload(MultipartFile personImage) {

        PersonImageEntity personImageEntity = new PersonImageEntity();

        System.out.println(personImage.getSize());
        return "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg";

    }

    @Override
    public Page<ComposePageClothesResponseDTO> getClothesList(Category category, Color color, Integer page, Integer size) {


        // 페이지네이션으로 엔터티 가져오기
        Page<ClothesEntity> clothesEntityPage = clothesRepository.getClothes(category, color, page, size);

//        Page<ComposePageClothesResponseDTO> pageClothesResponseDTOs = new PageImpl<>(
//                new ArrayList<>(), clothesEntityPage.getPageable(), clothesEntityPage.getTotalElements()
//        );

        List<ComposePageClothesResponseDTO> content = new ArrayList<>();


        for (ClothesEntity clothesEntity : clothesEntityPage) {

            String url;
            Long clothesId = clothesEntity.getId();
            List<ImageEntity> imageEntityList = imageRepository.findByRepoNameAndObjectId("clothes", clothesId);
            if (imageEntityList.isEmpty()) url = defaultImageUrl;

            else if (imageEntityList.size() >=2 ) throw new ApiException("데이터베이스 오류입니다.");

            else {
                ImageEntity imageEntity = imageEntityList.get(0);
                String repoName = imageEntity.getRepoName();
                Long objectId = imageEntity.getObjectId();
                String fileName = imageEntity.getFileName();
                url = imageService.combineUrl(repoName, objectId, fileName);
            }

            ComposePageClothesResponseDTO clothesResponseDTO = new ComposePageClothesResponseDTO();
            clothesResponseDTO.setClothesColor(clothesEntity.getColor());
            clothesResponseDTO.setClothesCategory(clothesEntity.getCategory());
            clothesResponseDTO.setClothesId(clothesId);
            clothesResponseDTO.setClothesImageUrl(url);

            content.add(clothesResponseDTO);
        }

        return new PageImpl<>(content, clothesEntityPage.getPageable(), clothesEntityPage.getTotalElements()) ;

    }
}
