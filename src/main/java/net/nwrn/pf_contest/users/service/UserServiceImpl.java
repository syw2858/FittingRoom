package net.nwrn.pf_contest.users.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.nwrn.pf_contest.common.security.AuthorizationService;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.users.repository.UserEntity;
import net.nwrn.pf_contest.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    @Override
    public void join(String userId, String password, HttpServletResponse response) {
        if (!userRepository.findByUserId(userId).isEmpty()) {
            throw new ApiException("이미 사용 중인 userId 입니다");
            // cookie x
        } else {
            userRepository.save(new UserEntity(null, userId, password));
            // cookie o
            String token = authorizationService.reverseParseToken(userId, password);
            Cookie cookie = new Cookie("nwrn-token", token);
            response.addCookie(cookie);
        }
    }

    @Override
    public void login(String userId, String password, HttpServletResponse response) {
        String zeroFirst = userId;
        String zeroSecond = password;

       List<UserEntity> userEntities = userRepository.findByUserIdAndPassword(userId, password);

        if (userEntities.size() == 1) {
            String one = authorizationService.reverseParseToken(zeroFirst, zeroSecond);
            Cookie cookie = new Cookie("nwrn-token", one);
            response.addCookie(cookie);
        } else {
            throw new ApiException("잘못된 인증 정보입니다");
        }
    }

    @Override
    public void logout(Long id, HttpServletResponse response) {
        Cookie cookie = new Cookie("nwrn-token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
