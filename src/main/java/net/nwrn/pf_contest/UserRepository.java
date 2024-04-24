package net.nwrn.pf_contest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    List<UserVO> findAll();
    List<UserEntity> findByUserIdAndPassword(String userId, String password);

    List<UserEntity> findByUserId(String userId);

}
