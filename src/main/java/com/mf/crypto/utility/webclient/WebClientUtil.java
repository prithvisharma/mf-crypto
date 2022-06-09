package com.mf.crypto.utility.webclient;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.mf.crypto.constant.CustomError;
import com.mf.crypto.exception.ServiceException;
import com.mf.crypto.utility.json.JsonUtil;

import reactor.core.publisher.Mono;

@Component
public class WebClientUtil {

    @Autowired
    private WebClient webClient;

    @Autowired
    private JsonUtil jsonUtil;

    private final Logger logger = LoggerFactory.getLogger(WebClientUtil.class);

    public <T> ResponseEntity<T> get(String uri, Class<T> responseClass) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class)
                        .flatMap(body -> {
                            logger.error("Endpoint: " + uri);
                            logger.error("(ERROR) Response Body: {}", body);
                            return Mono.error(new ServiceException(CustomError.VENDOR_API_FAILURE));
                        }))
                .toEntity(responseClass)
                .block();
    }

    public <T, U> ResponseEntity<U> get(String uri, HttpHeaders httpHeaders, Class<U> responseClass) {
        return webClient.get()
                .uri(uri)
                .headers(headers -> {
                    headers.addAll(httpHeaders);
                })
                .retrieve()
                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class)
                        .flatMap(body -> {
                            logger.error("Endpoint: " + uri);
                            logger.error("(ERROR) Response Body: {}", body);
                            return Mono.error(new ServiceException(CustomError.VENDOR_API_FAILURE));
                        }))
                .toEntity(responseClass)
                .block();
    }

    public <T, U> ResponseEntity<U> post(String uri, T requestBody, Class<U> responseClass) {
        return webClient.post()
                .uri(uri)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class)
                        .flatMap(body -> {
                            logger.error("(ERROR) Endpoint: " + uri);
                            logger.error("(ERROR) Request Body: {}", jsonUtil.jsonStringNotPretty(requestBody));
                            logger.error("(ERROR) Response Body: {}", body);
                            return Mono.error(new ServiceException(CustomError.VENDOR_API_FAILURE));
                        }))
                .toEntity(responseClass)
                .block();
    }

    // post with timeout
    public <T, U> ResponseEntity<U> post(String uri, T requestBody,
            Class<U> responseClass, long timeoutInSeconds) {
        return webClient.post()
                .uri(uri)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class)
                        .flatMap(body -> {
                            logger.error("Endpoint: " + uri);
                            logger.error("Request Body: {}", jsonUtil.jsonStringNotPretty(requestBody));
                            logger.error("(ERROR) Response Body: {}", body);
                            return Mono.error(new ServiceException(CustomError.VENDOR_API_FAILURE));
                        }))
                .toEntity(responseClass)
                .timeout(Duration.ofSeconds(timeoutInSeconds))
                .block();
    }

    public <T> ResponseEntity<Void> post(String uri, T requestBody) {
        return webClient.post()
                .uri(uri)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class)
                        .flatMap(body -> {
                            logger.error("Endpoint: " + uri);
                            logger.error("Request Body: {}", jsonUtil.jsonStringNotPretty(requestBody));
                            logger.error("(ERROR) Response Body: {}", body);
                            return Mono.error(new ServiceException(CustomError.VENDOR_API_FAILURE));
                        }))
                .toBodilessEntity()
                .block();
    }

    public <T, U> ResponseEntity<U> post(String uri, HttpHeaders httpHeaders, T requestBody, Class<U> responseClass) {
        return webClient.post()
                .uri(uri)
                .headers(headers -> {
                    headers.addAll(httpHeaders);
                })
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class)
                        .flatMap(body -> {
                            logger.error("Endpoint: " + uri);
                            logger.error("Request Body: {}", jsonUtil.jsonStringNotPretty(requestBody));
                            logger.error("(ERROR) Response Body: {}", body);
                            return Mono.error(new ServiceException(CustomError.VENDOR_API_FAILURE));
                        }))
                .toEntity(responseClass)
                .block();
    }
}
