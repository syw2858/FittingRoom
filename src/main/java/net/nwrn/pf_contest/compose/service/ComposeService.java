package net.nwrn.pf_contest.compose.service;

import net.nwrn.pf_contest.origin_images.dto.ClothesImageDTO;
import net.nwrn.pf_contest.origin_images.dto.SelectedFilterSetDTO;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ComposeService {

    // 문자열 url 반환받는 ImageService 메서드
    String upload(MultipartFile file, String repo_name, Long ImageSn);

    // 새 이미지 생성하면서 그 sn 반환 받는 메서드
    Long newImage();

    // 합성 재료로 쓰일 url 선정하기
    void setUrl(Long ImageSn, String ImageUrl);

    // 의류 이미지 리스트 불러오기
    Page<ClothesImageDTO> getClothesImageList(Integer page, Integer size);

    // 필터에 부합하는 의류 이미지 검색 결과 리스트 리턴하기
    Page<SelectedFilterSetDTO> getSelectedFilterSetList(Category category, Color color, Integer page, Integer size);


    // 선택한 재료 바탕으로 합성 하기
    String executeCompose(Long userSn, MultipartFile personImage, MultipartFile clothesImage);


    String executeCompose(String personImageUrl, String clothesImageUrl);
}
