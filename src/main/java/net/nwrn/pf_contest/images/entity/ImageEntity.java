package net.nwrn.pf_contest.images.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "Images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "repo_name")
    private String repoName;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "file_name")
    private String fileName;

}
