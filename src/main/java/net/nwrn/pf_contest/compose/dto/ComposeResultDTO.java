package net.nwrn.pf_contest.compose.dto;

import lombok.*;
import net.nwrn.pf_contest.origin_images.dto.SelectedDataSetDTO;
import net.nwrn.pf_contest.users.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ComposeResultDTO {

    Long composeResultSn;
    String composedResultImageUrl;
    UserDTO userDTO;
    SelectedDataSetDTO selectedDataSetDTO;

}
