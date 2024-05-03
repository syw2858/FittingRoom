package net.nwrn.pf_contest.origin_images.service;

import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadImageToS3AndGetUrl(MultipartFile Image, String repoName, Long ImageSn);

    public Long newClothesImageAndGetId(Category category, Color color);

    public String uploadNewClothesImage(MultipartFile Image, Category category, Color color);

    public String uploadNewPersonImage(MultipartFile Image);
}
