package com.mf.crypto.vendor.m2p.apiintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.mf.crypto.constant.CustomError;
import com.mf.crypto.constant.EnvConstant;
import com.mf.crypto.exception.ServiceException;
import com.mf.crypto.utility.json.JsonUtil;
import com.mf.crypto.utility.webclient.WebClientUtil;

public class BinanceApi {

    protected final String BASE_URL = EnvConstant.BINANCE_BASE_URL;

    private final Logger logger = LoggerFactory.getLogger(BinanceApi.class);

    @Autowired
    private WebClientUtil webClientUtil;

    @Autowired
    protected JsonUtil jsonUtil;

    private void logRequest(String url, JsonNode responseJsonNode) {
        if (EnvConstant.SERVICE_ENV.equalsIgnoreCase("dev")) {
            logger.info("Request Url: " + url);
            logger.info("Response Body: " + jsonUtil.jsonStringNotPretty(responseJsonNode));
        }
    }

    protected JsonNode get(String url) throws ServiceException {
        final ResponseEntity<JsonNode> responseEntity = webClientUtil.get(url, JsonNode.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ServiceException(CustomError.VENDOR_API_FAILURE);
        }
        final JsonNode responseJsonNode = responseEntity.getBody();
        logRequest(url, responseJsonNode);
        return responseJsonNode;
    }
}
