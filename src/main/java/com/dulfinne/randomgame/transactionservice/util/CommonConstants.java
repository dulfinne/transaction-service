package com.dulfinne.randomgame.transactionservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonConstants {
  public static final String HEADER_TIMEZONE = "X-Timezone";
  public static final String TIMEZONE_FIELD = "timezone";

  public static final String PATTERN_DATE = "dd-MM-yyyy HH:mm:ss";
  public static final String MAX_INT_STRING = Integer.MAX_VALUE + "";
}
