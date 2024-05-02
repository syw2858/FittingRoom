package net.nwrn.pf_contest.users.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.compose.service.ComposeService;
import net.nwrn.pf_contest.security.AuthorizationService;
import net.nwrn.pf_contest.users.service.UserService;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ExceptionService exceptionService;
    private final AuthorizationService authorizationService;

    private final ComposeService composeService;

    @GetMapping("/join")
    public String joinPage(Model model, @RequestParam(required = false, name = "errorMessage") String errorMessage, HttpServletRequest request) {

        // 이미 가입한 사람은 가입 페이지에 들어올 수 없음
        try {
            if (!authorizationService.hasNoToken(request))
                return exceptionService.redirect("/", "이미 가입 완료 되었습니다");
            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));
            return "Join";

        } catch (ApiException e) {
            return exceptionService.redirect("/", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/", "백엔드에서 알 수 없는 에러가 발생하였습니다");
        }
    }

    @PostMapping("/join")
    public String join(@RequestParam(required = false, name = "userId") String userId,
                       @RequestParam(required = false, name = "password") String password,
                       HttpServletResponse response) {
        try {
            if (userId == null || userId.isEmpty())
                throw new ApiException("userId is null");
            if (password == null || password.isEmpty())
                throw new ApiException("password is null");
            
            userService.join(userId, password, response);
            return "redirect:/";
        } catch (ApiException e) {
            return exceptionService.redirect("/join", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/join", "백엔드에서 알수없는 에러입니다");
        }
    }
    
    @GetMapping("/login")
    public String loginPage(@RequestParam(required=false, name="errorMessage") String errorMessage, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 이미 로그인 한 사람은 로그인 페이지에 들어올 수 없음
        try {
            if (!authorizationService.hasNoToken(request)) {
                Cookie cookie = new Cookie("nwrn-token", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return exceptionService.redirect("/", "이미 로그인 상태입니다");
            } else {
                if (errorMessage == null)
                    model.addAttribute("errorMessage", errorMessage);
                else
                    model.addAttribute("errorMessage", exceptionService.decode(errorMessage));
                return "Login";
            }
        } catch (ApiException e) {
            return exceptionService.redirect("/errorPage", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/errorPage", "알수 없는 오류");
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam(required = false, name = "userId") String userId,
                        @RequestParam(required = false, name = "password") String password, HttpServletResponse response) {

        try {
            if (userId == null || userId.isEmpty())
                return exceptionService.redirect("/login", "아이디가 비었습니다");
            if (password == null || password.isEmpty())
                return exceptionService.redirect("/login", "비밀번호가 비었습니다");
            
            userService.login(userId, password, response);
            return "redirect:/";
            
        } catch (ApiException e) {
            return exceptionService.redirect("/login", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/login", "알수 없는 오류");
        }                    
    }

    @PostMapping("/logout")
    public String logout(@RequestParam(required = false, name="redirectUrl") String redirectUrl, HttpServletRequest request, HttpServletResponse response) {
        try {
            Long id = authorizationService.authenticate(request);
             if (id != null) {
                userService.logout(id, response);
                return "redirect:/";
            } else {
                return exceptionService.redirect(redirectUrl, "잘못된 인증 정보입니다.");
            }
        } catch (ApiException e) {
            return exceptionService.redirect("/errorPage", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/errorPage", "알수 없는 오류");
        }
    }


}
