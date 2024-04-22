package net.nwrn.pf_contest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserVO, Long> {

//    List<UserVO> findAll();
    List<UserVO> findByUserIdAndPassword(String userId, String password);
}
