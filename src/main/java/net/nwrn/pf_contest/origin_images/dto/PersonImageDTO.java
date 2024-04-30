package net.nwrn.pf_contest.origin_images.dto;

import lombok.Getter;
import lombok.Setter;
import net.nwrn.pf_contest.origin_images.entity.PersonImageEntity;

@Getter
@Setter
public class PersonImageDTO {

    private Long personImageSn;
    private String personImageUrl;

    public PersonImageDTO(PersonImageEntity personImageEntity) {
        this.personImageSn = personImageEntity.getPersonImageSn();
        this.personImageUrl = personImageEntity.getPersonImageUrl();
    }
}
