package net.nwrn.pf_contest.person.repository;

import net.nwrn.pf_contest.person.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
