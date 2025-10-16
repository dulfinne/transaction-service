package com.dulfinne.randomgame.transactionservice.controller;

import com.dulfinne.randomgame.transactionservice.dto.request.ParallelRequest;
import com.dulfinne.randomgame.transactionservice.service.ParallelService;
import com.dulfinne.randomgame.transactionservice.util.ApiPaths;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(ApiPaths.PARALLEL_BASE_URL)
@RequiredArgsConstructor
public class ParallelController {

  private final ParallelService parallelService;

  @GetMapping
  public String getParallelNonBlocking(@Valid ParallelRequest request)
      throws ExecutionException, InterruptedException {
    return parallelService.getParallelNonBlocking(request);
  }

  @GetMapping(ApiPaths.BLOCKING)
  public String getParallelBlocking(@Valid ParallelRequest request)
      throws ExecutionException, InterruptedException {
    return parallelService.getParallelBlocking(request);
  }
}
