package net.nwrn.pf_contest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final AuthorizationService authorizationService;

    @GetMapping("")
    public String helloWorld (HttpServletRequest request, Model model) {
        Long id = authorizationService.authenticate(request);
        if (id==null) {
            return "redirect:/login";
        }
        UserEntity userEntity = testService.getUser(id);
        model.addAttribute("userId", userEntity.getUserId());
        model.addAttribute("password", userEntity.getPassword());
        model.addAttribute("id", userEntity.getId());
        return "HelloWorld";
    }

    @PostMapping("/add")
    public String add (HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("age") Integer age) {
        Long id = authorizationService.authenticate(request);
        if (id==null) {
            throw new RuntimeException();
        }
        testService.add(userId,age);
        return "redirect:/";
    }

}
