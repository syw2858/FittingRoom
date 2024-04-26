package net.nwrn.pf_contest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final UserRepository userRepository;

    private String[] parseToken(String token) {

        Base64.Decoder decoder = Base64.getDecoder();

        String zero = token;
        byte[] one = decoder.decode(zero);
        String two = new String(one);
        String[] three = two.split(":");
        return three;

//        String[] rt = new String(Base64.getDecoder().decode(token)).split(":");
//        System.out.println(rt[0]);
//        System.out.println(rt[1]);
//        return rt;

    }

    public String reverseParseToken(String userId, String password) {

        Base64.Encoder encoder = Base64.getEncoder();

        String zeroFirst = userId;
        String zeroSecond = password;
        
        String one = zeroFirst+":"+zeroSecond;
        byte[] two = one.getBytes();
        String three = encoder.encodeToString(two);
        return three;
    }

    private String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
               String name = cookie.getName();
               if (name.equals("nwrn-token")) {
                   return cookie.getValue();
               }
            }
            return null;
        }
        return null;
    }

    public Long authenticate(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) {
            return null;
        }

        String[] parsed = parseToken(token);

        String userId = parsed[0]; //토큰에서 분리
        String password = parsed[1];
        List<UserEntity> userEntities = userRepository.findByUserIdAndPassword(userId, password); //아이디 비밀번호 찾기
        if (userEntities.size() > 0) {
            return userEntities.get(0).getId();
        }
        
        return null;

    }
}
