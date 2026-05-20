package com.dulfinne.randomgame.transactionservice.dto.request;

import jakarta.validation.constraints.Positive;

public record ParallelRequest(
  @Positive
  Long firstDelay,

  @Positive
  Long secondDelay
) {
}
