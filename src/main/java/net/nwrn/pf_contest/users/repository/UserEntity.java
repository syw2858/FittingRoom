package net.nwrn.pf_contest.users.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="password")
    private String password;

    @Column(name="user_id")
    private String userId;
}
