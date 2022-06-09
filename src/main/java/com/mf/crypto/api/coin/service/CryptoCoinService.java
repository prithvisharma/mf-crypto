package com.mf.crypto.api.coin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mf.crypto.constant.CustomError;
import com.mf.crypto.constant.SupportedSymbolEnum;
import com.mf.crypto.database.CoinDb;
import com.mf.crypto.dto.ServiceResponseDataDto;
import com.mf.crypto.exception.ServiceException;
import com.mf.crypto.model.Coin;

@Service
@Transactional(rollbackFor = { ServiceException.class, Exception.class })
public class CryptoCoinService {

    @Autowired
    private CoinDb coinDb;

    public ServiceResponseDataDto getAll(String symbol, String limit) throws ServiceException {
        final int defaultLimit = 10;
        if (symbol == null && limit == null) {
            final List<Coin> coinList = coinDb.findAllByLimit(defaultLimit);
            return new ServiceResponseDataDto(coinList);
        }

        if (symbol == null && limit != null) {
            final int limitIntValue = validateLimit(limit);
            final List<Coin> coinList = coinDb.findAllByLimit(limitIntValue);
            return new ServiceResponseDataDto(coinList);
        }

        if (symbol != null && limit != null) {
            validateSymbol(symbol);
            final int limitIntValue = validateLimit(limit);
            final String coinId = coinDb.findBySymbol(symbol).getId();
            final List<Coin> coinList = coinDb.findAllByRange(symbol, coinId, limitIntValue);
            return new ServiceResponseDataDto(coinList);
        }
        throw new ServiceException(CustomError.QUERY_PARAM_NOT_SUPPORTED);
    }

    public ServiceResponseDataDto get(String symbol) throws ServiceException {
        validateSymbol(symbol);

        final Map<String, Coin> cachedCoins = new HashMap<String, Coin>();

        if (cachedCoins.containsKey(symbol)) {
            final Coin coin = cachedCoins.get(symbol);
            return new ServiceResponseDataDto(coin);
        }

        final Coin coin = coinDb.findBySymbol(symbol);

        cachedCoins.put(symbol, coin);

        return new ServiceResponseDataDto(coin);
    }

    private void validateSymbol(String symbol) throws ServiceException {
        try {
            SupportedSymbolEnum.valueOf(symbol);
        } catch (Exception e) {
            throw new ServiceException(CustomError.INVALID_SYMBOL);
        }
    }

    private int validateLimit(String limit) throws ServiceException {
        try {
            return Integer.parseInt(limit);
        } catch (Exception e) {
            throw new ServiceException(CustomError.INVALID_LIMIT);
        }
    }

}
