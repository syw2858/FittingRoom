package net.nwrn.pf_contest.compose.service;

import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import net.nwrn.pf_contest.origin_images.dto.res.ClothesResponseImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ComposeService {

    String upload(MultipartFile file);


    // 의류 이미지 리스트 불러오기
    Page<ClothesResponseImageDTO> getClothesImageList(Category category, Color color, Integer page, Integer size);

}
