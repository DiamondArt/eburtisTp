package com.eburtis.tp.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.naming.NamingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@ControllerAdvice
public class CustomRestExceptionHandler extends Throwable {

    @Nullable
    @ResponseBody
    @ExceptionHandler(value = {
            NotFoundException.class, Exception.class, NamingException.class,
            MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class,
            MethodArgumentTypeMismatchException.class, UnknownHostException.class, BindException.class, HttpClientErrorException.class,

            NoSuchElementException.class, NullPointerException.class,

    })
    public final ResponseEntity<ApiException> handleException(Exception ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        getLogger().error("Handling {} due to {}", ex.getClass().getSimpleName(), ex.getMessage());

        if (ex instanceof NotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NotFoundException nfe = (NotFoundException) ex;

            return handleNotFoundException(nfe, headers, status, request);
        } else if (ex instanceof NoSuchElementException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NoSuchElementException nee = (NoSuchElementException) ex;

            return handleException(nee, headers, status, request);
        }  else if (ex instanceof HttpClientErrorException) {
            HttpClientErrorException hcee = (HttpClientErrorException) ex;

            return handleHttpClientErrorException(hcee, headers, request);
        }  else if (ex instanceof NullPointerException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NullPointerException npe = (NullPointerException) ex;
            Exception exception = new Exception(npe.getMessage() == null ? "NullPointerException" : npe.getMessage());

            return handleException(exception, headers, status, request);
        } else if (ex instanceof NamingException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NamingException ne = (NamingException) ex;

            return handleException(ne, headers, status, request);
        }  else if (ex instanceof ServletException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ServletException se = (ServletException) ex;

            return handleException(se, headers, status, request);
        } else {
            if (getLogger().isWarnEnabled()) {
                getLogger().warn("Unknown exception type :: {}", ex.getClass().getSimpleName());
            }

            HttpStatus status = HttpStatus.NOT_FOUND;
            ApiException apiError = new ApiException(status, request.getServletPath(), Collections.singletonList(ex.getMessage()));
            return handleExceptionInternal(ex, apiError, headers, status, request);
        }
    }

    protected ResponseEntity<ApiException> handleHttpClientErrorException(HttpClientErrorException ex, HttpHeaders headers, HttpServletRequest request) {
        List<String> reasons = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex,
                new ApiException((HttpStatus) ex.getStatusCode(), request.getServletPath(), reasons), headers, HttpStatus.valueOf(ex.getStatusCode().value()), request);
    }

    protected ResponseEntity<ApiException> handleException(Exception ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        List<String> reasons = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiException(status, request.getServletPath(), reasons), headers, status, request);
    }


    protected ResponseEntity<ApiException> handleNotFoundException(NotFoundException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        List<String> reasons = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiException(status, request.getServletPath(), reasons), headers, status, request);
    }

    protected ResponseEntity<ApiException> handleExceptionInternal(Exception ex, @Nullable ApiException apiException, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex);
        }
        return new ResponseEntity<>(apiException, headers, status);
    }

    private org.slf4j.Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(this.getClass().getSimpleName());
    }
}