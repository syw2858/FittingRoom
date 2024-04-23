package net.nwrn.pf_contest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    private final ExceptionService exceptionService;

    @Override
    public void join(String userId, String password) {
        UserEntity userEntity = new UserEntity();
        try {
            List<UserEntity> user_exist = userRepository.findByUserId(userId);
            if(user_exist.isEmpty()) {
                userDTO.setPassword(password);
                userRepository.save(userDTO);
            }
            throw

        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "중복된 아이디 입니다.");
        }
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
