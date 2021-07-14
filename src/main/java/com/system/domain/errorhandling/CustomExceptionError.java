package com.system.domain.errorhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionError extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        String error = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Requisição inválida", error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("Erro", ex);
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "ocorreu um erro interno");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


}
