package net.nwrn.pf_contest.compose.service;

import net.nwrn.pf_contest.compose.dto.res.ComposeBottomResponseDTO;
import net.nwrn.pf_contest.compose.dto.res.ComposeTopResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ComposeService {

    // 사람 이미지 파일 업로드
    String uploadPerson(MultipartFile personImage);

//    // 상의 이미지 파일 업로드
//    String uploadTop(MultipartFile topImage);
//
//    // 하의 이미지 파일 업로드
//    String uploadBottom(MultipartFile bottomImage);

    // 상의 이미지 리스트 불러오기
    List<ComposeTopResponseDTO> getTopList();

    // 하의 이미지 리스트 불러오기
    List<ComposeBottomResponseDTO> getBottomList();



    // 의류 이미지 리스트 불러오기
//    Page<ComposePageClothesResponseDTO> getClothesList(Category category, Color color, Integer page, Integer size);

}
