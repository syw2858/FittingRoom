package net.nwrn.pf_contest.origin_images.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComposePageClothesResponseDTO {
    private Long clothesImageSn;
    private String clothesImageUrl;
    private Category clothesCategory;
    private Color clothesColor;

}
