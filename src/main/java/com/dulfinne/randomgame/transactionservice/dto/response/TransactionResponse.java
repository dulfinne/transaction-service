package com.dulfinne.randomgame.transactionservice.dto.response;

import com.dulfinne.randomgame.transactionservice.entity.TransactionType;
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
