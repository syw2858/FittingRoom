package net.nwrn.pf_contest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    @Override
    public void join(String userId, String password) {
        UserDTO userDTO = new UserDTO(new UserEntity());
        userDTO.setUserId(userId);
        userDTO.setPassword(password);
        userRepository.save(userDTO);
    }

    @Override
    public void login(String userId, String password, HttpServletResponse response) {
        String zeroFirst = userId;
        String zeroSecond = password;

       List<UserEntity> userEntities = userRepository.findByUserIdAndPassword(userId, password);

        if(userEntities.size() == 1) {
            String one = authorizationService.reverseParseToken(zeroFirst, zeroSecond);
            Cookie cookie = new Cookie("nwrn-token", one);
            response.addCookie(cookie);
            return;
       }
        return;

    }


}
