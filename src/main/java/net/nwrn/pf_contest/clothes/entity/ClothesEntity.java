package net.nwrn.pf_contest.clothes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Clothes")
public class ClothesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;
}
