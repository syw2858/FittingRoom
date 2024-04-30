package net.nwrn.pf_contest.compose.controller;

import net.nwrn.pf_contest.compose.dto.ComposeResultDTO;
import net.nwrn.pf_contest.compose.service.ComposeService;
import net.nwrn.pf_contest.origin_images.dto.PersonImageDTO;
import net.nwrn.pf_contest.origin_images.dto.SelectedDataSetDTO;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public class ComposeController {

    ComposeService composeService;

    // 합성 서비스 페이지 열기
    @GetMapping("/fittingroom")
    public String composePage() {
        return "FittingRoom";
    }

    // 선택할 수 있는 사람 이미지 목록 불러오기
    @GetMapping("/fittingroom/list/person")
    public Page<PersonImageDTO> getPersonImageList(Long userSn, Model model) {
        return composeService.getPersonImageList(userSn, page, size);
    }

    // 사람 이미지 한 개 집기 (의문 : 학습용 이미지를 고르는 거면 왜 한 개만 고르는 건지 모르겠다. 동일한 실체를 나타내는 여러개의 이미지를 제공해야 학습이 되는데)
    @GetMapping("fittingroom/list/{personImageSn}")
    public PersonImageDTO getPersonImage(Long userSn, @PathVariable Long personImageSn, Model model) {
        return composeService.getPersonImage(userSn, personImageSn);
    }

    // 사람 이미지 한 개 합성 재료로 넣기
    @PostMapping("/fittingroom")
    public SelectedDataSetDTO setPersonImage(Long userSn, @RequestParam(required=true, name= "personSn") String person) {
        return composeService.setPersonImage(userSn, person);
    }

    // 선택할 수 있는 상의 이미지 목록 불러오기

    // 상의 이미지 한 개 선택하기

    // 상의 이미지 한 개 합성 재료로 넣기

    // 선택할 수 있는 하의 이미지 목록 불러오기

    // 하의 이미지 한 개 선택하기

    // 하의 이미지 한 개 합성 재료로 넣기


    // 선택한 재료 바탕으로 합성 하기
    @PostMapping("/fittingroom")
    public String executeCompose(Long userSn, @RequestPart MultipartFile personImage, @RequestPart(required = false) MultipartFile topImage, @RequestPart(required = false) MultipartFile bottomImage) {
        composeService.execute(userSn, personImage, topImage, bottomImage);
        return "FittingRoom";
    }

    // 합성 결과 보여주기
    @GetMapping("/fittingroom")
    public ComposeResultDTO getComposeResult(Long userSn, @RequestParam Long composeResultSn, Model model) {
        composeService.getComposeResult(userSn, composeResultSn);
        return "FittingRoom"
    }

}
