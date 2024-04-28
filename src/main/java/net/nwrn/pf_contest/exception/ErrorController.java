package net.nwrn.pf_contest.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ErrorController {
    private final ExceptionService exceptionService;
    
    @GetMapping("/errorPage")
    public String errorPage(Model model, @RequestParam(required = false, name = "errorMessage") String errorMessage) {
         if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));
        return "Error";
    }
}
