package net.nwrn.pf_contest.origin_images.repository;

import net.nwrn.pf_contest.origin_images.entity.ClothesImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClothesImageRepository extends JpaRepository<ClothesImageEntity, Long> {

    Long findByImageUrl(String imageUrl);

    Optional<ClothesImageEntity> findById(Long ImageSn);
}


