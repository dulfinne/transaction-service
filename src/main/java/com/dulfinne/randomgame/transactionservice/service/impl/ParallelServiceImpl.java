package com.dulfinne.randomgame.transactionservice.service.impl;

import com.dulfinne.randomgame.transactionservice.dto.request.ParallelRequest;
import com.dulfinne.randomgame.transactionservice.service.ParallelService;
import com.dulfinne.singleflightstarter.annotation.SingleFlight;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ParallelServiceImpl implements ParallelService {

  private final Executor virtualExecutor =
      Executors.newThreadPerTaskExecutor(Thread.ofVirtual()
                                               .factory());

  @Override
  public String getParallelNonBlocking(ParallelRequest request) throws ExecutionException, InterruptedException {
    CompletableFuture<String> task1 =
        CompletableFuture.supplyAsync(
            () -> "Result 1",
            CompletableFuture.delayedExecutor(request.firstDelay(), TimeUnit.MILLISECONDS));

    CompletableFuture<String> task2 =
        CompletableFuture.supplyAsync(
            () -> "Result 2",
            CompletableFuture.delayedExecutor(request.secondDelay(), TimeUnit.MILLISECONDS));

    return String.format("Results: %s, %s", task1.get(), task2.get());
  }

  @Override
  @SingleFlight(key = "'blocking:delays:' + #request.firstDelay + ':' + #request.secondDelay")
  public String getParallelBlocking(ParallelRequest request) throws ExecutionException, InterruptedException {
    CompletableFuture<String> task1 =
        CompletableFuture.supplyAsync(
            () -> {
              try {
                Thread.sleep(request.firstDelay());
              } catch (InterruptedException e) {
                Thread.currentThread()
                      .interrupt();
              }
              return "Result 1";
            },
            virtualExecutor);

    CompletableFuture<String> task2 =
        CompletableFuture.supplyAsync(
            () -> {
              try {
                Thread.sleep(request.secondDelay());
              } catch (InterruptedException e) {
                Thread.currentThread()
                      .interrupt();
              }
              return "Result 2";
            },
            virtualExecutor);
    return String.format("Results: %s, %s", task1.get(), task2.get());
  }
}
