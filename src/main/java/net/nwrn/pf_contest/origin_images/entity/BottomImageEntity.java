package net.nwrn.pf_contest.origin_images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="bottom_image")
@AllArgsConstructor
@NoArgsConstructor
public class BottomImageEntity {

    @Id
    @Column(name="bottom_image_sn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bottomImageSn;

    @Column(name="bottom_image_url")
    private String bottomImageUrl;
}