package net.nwrn.pf_contest.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    List<UserVO> findAll();
    List<UserEntity> findByUserIdAndPassword(String userId, String password);

    List<UserEntity> findByUserId(String userId);

}
