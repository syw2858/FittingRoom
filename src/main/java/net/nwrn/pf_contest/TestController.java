package net.nwrn.pf_contest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
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
        Long id = authorizationService.authenticate(request);
//        if (id==null) {
//            return "redirect:/login";
//        }
        if (errorMessage == null) {
                model.addAttribute("errorMessage", errorMessage);
        } else {
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));
        }

        if (id == null) {
            model.addAttribute("userId", "로그인 정보가 없습니다");
            model.addAttribute("password", "로그인 정보가 없습니다");
            model.addAttribute("id", "로그인 정보가 없습니다");
        } else {
            UserEntity userEntity = testService.getUser(id);
            model.addAttribute("userId", userEntity.getUserId());
            model.addAttribute("password", userEntity.getPassword());
            model.addAttribute("id", userEntity.getId());
        }

        return "HelloWorld";
    }

    @PostMapping("/add")
    public String add (HttpServletRequest request, @RequestParam(required = false, name="name") String name, @RequestParam(required = false, name="age") Integer age) {
        Long id = authorizationService.authenticate(request);
        if (id==null) {
            String message = "인증되지 않은 사용자입니다.";
            return "redirect:/?errorMessage="+exceptionService.encode(message);
        }
        if (name == null) {
            String message = "아이디를 입력해주세요";
            return "redirect:/?errorMessage="+exceptionService.encode(message);
        }
        if (age == null) {
            String message = "나이를 입력해주세요";
            return "redirect:/?errorMessage="+exceptionService.encode(message);
        }
        else {
            try {
                testService.add(name,age);
                return "redirect:/";
            }
            catch (ApiException e) {
                String message = e.getMessage();
                return "redirect:/add?errorMessage="+exceptionService.encode(message);
            }
            catch (Exception e) {
                String message = "백엔드에서 알 수 없는 오류가 발생했습니다";
                log.error(exceptionService.generateMessage(), e);
                return "redirect:/add?errorMessage="+exceptionService.encode(message);
            }

        }



    }

}
