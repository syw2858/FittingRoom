package net.nwrn.pf_contest.compose.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.origin_images.dto.ClothesImageDTO;
import net.nwrn.pf_contest.origin_images.repository.ClothesImageRepository;
import net.nwrn.pf_contest.origin_images.repository.PersonImageRepository;
import net.nwrn.pf_contest.origin_images.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    private final ImageService imageService;
    private final AmazonS3 amazonS3;
    private final PersonImageRepository personImageRepository;
    private final ClothesImageRepository clothesImageRepository;

    @Override
    public String upload(MultipartFile file, String repo_name, Long ImageSn) {
        return imageService.upload(file, repo_name, ImageSn);
    }

    @Override
    public Long newImage() {
        amazonS3.
        return 0;
    }

    @Override
    public void setUrl(Long ImageSn, String ImageUrl) {

    }


    @Override
    public Page<ClothesImageDTO> getClothesImageList(Integer page, Integer size) {

        Page<ClothesImageDTO> result = null;
        return result;
    }



    @Override
    public String executeCompose(String personImageUrl, String clothesImageUrl) {
        String compoesedImageUrl = "person "+personImageUrl+" clothes "+clothesImageUrl;
        return "composedUrl";
    }

}
