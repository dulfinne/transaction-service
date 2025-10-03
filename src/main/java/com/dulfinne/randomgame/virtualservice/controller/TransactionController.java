package com.dulfinne.randomgame.virtualservice.controller;

import com.dulfinne.randomgame.virtualservice.dto.request.FilterRequest;
import com.dulfinne.randomgame.virtualservice.dto.response.TransactionResponse;
import com.dulfinne.randomgame.virtualservice.service.TransactionService;
import com.dulfinne.randomgame.virtualservice.util.ApiPaths;
import com.dulfinne.randomgame.virtualservice.util.CommonConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.TRANSACTION_BASE_URL)
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @GetMapping
  public List<TransactionResponse> getFilteredTransactionsOffset(
      FilterRequest filters,
      @RequestParam(name = "from", defaultValue = CommonConstants.MAX_INT_STRING) Long lastSeenId,
      @RequestParam(defaultValue = "10") Integer limit
  ) {
    return transactionService.getFilteredTransactions(filters, lastSeenId, limit);
  }

  @GetMapping(ApiPaths.KEYSET)
  public List<TransactionResponse> getTransactionsKeyset(
      @RequestParam(name = "from", defaultValue = CommonConstants.MAX_INT_STRING) Long lastSeenId,
      @RequestParam(defaultValue = "10") Integer limit
  ) {
    return transactionService.getTransactionsKeyset(lastSeenId, limit);
  }

  @GetMapping(ApiPaths.OFFSET)
  public List<TransactionResponse> getTransactionsOffset(
      @RequestParam(defaultValue = "0") Integer offset,
      @RequestParam(defaultValue = "10") Integer limit
  ) {
    return transactionService.getTransactionsOffset(offset, limit);
  }

  @GetMapping(ApiPaths.ID)
  public TransactionResponse getTransaction(@PathVariable Long id) {
    return transactionService.getTransactionById(id);
  }
}
