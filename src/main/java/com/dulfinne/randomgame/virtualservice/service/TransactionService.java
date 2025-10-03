package com.dulfinne.randomgame.virtualservice.service;

import com.dulfinne.randomgame.virtualservice.dto.request.FilterRequest;
import com.dulfinne.randomgame.virtualservice.dto.response.TransactionResponse;
import com.dulfinne.randomgame.virtualservice.entity.Transaction;

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
