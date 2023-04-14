package com.eburtis.tp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorVo> handleException(EntityNotFoundException exception, WebRequest webRequest) {

    final HttpStatus notFound = HttpStatus.NOT_FOUND;
    final ErrorVo errorDto = ErrorVo.builder().timestamp(new Date())
        .code(exception.getErrorCode())
        .httpCode(notFound.value())
        .message(exception.getMessage()).path(webRequest.getContextPath())
        .build();

    return new ResponseEntity<>(errorDto, notFound);
  }

  @ExceptionHandler(InvalidOperationException.class)
  public ResponseEntity<ErrorVo> handleException(InvalidOperationException exception, WebRequest webRequest) {

    final HttpStatus notFound = HttpStatus.BAD_REQUEST;
    final ErrorVo errorDto = ErrorVo.builder().timestamp(new Date())
        .code(exception.getErrorCode())
        .httpCode(notFound.value())
            .message(exception.getMessage()).path(webRequest.getContextPath())
        .build();

    return new ResponseEntity<>(errorDto, notFound);
  }

  @ExceptionHandler(InvalidEntityException.class)
  public ResponseEntity<ErrorVo> handleException(InvalidEntityException exception, WebRequest webRequest) {
    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    final ErrorVo errorDto = ErrorVo.builder().timestamp(new Date())
        .code(exception.getErrorCode())
        .httpCode(badRequest.value())
            .message(exception.getMessage())
            .path(webRequest.getContextPath())
        .errors(exception.getErrors())
        .build();

    return new ResponseEntity<>(errorDto, badRequest);
  }
}
