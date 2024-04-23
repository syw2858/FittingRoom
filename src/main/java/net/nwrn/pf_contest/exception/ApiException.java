package net.nwrn.pf_contest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ApiException() {
        this("서버 에러입니다");
    }

    public ApiException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
