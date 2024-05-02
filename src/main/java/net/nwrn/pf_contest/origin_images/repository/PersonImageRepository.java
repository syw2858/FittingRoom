package net.nwrn.pf_contest.origin_images.repository;

import net.nwrn.pf_contest.origin_images.entity.PersonImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonImageRepository extends JpaRepository<PersonImageEntity, Long> {

    Long findByImageUrl(String imageUrl);

    Optional<PersonImageEntity> findById(Long ImageSn);
}
