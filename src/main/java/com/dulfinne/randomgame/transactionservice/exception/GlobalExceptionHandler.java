package com.dulfinne.randomgame.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handleException(Exception ex) {
    return ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(ex.getMessage())
                        .build();
  }
}
