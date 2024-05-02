package net.nwrn.pf_contest.compose.service;

import net.nwrn.pf_contest.origin_images.dto.ClothesImageDTO;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ComposeService {
    Page<ClothesImageDTO> getClothesImageList(Category category, Color color, Integer page, Integer size);

    String upload(MultipartFile personImage);
}
