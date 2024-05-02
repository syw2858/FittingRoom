package net.nwrn.pf_contest.compose.service;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.origin_images.dto.ClothesImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import net.nwrn.pf_contest.origin_images.dto.filter.Category;
import net.nwrn.pf_contest.origin_images.dto.filter.Color;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    @Override
    public Page<ClothesImageDTO> getClothesImageList(Category category, Color color, Integer page, Integer size) {

        ClothesImageDTO dto = new ClothesImageDTO(1L, "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg", Category.shirt, Color.red);
        
        Page<ClothesImageDTO> result = new PageImpl<>(List.of(dto));
        return result;
    }

    @Override
    public String upload(MultipartFile personImage) {
        /*
        ImageEntity imageEntity = new ImageEntity();
        //setters
        composeRepository.save(Imageentity);
        String personImageUrl = imageService.upload(personImage, "person", imageSn);
        imageEntity.setUrl(personImageUrl);
        return personImageUrl;
         */
        System.out.println(personImage.getSize());
        return "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg";
    }
}
