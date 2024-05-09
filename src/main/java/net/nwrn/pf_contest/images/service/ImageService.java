package net.nwrn.pf_contest.images.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String combineUrl(String repoName, Long objectId, String filename);

    public String uploadTopImageToS3AndGetUrl(MultipartFile Image);

    public String uploadBottomImageToS3AndGetUrl(MultipartFile Image);

    public String uploadPersonImageToS3AndGetUrl(MultipartFile Image, String repoName);
}
