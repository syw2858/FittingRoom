package net.nwrn.pf_contest.compose.dto.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComposeTopResponseDTO {
    private Long topId;
    private String topImageUrl;
    @JsonSerialize(using= ToStringSerializer.class)
    private Timestamp topRegisterDt;
}
