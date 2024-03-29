package com.cts.pension.disbursement.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ExceptionDetails> handleAuthorizationException(AuthorizationException ex) {
        ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.FORBIDDEN, ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionDetail, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ExceptionDetails> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AadharNumberNotFound.class)
    public ResponseEntity<ExceptionDetails> handleMissingRequestHeaderException(AadharNumberNotFound ex) {
        ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionDetails> handleFeignStatusException(FeignException ex, HttpServletResponse response) {
        log.error("handles by handleFeignStatusException");
        ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionDetail, HttpStatus.BAD_REQUEST);
    }
}
