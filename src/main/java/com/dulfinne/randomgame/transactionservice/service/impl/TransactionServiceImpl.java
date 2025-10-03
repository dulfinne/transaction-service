package com.dulfinne.randomgame.transactionservice.service.impl;

import com.dulfinne.randomgame.transactionservice.dto.request.FilterRequest;
import com.dulfinne.randomgame.transactionservice.dto.response.TransactionResponse;
import com.dulfinne.randomgame.transactionservice.entity.Transaction;
import com.dulfinne.randomgame.transactionservice.mapper.TransactionMapper;
import com.dulfinne.randomgame.transactionservice.repository.TransactionRepository;
import com.dulfinne.randomgame.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  @Override
  @Transactional(readOnly = true)
  public List<TransactionResponse> getFilteredTransactions(
      FilterRequest filters,
      Long lastSeenId,
      Integer limit
  ) {
    List<Transaction> transactions =
        transactionRepository.findTransactionsWithFilters(filters, lastSeenId, limit);
    return transactions.stream()
                       .map(transactionMapper::toResponse)
                       .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<TransactionResponse> getTransactionsKeyset(
      Long lastSeenId,
      Integer limit
  ) {
    List<Transaction> transactions = transactionRepository.findAllTransactionsKeyset(lastSeenId, limit);
    return transactions.stream()
                       .map(transactionMapper::toResponse)
                       .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<TransactionResponse> getTransactionsOffset(
      Integer offset,
      Integer limit
  ) {
    List<Transaction> transactions = transactionRepository.findAllTransactionsOffset(offset, limit);
    return transactions.stream()
                       .map(transactionMapper::toResponse)
                       .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public TransactionResponse getTransactionById(Long id) {
    Transaction transaction = transactionRepository.findTransactionById(id);
    return transactionMapper.toResponse(transaction);
  }

  @Override
  @Transactional
  public void saveTransaction(Transaction transaction) {
    transactionRepository.saveTransaction(transaction);
  }
}
