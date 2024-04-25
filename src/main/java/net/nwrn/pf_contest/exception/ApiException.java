package net.nwrn.pf_contest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String url;


    public ApiException(String url, String message) {
        super(message);
        this.url = url;

    }

    public ApiException() {
        this("서버 에러");
    }

    public ApiException(String message) {
        this ("error", message);
    }

}
