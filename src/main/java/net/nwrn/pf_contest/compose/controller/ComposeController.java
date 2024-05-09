package net.nwrn.pf_contest.compose.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.common.security.AuthorizationService;
import net.nwrn.pf_contest.compose.dto.res.ComposeBottomResponseDTO;
import net.nwrn.pf_contest.compose.dto.res.ComposeTopResponseDTO;
import net.nwrn.pf_contest.compose.service.ComposeService;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ComposeController {

    private final AuthorizationService authorizationService;
    private final ExceptionService exceptionService;
    private final ComposeService composeService;


    // 합성 서비스 페이지 열기
    @GetMapping("/fittingroom")
    public String composePage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @RequestParam(required=false, name="errorMessage")String errorMessage,
            @RequestParam(required=false, name="personImageUrl") String personImageUrl,
            @RequestParam(required=false, name="composedImageUrl") String composedImageUrl
            ) {

        try {

            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));
            
            model.addAttribute("personImageUrl", personImageUrl);
            model.addAttribute("composedImageUrl", composedImageUrl);

            // 상의 이미지 리스트 불러오기
            List<ComposeTopResponseDTO> topList = composeService.getTopList();
            model.addAttribute("topList", topList);

            // 하의 이미지 리스트 불러오기
            List<ComposeBottomResponseDTO> bottomList = composeService.getBottomList();
            model.addAttribute("bottomList", bottomList);

            return "EditedFittingRoom";
        } catch (ApiException e) {
            return exceptionService.redirect("/errorPage", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/errorPage", "알수 없는 오류");
        }
    }
        

    // 사람 이미지 한 개 합성 재료로 넣기
    @PostMapping("/fittingroom/uploadPersonImage")
    public String putPersonImage(
            @RequestParam(required = false, value = "personImage") MultipartFile personImage
    ) {

        try {

            String personImageUrl = composeService.uploadPerson(personImage);

            return "redirect:/fittingroom?personImageUrl="+personImageUrl;

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }
}
