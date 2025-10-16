package com.dulfinne.randomgame.transactionservice.service;

import com.dulfinne.randomgame.transactionservice.dto.request.ParallelRequest;

import java.util.concurrent.ExecutionException;

public interface ParallelService {
  String getParallelNonBlocking(ParallelRequest request) throws ExecutionException, InterruptedException;

  String getParallelBlocking(ParallelRequest request) throws ExecutionException, InterruptedException;
}
