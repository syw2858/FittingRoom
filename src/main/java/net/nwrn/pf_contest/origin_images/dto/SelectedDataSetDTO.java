package net.nwrn.pf_contest.origin_images.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class SelectedDataSetDTO {
    private Long selectedDataSetSn;
    private PersonImageDTO personImageDTO;
    private TopImageDTO topImageDTO;
    private BottomImageDTO bottomImageDTO;

}
