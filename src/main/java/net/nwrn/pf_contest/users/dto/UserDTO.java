package net.nwrn.pf_contest.users.dto;

import lombok.Getter;
import lombok.Setter;
import net.nwrn.pf_contest.users.repository.UserEntity;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String password;
    private String userId;

    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.password = userEntity.getPassword();
        this.userId = userEntity.getUserId();
    }

}
