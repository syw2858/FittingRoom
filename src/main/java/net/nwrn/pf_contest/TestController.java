package net.nwrn.pf_contest;

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

    @GetMapping("")
    public String helloWorld (Model model) {
        TestVO testVO = testService.getRecent();
        model.addAttribute("name", testVO.getName());
        model.addAttribute("age", testVO.getAge());
        return "HelloWorld";
    }

    @PostMapping("/add")
    public String add (@RequestParam("name") String name, @RequestParam("age") Integer age) {
        testService.add(name,age);
        return "redirect:/";
    }

}
