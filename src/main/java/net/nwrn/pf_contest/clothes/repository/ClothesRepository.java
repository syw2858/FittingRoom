package net.nwrn.pf_contest.clothes.repository;

import net.nwrn.pf_contest.clothes.entity.ClothesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesRepository extends JpaRepository<ClothesEntity, Long>, ClothesQueryDsl {
}
