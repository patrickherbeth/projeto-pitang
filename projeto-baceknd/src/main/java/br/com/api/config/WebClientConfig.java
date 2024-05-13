package br.com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  // Memory size in Megabytes for WebClient
  private final int MAX_IN_MEMORY_SIZE = 10 * 1024 * 1024;

  @Bean
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder().exchangeStrategies(
        ExchangeStrategies.builder().codecs(
            configurer -> configurer
                .defaultCodecs()
                .maxInMemorySize(MAX_IN_MEMORY_SIZE))
            .build());
  }
}
