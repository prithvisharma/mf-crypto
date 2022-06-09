package com.mf.crypto.database.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mf.crypto.database.CoinDb;
import com.mf.crypto.exception.ServiceException;
import com.mf.crypto.model.Coin;
import com.mf.crypto.vendor.m2p.apiintegration.PriceTickerApi;
import com.mf.crypto.vendor.m2p.dto.response.PriceTickerResponseDto;

@Component
public class DatabaseInitializer {

    @Autowired
    private PriceTickerApi priceTickerApi;

    @Autowired
    private CoinDb coinDb;

    public void load() throws ServiceException {
        final List<PriceTickerResponseDto> priceTickerResponseDtoList = priceTickerApi.getForAllSupportedSymbols();

        final List<Coin> coinList = priceTickerResponseDtoList.stream().map(
                obj -> new Coin(obj.getSymbol(), Double.parseDouble(obj.getPrice())))
                .collect(Collectors.toList());

        for (Coin coin : coinList) {
            coinDb.upsert(coin);
        }
    }
}
