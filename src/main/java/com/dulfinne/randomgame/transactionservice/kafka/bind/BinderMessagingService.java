package com.dulfinne.randomgame.transactionservice.kafka.bind;

import com.dulfinne.randomgame.transactionservice.entity.Transaction;
import com.dulfinne.randomgame.transactionservice.kafka.entity.TransactionMessage;
import com.dulfinne.randomgame.transactionservice.mapper.TransactionMapper;
import com.dulfinne.randomgame.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.ErrorMessage;

import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BinderMessagingService {
  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @Bean
  public Consumer<TransactionMessage> receiveTransactionMessage() {
    return value -> {
      log.info("Received transaction message from BINDER: {}", value);
      Transaction transaction = transactionMapper.fromTransactionMessage(value);
      transactionService.saveTransaction(transaction);
    };
  }

  @Bean
  public Consumer<ErrorMessage> myErrorHandler() {
    return value ->
        log.error("Kafka binder error: failed to process message {}", value.getOriginalMessage());
  }
}
