package com.dulfinne.randomgame.transactionservice.kafka.entity;

import com.dulfinne.randomgame.transactionservice.entity.TransactionType;

import java.math.BigDecimal;

public record TransactionMessage(
    String username,
    BigDecimal amount,
    TransactionType type
) {
}
