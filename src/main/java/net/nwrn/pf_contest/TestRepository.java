package net.nwrn.pf_contest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<TestVO, Long> {
    List<TestVO> findAll();
}
