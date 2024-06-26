package net.nwrn.pf_contest.compose.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.clothes.entity.BottomEntity;
import net.nwrn.pf_contest.clothes.entity.TopEntity;
import net.nwrn.pf_contest.clothes.repository.BottomRepository;
import net.nwrn.pf_contest.clothes.repository.TopRepository;
import net.nwrn.pf_contest.compose.dto.res.ComposeBottomResponseDTO;
import net.nwrn.pf_contest.compose.dto.res.ComposeTopResponseDTO;
import net.nwrn.pf_contest.exception.ApiException;
import net.nwrn.pf_contest.images.entity.ImageEntity;
import net.nwrn.pf_contest.images.repository.ImageRepository;
import net.nwrn.pf_contest.images.service.ImageService;
import net.nwrn.pf_contest.person.repository.PersonRepository;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    private final TopRepository topRepository;
    private final BottomRepository bottomRepository;
    private final PersonRepository personRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final WebClient webClient;

    @Value("${properties.awsBucketName}")
    private String awsBucketName;

    @Value("${properties.cloudfrontDomain}")
    private String cloudfrontDomain;

    @Value("${properties.defaultImageUrl}")
    private String defaultImageUrl;

    @Override
    public String uploadPerson(MultipartFile personImage) {

        String personImageUrl = imageService.uploadPersonImageToS3AndGetUrl(personImage, "person");

//        System.out.println(personImage.getSize());
//        return "https://d1hds1xxjs6al7.cloudfront.net/test/default.jpeg";

        return personImageUrl;
    }

    @Override
    public String uploadTop(MultipartFile topImage) {
        String topImageUrl = imageService.uploadTopImageToS3AndGetUrl(topImage);

        return topImageUrl;
    }


    @Override
    public String uploadBottom(MultipartFile bottomImage) {
        String bottomImageUrl = imageService.uploadBottomImageToS3AndGetUrl(bottomImage);

        return bottomImageUrl;
    }

    private String composeInner (String personImageUrl, String topImageUrl, String bottomImageUrl) {
        if (personImageUrl == null)
            throw new ApiException("사람 이미지가 없습니다.");
        if (topImageUrl == null && bottomImageUrl == null)
            throw new ApiException("옷 이미지가 아무것도 없습니다.");

        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost:4000")
                    .setPath("/fitting")
                    .build();

            String requestBody = "{\"my_image\": \"" + personImageUrl + "\", \"upper_clothes\": \"" + topImageUrl + "\", \"lower_clothes\": \"" + bottomImageUrl + "\"}";

            byte[] response = webClient.post().uri(uri)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve().bodyToMono(byte[].class).block();

           return imageService.uploadComposeImageToS3AndGetUrl(response);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String compose(Boolean isSample, String personImageUrl, String topImageUrl, String bottomImageUrl, String sampleTopImageUrl, String sampleBottomImageUrl) {

        if (isSample) {
            return composeInner(personImageUrl, sampleTopImageUrl, sampleBottomImageUrl);
        } else {
            return composeInner(personImageUrl, topImageUrl, bottomImageUrl);
        }

    }

    public void getComposeImageUrl() {
        /*
        https://simdaback.nwrn.net:443/api/banner/all/list
         */

        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("simdaback.nwrn.net:443")
                    .setPath("/api/banner/all/list")
                    .build();

            List<?> list = webClient.get().uri(uri).retrieve().bodyToMono(List.class).block();

            Map<?, ?> map = (Map<?, ?>) list.get(0);

            String bannerType = (String) map.get("bannerType");

            System.err.println("codingdochi1234" + bannerType);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    public List<ComposeTopResponseDTO> getTopList() {
        List<TopEntity> topEntityList = topRepository.findAll();
        List<ComposeTopResponseDTO> topContentList = new ArrayList<>();

        for (TopEntity topEntity : topEntityList) {
            Long topId = topEntity.getTopId();
            List<ImageEntity> imageEntityList = imageRepository.findByRepoNameAndObjectId("top", topId);
            String topUrl;
            Timestamp topRegisterDt = topEntity.getTopRegisterDt();

            if (imageEntityList.isEmpty()) {
                topUrl = defaultImageUrl;
            }
            else {
                ImageEntity imageEntity = imageEntityList.get(0);
                String repoName = imageEntity.getRepoName();
                Long objectId = imageEntity.getObjectId();
                String fileName = imageEntity.getFileName();
                topUrl = imageService.combineUrl(repoName, objectId, fileName);
            }

            ComposeTopResponseDTO topResponseDTO = new ComposeTopResponseDTO();
            topResponseDTO.setTopId(topId);
            topResponseDTO.setTopImageUrl(topUrl);
            topResponseDTO.setTopRegisterDt(topRegisterDt);

            topContentList.add(topResponseDTO);
        }

        Collections.sort(topContentList, new Comparator<ComposeTopResponseDTO>() {

            @Override
            public int compare(ComposeTopResponseDTO t0, ComposeTopResponseDTO t1) {
                return t1.getTopRegisterDt().compareTo(t0.getTopRegisterDt());
            }
        });

        if (topContentList.size() >= 12) {
            List<ComposeTopResponseDTO> top12 = topContentList.subList(0, 12);
            return top12;
        } else {
            return topContentList;
        }
    }

    public List<ComposeBottomResponseDTO> getBottomList() {
        List<BottomEntity> bottomEntityList = bottomRepository.findAll();
        List<ComposeBottomResponseDTO> bottomContentList = new ArrayList<>();

        for (BottomEntity bottomEntity : bottomEntityList) {
            Long bottomId = bottomEntity.getBottomId();
            List<ImageEntity> imageEntityList = imageRepository.findByRepoNameAndObjectId("bottom", bottomId);
            String bottomUrl;
            Timestamp bottomRegisterDt = bottomEntity.getBottomRegisterDt();

            if (imageEntityList.isEmpty()) {
                bottomUrl = defaultImageUrl;
            }
            else {
                ImageEntity imageEntity = imageEntityList.get(0);
                String repoName = imageEntity.getRepoName();
                Long objectId = imageEntity.getObjectId();
                String fileName = imageEntity.getFileName();
                bottomUrl = imageService.combineUrl(repoName, objectId, fileName);
            }

            ComposeBottomResponseDTO bottomResponseDTO = new ComposeBottomResponseDTO();
            bottomResponseDTO.setBottomId(bottomId);
            bottomResponseDTO.setBottomImageUrl(bottomUrl);
            bottomResponseDTO.setBottomRegisterDt(bottomRegisterDt);

            bottomContentList.add(bottomResponseDTO);
        }

        Collections.sort(bottomContentList, new Comparator<ComposeBottomResponseDTO>() {

            @Override
            public int compare(ComposeBottomResponseDTO t0, ComposeBottomResponseDTO t1) {
                return t1.getBottomRegisterDt().compareTo(t0.getBottomRegisterDt());
            }
        });

        if (bottomContentList.size() >= 12) {
            List<ComposeBottomResponseDTO> bottom12 = bottomContentList.subList(0, 12);
            return bottom12;
        } else {
            return bottomContentList;
        }
    }

}
