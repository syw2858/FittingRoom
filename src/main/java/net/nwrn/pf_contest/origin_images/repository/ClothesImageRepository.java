package net.nwrn.pf_contest.origin_images.repository;

import net.nwrn.pf_contest.origin_images.entity.ClothesImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesImageRepository extends PagingAndSortingRepository<ClothesImageEntity, Long>, JpaRepository<ClothesImageEntity, Long> {

}
