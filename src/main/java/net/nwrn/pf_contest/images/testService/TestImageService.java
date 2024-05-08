package net.nwrn.pf_contest.images.testService;

import org.springframework.web.multipart.MultipartFile;

public interface TestImageService {
    public String combineUrl(String repoName, Long objectId, String filename);

    public String uploadClothesImageToS3AndGetUrl(MultipartFile Image, String repoName, Long objectId);

    public String uploadPersonImageToS3AndGetUrl(MultipartFile Image, String repoName);
}