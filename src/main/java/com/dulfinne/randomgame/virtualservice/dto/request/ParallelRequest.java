package com.dulfinne.randomgame.virtualservice.dto.request;

import jakarta.validation.constraints.Positive;

public record ParallelRequest(
  @Positive
  Long firstDelay,

  @Positive
  Long secondDelay
) {
}
