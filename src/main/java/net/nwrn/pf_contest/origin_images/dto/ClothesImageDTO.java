package net.nwrn.pf_contest.origin_images.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;

@Getter
@Setter
@AllArgsConstructor
public class ClothesImageDTO {

    private Long clothesImageSn;
    private String clothesImageUrl; // 
    private Category clothesCategory;
    private Color clothesColor;
}
