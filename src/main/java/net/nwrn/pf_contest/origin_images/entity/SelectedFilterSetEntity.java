package net.nwrn.pf_contest.origin_images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="selected_filter_set")
@AllArgsConstructor
@NoArgsConstructor
public class SelectedFilterSetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_filter_set_sn")
    private Long selectedFilterSetSn;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="person_image_sn")
//    private PersonImageEntity personImageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clothes_image_sn")
    private ClothesImageEntity clothesImageEntity;

}
