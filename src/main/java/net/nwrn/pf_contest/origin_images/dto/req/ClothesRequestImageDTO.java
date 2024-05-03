package net.nwrn.pf_contest.origin_images.dto.req;

import lombok.*;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClothesRequestImageDTO {
    private String clothesImageUrl;
    private Category clothesCategory;
    private Color clothesColor;
}
