package net.nwrn.pf_contest.origin_images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="selected_data_set")
@AllArgsConstructor
@NoArgsConstructor
public class SelectedDataSetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_data_set_sn")
    private Long selectedDataSetSn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_image_sn")
    private PersonImageEntity personImageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="top_image_sn")
    private TopImageEntity topImageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bottom_image_sn")
    private BottomImageEntity bottomImageEntity;

}
