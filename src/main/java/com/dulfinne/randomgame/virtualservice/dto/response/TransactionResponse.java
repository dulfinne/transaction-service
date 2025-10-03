package com.dulfinne.randomgame.virtualservice.dto.response;

import com.dulfinne.randomgame.virtualservice.entity.TransactionType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionResponse(
    Long id,
    String username,
    BigDecimal amount,
    String description,
    String date,
    TransactionType type
) {
}
