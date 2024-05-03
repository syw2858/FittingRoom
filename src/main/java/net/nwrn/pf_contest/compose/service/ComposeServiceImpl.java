package net.nwrn.pf_contest.compose.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.clothes.entity.ClothesEntity;
import net.nwrn.pf_contest.clothes.repository.ClothesRepository;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.dto.res.ComposePageClothesResponseDTO;
import net.nwrn.pf_contest.origin_images.entity.ClothesImageEntity;
import net.nwrn.pf_contest.origin_images.entity.PersonImageEntity;
import net.nwrn.pf_contest.origin_images.repository.ClothesImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    private final ClothesRepository clothesRepository;


    @Override
    public String upload(MultipartFile personImage) {

        PersonImageEntity personImageEntity = new PersonImageEntity();

        System.out.println(personImage.getSize());
        return "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg";

    }


    @Override
    public Page<ComposePageClothesResponseDTO> getClothesList(Category category, Color color, Integer page, Integer size) {

        // 페이지네이션으로 엔터티 가져오기
        Page<ClothesEntity> clothesImageEntityPage = clothesRepository.getClothes(category, color, page, size);

        // 엔터티를 DTO로 변환하여 반환
        return clothesImageEntityPage.map(entity -> {
            ComposePageClothesResponseDTO composePageClothesResponseDTO = new ComposePageClothesResponseDTO(
                    entity.getId(),
                    entity.getImageUrl(),
                    entity.getCategory(),
                    entity.getColor()
            );

            return composePageClothesResponseDTO;
        });

    }
}
