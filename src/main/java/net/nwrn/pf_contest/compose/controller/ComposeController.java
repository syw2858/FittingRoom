package net.nwrn.pf_contest.compose.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.compose.service.ComposeService;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import net.nwrn.pf_contest.origin_images.dto.ClothesImageDTO;
import net.nwrn.pf_contest.origin_images.dto.SelectedFilterSetDTO;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.service.ImageService;
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

    private final ImageService imageService;

    // 합성 서비스 페이지 열기
    @GetMapping("/fittingroom")
    public String composePage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @RequestParam(required=false, name="errorMessage")String errorMessage,
            @RequestParam(required=false, name="person_image_url") String personImageUrl,
            @RequestParam(required=false, name="composed_image_url") String composedImageUrl,
            @RequestParam(defaultValue="0", name="page_num") Integer pageNum,

            @RequestParam(required=false, name="category") Category category,
            @RequestParam(required=false, name="color") Color color

            ) {
        try {

            if (errorMessage == null)
                model.addAttribute("errorMessage", errorMessage);
            else
                model.addAttribute("errorMessage", exceptionService.decode(errorMessage));

            model.addAttribute("person_image_url", personImageUrl);
            model.addAttribute("composed_image_url", composedImageUrl);

            // 검색 필터를 지정하지 않았을 경우 : s3에 저장되어 있는 옷들을 페이지네이션 하여 x페이지의 옷 목록 불러오기
            if (category == null && color == null) {
                Page<ClothesImageDTO> pageOfClothesImage = composeService.getClothesImageList(pageNum, 15);
                model.addAttribute("pageOfClothesImage", pageOfClothesImage);
            } else {

                // 검색 필터 지정했을 경우

                Page<SelectedFilterSetDTO> pageOfSelectedFilterImage = composeService.getSelectedFilterSetList(category, color, pageNum, 15);

                model.addAttribute("pageOfClothesImage", pageOfSelectedFilterImage);

            }

            return "FittingRoom";
        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알수 없는 오류");
        }
    }
        

    // 사람 이미지 한 개 합성 재료로 넣기
    @PostMapping("/fittingroom")
    public String putPersonImage(@RequestPart MultipartFile personImage, Integer pageNum) {

        try {
            // 새 이미지 생성 시 이미지 시리얼 넘버 저장
            Long imageSn = composeService.newImage();

            // 문자열 url 반환받는 메서드
            String personImageUrl = imageService.upload(personImage, "person", imageSn);

            // 합성 재료로 쓰일 url 선정하기
            composeService.setUrl(imageSn, personImageUrl);

            return "redirect:/fittingroom?page_num=" + pageNum + "&person_image_url=" + personImageUrl;

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }

    // 의류 이미지 한 개 합성 재료로 넣기
    @PostMapping("/fittingroom")
    public String putClothesImage(Long imageSn, String clothesImageUrl, Integer pageNum) {

        try {
            // 클라우드에 있는 파일의 문자열 url 반환받는 메서드
            String[] UrlParts = imageService.splitUrl(clothesImageUrl);
            clothesImageUrl = UrlParts[1];

            // 합성 재료로 쓰일 url 선정하기
            composeService.setUrl(imageSn, clothesImageUrl);

            return "redirect:/fittingroom?page_num=" + pageNum + "&clothes_image_url=" + clothesImageUrl;

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }
    }

    // 검색 필터에 해당하는 조합 불러오기
    @PostMapping("/fittingroom")
    public String getSelectedFilterImageList(@RequestBody SelectedFilterSetDTO selectedFilterSet, Model model, Integer pageNum) {

        try {
           // 검색 필터 별로 쿼리스트링 생성
            ClothesImageDTO clothes = selectedFilterSet.getClothesImageDTO();

            Category category = clothes.getClothesCategory();
            Color color = clothes.getClothesColor();

            return "redirect:/fittingroom?page_num="+pageNum+"&category"+category+"&color="+color;
        } catch (ApiException e) {
        return exceptionService.redirect("/fittingroom", e.getMessage());}
        catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알수 없는 오류");
        }
    }

    // 선택한 재료 바탕으로 합성 하기
    @PostMapping("/fittingroom")
    public String executeCompose(String personImageUrl, String clothesImageUrl, Integer pageNum) {

        String composedImageUrl = composeService.executeCompose(personImageUrl, clothesImageUrl);

        return "redirect:/fittingroom?page_num=" + pageNum + "&composed_image_url=" + composedImageUrl;
    }



}
