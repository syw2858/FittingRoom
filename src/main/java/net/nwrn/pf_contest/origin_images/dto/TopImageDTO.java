package net.nwrn.pf_contest.origin_images.dto;

import lombok.Getter;
import lombok.Setter;
import net.nwrn.pf_contest.origin_images.entity.TopImageEntity;

@Getter
@Setter
public class TopImageDTO {
    private Long topImageSn;
    private String topImageUrl;

    public TopImageDTO(TopImageEntity topImageEntity) {
        this.topImageSn = topImageEntity.getTopImageSn();
        this.topImageUrl = topImageEntity.getTopImageUrl();
    }
}
