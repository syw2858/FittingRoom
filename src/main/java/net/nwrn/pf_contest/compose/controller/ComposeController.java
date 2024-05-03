package net.nwrn.pf_contest.compose.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.compose.service.ComposeService;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.dto.res.ClothesResponseImageDTO;
import net.nwrn.pf_contest.security.AuthorizationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
            @RequestParam(required=false, name="category") Category category,
            @RequestParam(required=false, name="color") Color color,
            @RequestParam(defaultValue="0", name="pageNum") Integer pageNum
            ) {
        try {

            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));


            model.addAttribute("personImageUrl", personImageUrl);
            model.addAttribute("composedImageUrl", composedImageUrl);

            // s3에 저장되어 있는 옷들을 페이지네이션 하여 x페이지의 옷 목록 불러오기

            Page<ClothesResponseImageDTO> pageOfClothesImage = composeService.getClothesImageList(category, color, pageNum, 15);
            model.addAttribute("pageOfClothesImage", pageOfClothesImage);

            return "FittingRoom";
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
            @RequestParam(required = false, value = "personImage") MultipartFile personImage,
            @RequestParam(required = false, value = "category") Category category,
            @RequestParam(required = false, value = "color") Color color,
            @RequestParam (required = false, value = "pageNum") Integer pageNum
    ) {

        try {

            String personImageUrl = composeService.upload(personImage);

            return "redirect:/fittingroom?pageNum=" + pageNum + "&personImageUrl=" + personImageUrl + "&category=" + category + "&color=" + color;

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }
}
