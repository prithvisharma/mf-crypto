package com.mf.crypto.config.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("webclient")
    public WebClient buildWebClient(WebClient.Builder webClientBuilder) {
        return WebClient.builder().build();
    }

}
