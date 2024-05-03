package net.nwrn.pf_contest.origin_images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="person_image")
@AllArgsConstructor
@NoArgsConstructor
public class PersonImageEntity {

    @Id
    @Column(name="person_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personImageId;

    @Column(name="person_image_url")
    private String personImageUrl;
}
