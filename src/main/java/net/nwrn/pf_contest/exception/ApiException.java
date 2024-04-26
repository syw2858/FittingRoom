package net.nwrn.pf_contest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
        this("서버 에러");
    }
}
