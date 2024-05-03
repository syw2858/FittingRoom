package net.nwrn.pf_contest.compose.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.dto.res.ClothesResponseImageDTO;
import net.nwrn.pf_contest.origin_images.entity.ClothesImageEntity;
import net.nwrn.pf_contest.origin_images.entity.PersonImageEntity;
import net.nwrn.pf_contest.origin_images.repository.ClothesImageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    private final ClothesImageRepository clothesImageRepository;


    @Override
    public String upload(MultipartFile personImage) {

        PersonImageEntity personImageEntity = new PersonImageEntity();

        System.out.println(personImage.getSize());
        return "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg";

    }


    @Override
    public Page<ClothesResponseImageDTO> getClothesImageList(Category category, Color color, Integer page, Integer size) {

//        ClothesResponseImageDTO dtoOne = new ClothesResponseImageDTO(1L, "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg", Category.SHIRT, Color.RED);
//
//        ClothesResponseImageDTO dtoTwo = new ClothesResponseImageDTO(2L, "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg", Category.SHIRT, Color.WHITE);
//
//        ClothesResponseImageDTO dtoThree = new ClothesResponseImageDTO(3L, "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg", Category.SWEATSHIRT, Color.BLACK);
//        Page<ClothesResponseImageDTO> res = new PageImpl<>(List.of(dtoOne, dtoTwo, dtoThree));
//
//        return res;

        Pageable pageable = PageRequest.of(page, size);
        // 페이지네이션으로 엔터티 가져오기
        Page<ClothesImageEntity> clothesImageEntityPage = clothesImageRepository.findAll(pageable);

        // 엔터티를 DTO로 변환하여 반환
        return clothesImageEntityPage.map(entity -> {
            ClothesResponseImageDTO clothesResponseImageDTO = new ClothesResponseImageDTO();
            BeanUtils.copyProperties(entity, clothesResponseImageDTO);
            return clothesResponseImageDTO;
        });

    }
}
