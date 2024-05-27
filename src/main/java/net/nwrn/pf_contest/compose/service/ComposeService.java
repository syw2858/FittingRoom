package net.nwrn.pf_contest.compose.service;

import net.nwrn.pf_contest.compose.dto.res.ComposeBottomResponseDTO;
import net.nwrn.pf_contest.compose.dto.res.ComposeTopResponseDTO;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ComposeService {

    // 사람 이미지 파일 업로드
    String uploadPerson(MultipartFile personImage);

    // 상의 이미지 파일 업로드
    String uploadTop(MultipartFile topImage);
//
    // 하의 이미지 파일 업로드
    String uploadBottom(MultipartFile bottomImage);

    String compose(Boolean isSample, String personImageUrl, String topImageUrl, String bottomImageUrl, String sampleTopImageUrl, String sampleBottomImageUrl);

    // 상의 이미지 리스트 불러오기
    List<ComposeTopResponseDTO> getTopList();

    // 하의 이미지 리스트 불러오기
    List<ComposeBottomResponseDTO> getBottomList();


    void getComposeImageUrl();

    // 의류 이미지 리스트 불러오기
//    Page<ComposePageClothesResponseDTO> getClothesList(Category category, Color color, Integer page, Integer size);

}
