package com.dulfinne.randomgame.transactionservice.dto.request;

import com.dulfinne.randomgame.transactionservice.entity.TransactionType;

import java.math.BigDecimal;

public record FilterRequest(
    String username,
    BigDecimal amount,
    TransactionType type
) {
}
