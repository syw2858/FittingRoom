package net.nwrn.pf_contest.origin_images.dto;

import lombok.Getter;
import lombok.Setter;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.entity.ClothesImageEntity;

@Getter
@Setter
public class ClothesImageDTO {

    private Long clothesImageSn;
    private String clothesImageUrl;
    private Category clothesCategory;
    private Color clothesColor;

    public ClothesImageDTO(ClothesImageEntity clothesImageEntity) {
        this.clothesImageSn = clothesImageEntity.getClothesImageSn();
        this.clothesImageUrl = clothesImageEntity.getClothesImageUrl();
    }
}
