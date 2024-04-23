package net.nwrn.pf_contest.exception;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ExceptionService exceptionService;
    private final EntityManager entityManager;

    public GlobalExceptionHandler(ExceptionService exceptionService, EntityManager entityManager) {
        this.exceptionService = exceptionService;
        this.entityManager = entityManager;
    }

    // api exception, api exception, back:알수 없는 에러 (로그+ 로그 죽이는 시점에)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResException> customExceptionHandler(ApiException exception) {
        ResException resException = new ResException(exception.getMessage());
        return new ResponseEntity<> (resException, exception.getStatus());
    }

    // try+catch e 메시지 노출은 안하고 Runtime 로그 남기기)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResException> customExceptionHandler(Exception exception) {
        log.error(exceptionService.generateMessage(), exception);
        ResException resException = new ResException("백엔드에서 예측하지 못한 서버 에러가 발생했습니다.");

        return new ResponseEntity<>(resException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
