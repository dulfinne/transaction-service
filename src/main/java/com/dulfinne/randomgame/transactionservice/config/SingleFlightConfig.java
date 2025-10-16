package com.dulfinne.randomgame.transactionservice.config;

import com.dulfinne.singleflightstarter.aop.SingleFlightAspect;
import com.dulfinne.singleflightstarter.support.SpelKeyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class SingleFlightConfig {
  @Bean
  public SingleFlightAspect singleFlightAspect(SpelKeyResolver resolver) {
    return new SingleFlightAspect(resolver, Executors.newThreadPerTaskExecutor(Thread.ofVirtual()
                                                                                     .factory()));
  }
}
