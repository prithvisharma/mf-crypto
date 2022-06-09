package com.mf.crypto.vendor.m2p.apiintegration;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.mf.crypto.constant.SupportedSymbolEnum;
import com.mf.crypto.exception.ServiceException;
import com.mf.crypto.vendor.m2p.constants.BinanceApiEndpointsEnum;
import com.mf.crypto.vendor.m2p.dto.response.PriceTickerResponseDto;

@Component
public class PriceTickerApi extends BinanceApi {
    private final String url = BASE_URL + BinanceApiEndpointsEnum.PRICE_TICKER.getEndpoint();

    private final String SYMBOL_QUERY_PARAM_KEY = "?symbol=";
    private final String SYMBOLS_QUERY_PARAM_KEY = "?symbols=";

    public PriceTickerResponseDto getForOne(SupportedSymbolEnum symbol) throws ServiceException {
        final String builtUrl = url + SYMBOL_QUERY_PARAM_KEY + symbol;
        final JsonNode responseNode = get(builtUrl);
        final PriceTickerResponseDto priceTickerResponseDto = jsonUtil.toObject(responseNode,
                PriceTickerResponseDto.class);
        return priceTickerResponseDto;
    }

    public List<PriceTickerResponseDto> getForAllSupportedSymbols() throws ServiceException {
        final String symbols = Arrays.asList(SupportedSymbolEnum.values()).toString()
                .replace("[", "[\"").replace(", ", "\",\"").replace("]", "\"]");

        // final String builtUri = new
        // URIBuilder(url).addParameter(SYMBOLS_QUERY_PARAM_KEY, symbols).toString();
        // final JsonNode responseNode = get(builtUri.replace("%2C", ","));
        final String builtUrl = url + SYMBOLS_QUERY_PARAM_KEY + symbols;
        final JsonNode responseNode = get(builtUrl);
        final List<PriceTickerResponseDto> priceTickerResponseDto = Arrays
                .asList(jsonUtil.toObject(responseNode, PriceTickerResponseDto[].class));
        return priceTickerResponseDto;
    }

}
