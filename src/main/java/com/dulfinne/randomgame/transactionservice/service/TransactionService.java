package com.dulfinne.randomgame.transactionservice.service;

import com.dulfinne.randomgame.transactionservice.dto.request.FilterRequest;
import com.dulfinne.randomgame.transactionservice.dto.response.TransactionResponse;
import com.dulfinne.randomgame.transactionservice.entity.Transaction;

import java.util.List;

public interface TransactionService {
  List<TransactionResponse> getFilteredTransactions(
      FilterRequest filters,
      Long lastSeenId,
      Integer limit
  );

  List<TransactionResponse> getTransactionsKeyset(Long lastSeenId, Integer limit);

  List<TransactionResponse> getTransactionsOffset(Integer offset, Integer limit);

  TransactionResponse getTransactionById(Long id);

  void saveTransaction(Transaction transaction);
}
