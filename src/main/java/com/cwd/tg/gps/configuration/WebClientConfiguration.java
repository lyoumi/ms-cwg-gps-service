package com.cwd.tg.gps.configuration;

import com.cwd.tg.gps.filters.OutgoingRequestResponseLoggingFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient(OutgoingRequestResponseLoggingFilter loggingFilter) {
        return WebClient.builder().filter(loggingFilter).build();
    }
}
