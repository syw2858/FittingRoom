package net.nwrn.pf_contest.clothes.repository;

import net.nwrn.pf_contest.clothes.entity.TopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopRepository extends JpaRepository<TopEntity, Long> {
}

