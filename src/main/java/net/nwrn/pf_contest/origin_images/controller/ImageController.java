package net.nwrn.pf_contest.origin_images.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.compose.service.ComposeService;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.exception.ExceptionService;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ComposeService composeService;
    private final ImageService imageService;
    private final ExceptionService exceptionService;

    @PostMapping("/fittingroom/uploadClothesImage")
    public String uploadClothesImage(
            @RequestParam(required = false, value = "clothesImage") MultipartFile clothesImage,
            @RequestParam(required = false, value = "category") Category category,
            @RequestParam(required = false, value = "color") Color color,
            @RequestParam (required = false, value = "pageNum") Integer pageNum
    ) {
        try {

            String url = imageService.uploadNewClothesImage(clothesImage, category, color);
            System.out.println(url);
            return url;

        } catch (ApiException e) {
            return exceptionService.redirect("/fittingroom", e.getMessage());
        } catch (Exception e) {
            log.error(exceptionService.generateMessage(), e);
            return exceptionService.redirect("/fittingroom", "알 수 없는 오류");
        }

    }
}
