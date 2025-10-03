package com.dulfinne.randomgame.virtualservice.dto.request;

import com.dulfinne.randomgame.virtualservice.entity.TransactionType;

import java.math.BigDecimal;

public record FilterRequest(
    String username,
    BigDecimal amount,
    TransactionType type
) {
}
