package com.dulfinne.randomgame.transactionservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiPaths {
  public static final String PARALLEL_BASE_URL = "/api/v1/parallel";
  public static final String TRANSACTION_BASE_URL = "/api/v1/transactions";

  public static final String BLOCKING = "/blocking";
  public static final String ID = "/{id}";
  public static final String KEYSET = "/keyset";
  public static final String OFFSET = "/offset";
}
