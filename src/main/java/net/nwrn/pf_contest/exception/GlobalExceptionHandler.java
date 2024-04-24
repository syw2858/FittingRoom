package net.nwrn.pf_contest.exception;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionService exceptionService;
    private final EntityManager entityManager;

    public GlobalExceptionHandler(ExceptionService exceptionService, EntityManager entityManager) {
        this.exceptionService = exceptionService;
        this.entityManager = entityManager;
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResException resException = new ResException(true, "["+ex.getRequestPartName()+"]", null);
        return new ResponseEntity<>(resException, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResException resException = new ResException(true, "["+ex.getParameterName()+"]", null);

        return new ResponseEntity<>(resException, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            errorMessage.append("[");
            for (FieldError fieldError : fieldErrorList) {
                errorMessage.append(fieldError.getField()).append(", ");
            }
            if (errorMessage.length() > 2) {
                errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            }
            errorMessage.append("]");
        }

        ResException resException = new ResException(true, errorMessage.toString(), null);
        return new ResponseEntity<>(resException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResException> customExceptionHandler(ApiException exception) {
        ResException resException = new ResException(false, null, exception.getMessage());
        return new ResponseEntity<> (resException, exception.getStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResException> customExceptionHandler(Exception exception) {
        log.error(exceptionService.generateMessage(), exception);
        ResException resException = new ResException(false, null, "백엔드에서 예측하지 못한 서버 에러가 발생했습니다.");

        return new ResponseEntity<>(resException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
