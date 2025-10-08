package com.dulfinne.randomgame.transactionservice.mapper;

import com.dulfinne.randomgame.transactionservice.dto.response.TransactionResponse;
import com.dulfinne.randomgame.transactionservice.entity.Transaction;
import com.dulfinne.randomgame.transactionservice.entity.TransactionType;
import com.dulfinne.randomgame.transactionservice.grpc.TransactionProto;
import com.dulfinne.randomgame.transactionservice.util.CommonConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimeZone;

@Component
public class TransactionMapper {
  public TransactionResponse toResponse(Transaction entity) {
    String timezone = resolveTimeZone();
    String formattedDate = entity.getDate()
                                 .atZone(ZoneId.of(timezone))
                                 .format(DateTimeFormatter.ofPattern(CommonConstants.PATTERN_DATE));

    return TransactionResponse.builder()
                              .id(entity.getId())
                              .username(entity.getUsername())
                              .amount(entity.getAmount())
                              .date(formattedDate)
                              .type(entity.getType())
                              .build();
  }

  public Transaction fromGrpc(TransactionProto.Transaction request) {
    return Transaction.builder()
                      .username(request.getUsername())
                      .amount(new BigDecimal(request.getAmount()))
                      .type(
                          TransactionType.valueOf(request.getType()))
                      .build();
  }

  private String resolveTimeZone() {
    return Objects.requireNonNullElse(
                      RequestContextHolder
                          .currentRequestAttributes()
                          .getAttribute(CommonConstants.TIMEZONE_FIELD,
                                        RequestAttributes.SCOPE_REQUEST),
                      TimeZone.getDefault()
                              .toZoneId())
                  .toString();
  }
}
