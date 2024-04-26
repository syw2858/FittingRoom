package net.nwrn.pf_contest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/join")
    public String joinPage(Model model, @RequestParam(required = false, name = "errorMessage") String errorMessage) {
        if (errorMessage == null)
            model.addAttribute("errorMessage", errorMessage);
        else
            model.addAttribute("errorMessage", exceptionService.decode(errorMessage));
        return "Join";
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
    public String loginPage(@RequestParam(required=false, name="errorMessage") String errorMessage, Model model) {
        try {
            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));
            return "Login";
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

}
