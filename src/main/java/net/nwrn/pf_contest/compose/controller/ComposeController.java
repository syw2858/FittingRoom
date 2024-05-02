package net.nwrn.pf_contest.compose.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.compose.service.ComposeService;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import net.nwrn.pf_contest.origin_images.dto.ClothesImageDTO;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.security.AuthorizationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            @RequestParam(required=false, name="person_image_url") String personImageUrl,
            @RequestParam(required=false, name="composed_image_url") String composedImageUrl,
            @RequestParam(required=false, name="category") Category category,
            @RequestParam(required=false, name="color") Color color,
            @RequestParam(defaultValue="0", name="page_num") Integer pageNum
            ) {
        try {

            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));

            model.addAttribute("personImageUrl", personImageUrl);
            model.addAttribute("composedImageUrl", composedImageUrl);

            // 검색 필터를 지정하지 않았을 경우 : s3에 저장되어 있는 옷들을 페이지네이션 하여 x페이지의 옷 목록 불러오기
            Page<ClothesImageDTO> pageOfClothesImage = composeService.getClothesImageList(category, color, pageNum, 15);
            model.addAttribute("pageOfClothesImage", pageOfClothesImage);

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
    public String putPersonImage(@RequestParam(required = false, value = "personImage") MultipartFile personImage, @RequestParam(required = false, value = "category") Category category, @RequestParam(required = false, value = "color") Color color, @RequestParam (required = false, value = "pageNum") Integer pageNum) {

        try {

            String personImageUrl = composeService.upload(personImage);
            

            //            Long imageSn = composeService.newImage();

            // 문자열 url 반환받는 메서드
            //            String personImageUrl = imageService.upload(personImage, "person", imageSn);

            // 합성 재료로 쓰일 url 선정하기
            //            composeService.setUrl(imageSn, personImageUrl);

            return "redirect:/fittingroom?page_num=" + pageNum + "&person_image_url=" + personImageUrl + "&category=" + category + "&color=" + color;

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }
}
