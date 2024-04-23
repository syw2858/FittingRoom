package net.nwrn.pf_contest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
