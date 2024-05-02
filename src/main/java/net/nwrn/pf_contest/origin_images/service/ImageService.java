package net.nwrn.pf_contest.origin_images.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    String upload(MultipartFile image, String repo_name, Long sn);
    List<String> uploadList(List<MultipartFile> images, String repo_name, Long sn, boolean def, String defUrl);
    void delete(String imageUrl);
    void deleteAll(List<String> imageUrlList);
    String[] splitUrl(String imageUrl);
    List<String> jsonToImageUrlList(String json);
}


