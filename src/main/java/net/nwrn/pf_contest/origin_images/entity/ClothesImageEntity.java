package net.nwrn.pf_contest.origin_images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;

@Getter
@Entity
@Table(name="clothes_image")
@AllArgsConstructor
@NoArgsConstructor
public class ClothesImageEntity {

    @Id
    @Column(name="clothes_image_sn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clothesImageSn;

    
    @Column(name="clothes_image_url", nullable = false)
    private String clothesImageUrl = "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg";

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Color color;


    public ClothesImageEntity(Category clothesCategory, Color clothesColor) {
    }
}