package net.nwrn.pf_contest.compose.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nwrn.pf_contest.origin_images.entity.SelectedDataSetEntity;
import net.nwrn.pf_contest.users.repository.UserEntity;

@Getter
@Entity
@Table(name="composed_result")
@AllArgsConstructor
@NoArgsConstructor
public class ComposeResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "composed_result_sn")
    Long composeResultSn;

    @Column(name = "composed_result_url")
    String composeResultUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_sn")
    UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="selected_data_set_sn")
    SelectedDataSetEntity selectedDataSetEntity;

}

