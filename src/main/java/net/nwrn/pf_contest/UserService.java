package net.nwrn.pf_contest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    public void join(String userId, String password) {
        UserVO userVO = new UserVO();
        userVO.setUserId(userId);
        userVO.setPassword(password);
        userRepository.save(userVO);
    }

    public void login(String userId, String password, HttpServletResponse response) {
        String zeroFirst = userId;
        String zeroSecond = password;

       List<UserVO> userVOList = userRepository.findByUserIdAndPassword(userId, password);

        if(userVOList.size() == 1) {
            String one = authorizationService.reverseParseToken(zeroFirst, zeroSecond);
            Cookie cookie = new Cookie("nwrn-token", one);
            response.addCookie(cookie);
            return;
       }
        return;

    }


}
