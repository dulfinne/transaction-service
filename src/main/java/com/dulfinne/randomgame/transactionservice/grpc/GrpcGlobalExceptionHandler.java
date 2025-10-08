package com.dulfinne.randomgame.transactionservice.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcGlobalExceptionHandler {

  @GrpcExceptionHandler(Exception.class)
  public StatusRuntimeException handleGenericException(Exception ex) {
    return Status.INTERNAL
        .withDescription(ex.getMessage())
        .asRuntimeException();
  }
}
