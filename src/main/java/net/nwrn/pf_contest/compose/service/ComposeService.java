package net.nwrn.pf_contest.compose.service;

import net.nwrn.pf_contest.compose.dto.ComposeResultDTO;
import net.nwrn.pf_contest.origin_images.dto.PersonImageDTO;
import net.nwrn.pf_contest.origin_images.dto.SelectedDataSetDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ComposeService {
    // 사람 이미지 리스트 불러오기
    Page<PersonImageDTO> getPersonImageList(Long userSn, Integer page, Integer size);

    // 사람 이미지 한 개 불러오기
    PersonImageDTO getPersonImage(Long userSn, Long personImageSn);

    // 사람 이미지 한 개 합성 재료로 넣기
    SelectedDataSetDTO setPersonImage(Long userSn, MultipartFile personImage);

    // 상의, 하의에 대해서도 같은 메서드 반복


    // 선택한 재료 바탕으로 합성 하기
    String executeCompose(Long userSn, MultipartFile personImage, MultipartFile topImage, MultipartFile bottomImage);

    // 합성 결과 보여주기
    ComposeResultDTO getComposeResult(Long userSn, Long composeResultSn);

}
