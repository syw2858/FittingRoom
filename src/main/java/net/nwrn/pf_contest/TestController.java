package net.nwrn.pf_contest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import net.nwrn.pf_contest.common.security.AuthorizationService;
import net.nwrn.pf_contest.test.dto.HomeModelDTO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final AuthorizationService authorizationService;
    private final ExceptionService exceptionService;

    @GetMapping("")
    public String helloWorld (HttpServletRequest request, Model model, @RequestParam(required=false, name="errorMessage")String errorMessage) {
        try {
            Long id = authorizationService.authenticate(request); // 쿠키를 로그인에서 담아주고, 여기에서 읽는겁니다.

            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));

            HomeModelDTO dto = testService.getUser(id);
            model.addAttribute("userId", dto.getUserId());
            model.addAttribute("password", dto.getPassword());
            model.addAttribute("id", dto.getId());

            return "HelloWorld";
        } catch (ApiException e) {
            return exceptionService.redirect("/errorPage", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/errorPage", "알수 없는 오류");
        }
    }

    @PostMapping("/add")
    public String add (HttpServletRequest request, @RequestParam(required = false, name="name") String name, @RequestParam(required = false, name="age") Integer age) {
        try {
            Long id = authorizationService.authenticate(request);
            if (id==null)
                return exceptionService.redirect("/", "인증되지 않은 사용자입니다.");
            if (name == null)
                return exceptionService.redirect("/", "아이디를 입력해주세요");
            if (age == null)
                return exceptionService.redirect("/", "나이를 입력해주세요");
            testService.add(name,age);
            return "redirect:/";
        } catch (ApiException e) {
            return exceptionService.redirect("/", e.getMessage());
        } catch (Exception e) {
            String message = "백엔드에서 알 수 없는 오류가 발생했습니다";
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/", e.getMessage());
        }
    }
}
