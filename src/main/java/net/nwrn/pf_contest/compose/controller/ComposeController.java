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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam(required=false, name="composedImageUrl") String composedImageUrl,
            @RequestParam(required=false, name="topImageUrl") String topImageUrl,
            @RequestParam(required=false, name="bottomImageUrl") String bottomImageUrl,
            @RequestParam(required=false, name="isSample") Boolean isSample
            ) {

        try {

            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));

            if (personImageUrl != null)
                model.addAttribute("personImageUrl", personImageUrl);

            if (composedImageUrl != null)
                model.addAttribute("composedImageUrl", composedImageUrl);

            if (topImageUrl != null)
                model.addAttribute("topImageUrl", topImageUrl);

            if (bottomImageUrl != null)
                model.addAttribute("bottomImageUrl", bottomImageUrl);

            // 상의 이미지 리스트 불러오기
            List<ComposeTopResponseDTO> topList = composeService.getTopList();
            model.addAttribute("topList", topList);

            // 하의 이미지 리스트 불러오기
            List<ComposeBottomResponseDTO> bottomList = composeService.getBottomList();
            model.addAttribute("bottomList", bottomList);

            return "FittingRoom2";

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
            @RequestParam(required = false, name = "personImage") MultipartFile personImage,
            @RequestParam(required = false, name = "topImageUrl") String topImageUrl,
            @RequestParam(required = false, name = "bottomImageUrl") String bottomImageUrl,
            RedirectAttributes redirectAttributes
    ) {

        try {

            String personImageUrl = composeService.uploadPerson(personImage);

            // RedirectAttributes를 사용하여 데이터 보존
            redirectAttributes.addAttribute("personImageUrl", personImageUrl);

            if (topImageUrl != null)
                redirectAttributes.addAttribute("topImageUrl", topImageUrl);
            if (bottomImageUrl != null)
                redirectAttributes.addAttribute("bottomImageUrl", bottomImageUrl);

            return "redirect:/fittingroom"; // 리다이렉션

//            StringBuilder sb = new StringBuilder();
//            sb.append("redirect:/fittingroom?personImageUrl=");
//            sb.append(personImageUrl);
//            if(topImageUrl != null) {
//                sb.append("&topImageUrl=");
//                sb.append(topImageUrl);
//            }
//            if(bottomImageUrl != null) {
//                sb.append("&bottomImageUrl=");
//                sb.append(bottomImageUrl);
//            }
//
//            return sb.toString();

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }


    // 상의 이미지 합성 재료로 넣기
    @PostMapping("/fittingroom/uploadTopImage")
    public String putTopImage(
            @RequestParam(required = false, name = "personImageUrl") String personImageUrl,
            @RequestParam(required = false, name = "topImage") MultipartFile topImage,
            @RequestParam(required = false, name = "bottomImageUrl") String bottomImageUrl,
            RedirectAttributes redirectAttributes
    ) {

        try {

            String topImageUrl = composeService.uploadTop(topImage);

            redirectAttributes.addAttribute("topImageUrl", topImageUrl);

            // RedirectAttributes를 사용하여 데이터 보존
            if (personImageUrl != null) {
                redirectAttributes.addAttribute("personImageUrl", personImageUrl);
            }
            if (bottomImageUrl != null)
                redirectAttributes.addAttribute("topImageUrl", bottomImageUrl);


            return "redirect:/fittingroom"; // 리다이렉션

//            StringBuilder sb = new StringBuilder();
//            sb.append("redirect:/fittingroom?topImageUrl=");
//            sb.append(topImageUrl);
//
//            if(personImageUrl != null) {
//                sb.append("&personImageUrl=");
//                sb.append(personImageUrl);
//            }
//            if(bottomImageUrl != null) {
//                sb.append("&bottomImageUrl=");
//                sb.append(bottomImageUrl);
//            }
//
//            return sb.toString();

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }

    // 하의 이미지 합성 재료로 넣기
    @PostMapping("/fittingroom/uploadBottomImage")
    public String putBottomImage(

            @RequestParam(required = false, name = "personImageUrl") String personImageUrl,
            @RequestParam(required = false, name = "topImageUrl") String topImageUrl,
            @RequestParam(required = false, name = "bottomImage") MultipartFile bottomImage,
            RedirectAttributes redirectAttributes
    ) {

        try {

            String bottomImageUrl = composeService.uploadBottom(bottomImage);

            // RedirectAttributes를 사용하여 데이터 보존

            if (personImageUrl != null) {
                redirectAttributes.addAttribute("personImageUrl", personImageUrl);
            }
            if (topImageUrl != null) {
                redirectAttributes.addAttribute("topImageUrl", topImageUrl);
            }
            redirectAttributes.addAttribute("bottomImageUrl", bottomImageUrl);

            return "redirect:/fittingroom"; // 리다이렉션

//            StringBuilder sb = new StringBuilder();
//            sb.append("redirect:/fittingroom?bottomImageUrl=");
//            sb.append(bottomImageUrl);
//
//            if(personImageUrl != null) {
//                sb.append("&personImageUrl=");
//                sb.append(personImageUrl);
//            }
//            if(topImageUrl != null) {
//                sb.append("&topImageUrl=");
//                sb.append(topImageUrl);
//            }
//
//            return sb.toString();

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }

}
