package net.nwrn.pf_contest.origin_images.dto;

import lombok.Getter;
import lombok.Setter;
import net.nwrn.pf_contest.origin_images.entity.BottomImageEntity;

@Getter
@Setter
public class BottomImageDTO {

    private Long bottomImageSn;
    private String bottomImageUrl;

    public BottomImageDTO(BottomImageEntity bottomImageEntity) {
        this.bottomImageSn = bottomImageEntity.getBottomImageSn();
        this.bottomImageUrl = bottomImageEntity.getBottomImageUrl();
    }
}
