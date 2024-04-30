package net.nwrn.pf_contest.compose.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nwrn.pf_contest.compose.dto.ComposeResultDTO;
import net.nwrn.pf_contest.origin_images.dto.PersonImageDTO;
import net.nwrn.pf_contest.origin_images.dto.SelectedDataSetDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComposeServiceImpl implements ComposeService {

    @Override
    public Page<PersonImageDTO> getPersonImageList(Long userSn, Integer page, Integer size) {
        return null;
    }

    @Override
    public PersonImageDTO getPersonImage(Long userSn, Long personImageSn) {
        return null;
    }

    @Override
    public SelectedDataSetDTO setPersonImage(Long userSn, MultipartFile personImage) {
        return null;
    }

    @Override
    public String executeCompose(Long userSn, MultipartFile personImage, MultipartFile topImage, MultipartFile bottomImage) {
        return "";
    }

    @Override
    public ComposeResultDTO getComposeResult(Long userSn, Long composeResultSn) {
        return null;
    }
}
