package net.nwrn.pf_contest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("")
    public String helloWorld () {
        return "HelloWorld";
    }
}
