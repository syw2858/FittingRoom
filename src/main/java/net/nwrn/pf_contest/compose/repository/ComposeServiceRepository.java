package net.nwrn.pf_contest.compose.repository;

import net.nwrn.pf_contest.origin_images.entity.PersonImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComposeServiceRepository extends JpaRepository<PersonImageEntity, Long> {
}
