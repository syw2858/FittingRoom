package net.nwrn.pf_contest.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HomeModelDTO {
    private final String userId;
    private final String password;
    private final String id;
}
