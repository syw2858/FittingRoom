package net.nwrn.pf_contest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/join")
    public String joinPage() {
        return "Join";
    }

    @PostMapping("/join")
    public String join(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        userServiceImpl.join(userId, password);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("password") String password, HttpServletResponse response) {
        userServiceImpl.login(userId, password, response);
        return "redirect:/";
    }
}
