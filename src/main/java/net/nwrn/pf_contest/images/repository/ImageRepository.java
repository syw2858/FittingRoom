package net.nwrn.pf_contest.images.repository;

import net.nwrn.pf_contest.images.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    List<ImageEntity> findByRepoNameAndObjectId(String repoName, Long objectId);

}
