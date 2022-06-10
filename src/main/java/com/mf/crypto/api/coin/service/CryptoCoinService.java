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

    final Map<String, Coin> coinCache = new HashMap<String, Coin>();
    final Map<String, List<Coin>> coinListCache = new HashMap<String, List<Coin>>();

    public ServiceResponseDataDto getAll(String pageNo, String pageSize) throws ServiceException {

        final int requestedPageNo = validatePageNo(pageNo);
        final int requestedPageSize = validatePageSize(pageSize);
        final String cacheKey = pageNo + ":" + pageSize;

        if (coinListCache.containsKey(cacheKey)) {
            return new ServiceResponseDataDto(coinListCache.get(cacheKey));
        }

        final List<Coin> coinList = coinDb.findAllPaginated(requestedPageNo, requestedPageSize);
        coinListCache.put(cacheKey, coinList);

        return new ServiceResponseDataDto(coinList);
    }

    public ServiceResponseDataDto get(String symbol) throws ServiceException {
        validateSymbol(symbol);

        if (coinCache.containsKey(symbol)) {
            final Coin coin = coinCache.get(symbol);
            return new ServiceResponseDataDto(coin);
        }

        final Coin coin = coinDb.findBySymbol(symbol);

        coinCache.put(symbol, coin);

        return new ServiceResponseDataDto(coin);
    }

    private void validateSymbol(String symbol) throws ServiceException {
        if (symbol == null) {
            throw new ServiceException(CustomError.QUERY_PARAM_REQUIRED);
        }
        try {
            SupportedSymbolEnum.valueOf(symbol);
        } catch (Exception e) {
            throw new ServiceException(CustomError.INVALID_SYMBOL);
        }
    }

    private int validatePageNo(String pageNo) throws ServiceException {
        final int defaultPageNo = 1;
        try {
            if (pageNo != null) {
                return Integer.parseInt(pageNo);
            }
            return defaultPageNo;
        } catch (Exception e) {
            throw new ServiceException(CustomError.INVALID_PAGE_NO);
        }
    }

    private int validatePageSize(String pageSize) throws ServiceException {
        final int defaultPageSize = 10;
        try {
            if (pageSize != null) {
                return Integer.parseInt(pageSize);
            }
            return defaultPageSize;
        } catch (Exception e) {
            throw new ServiceException(CustomError.INVALID_PAGE_SIZE);
        }
    }

}
