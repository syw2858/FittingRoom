package net.nwrn.pf_contest.origin_images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="top_image")
@AllArgsConstructor
@NoArgsConstructor
public class TopImageEntity {

    @Id
    @Column(name="top_image_sn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topImageSn;

    @Column(name="top_image_url")
    private String topImageUrl;
}
