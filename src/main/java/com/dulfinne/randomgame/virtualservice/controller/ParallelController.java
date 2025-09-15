package com.dulfinne.randomgame.virtualservice.controller;

import com.dulfinne.randomgame.virtualservice.dto.request.ParallelRequest;
import com.dulfinne.randomgame.virtualservice.util.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(ApiPaths.PARALLEL_BASE_URL)
public class ParallelController {

  private final Executor virtualExecutor =
      Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory());

  @GetMapping
  public String getParallelNonBlocking(@Valid ParallelRequest request)
      throws ExecutionException, InterruptedException {
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

  @GetMapping(ApiPaths.BLOCKING)
  public String getParallelBlocking(@Valid ParallelRequest request)
      throws ExecutionException, InterruptedException {
    CompletableFuture<String> task1 =
        CompletableFuture.supplyAsync(
            () -> {
              try {
                Thread.sleep(request.firstDelay());
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
                Thread.currentThread().interrupt();
              }
              return "Result 2";
            },
            virtualExecutor);

    return String.format("Results: %s, %s", task1.get(), task2.get());
  }
}
