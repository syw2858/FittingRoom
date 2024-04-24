package net.nwrn.pf_contest.exception;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
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

    @ExceptionHandler(ApiException.class)
    public ModelAndView customExceptionHandler(ApiException exception) {
        ModelAndView modelAndView = new ModelAndView(exception.getUrl());
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }


}
