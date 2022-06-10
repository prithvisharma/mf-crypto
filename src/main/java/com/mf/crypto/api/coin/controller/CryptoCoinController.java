package com.mf.crypto.api.coin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mf.crypto.api.coin.service.CryptoCoinService;
import com.mf.crypto.dto.ServiceResponseDataDto;
import com.mf.crypto.exception.ServiceException;

@RestController
@RequestMapping(path = "crypto")
public class CryptoCoinController {

    @Autowired
    private CryptoCoinService cryptoCoinService;

    @GetMapping(value = "/coins")
    public ServiceResponseDataDto getAllCoins(@RequestParam(name = "pageNo", required = false) String pageNo,
            @RequestParam(name = "pageSize", required = false) String pageSize) throws ServiceException {
        return cryptoCoinService.getAll(pageNo, pageSize);
    }

    @GetMapping(value = "/coin")
    public ServiceResponseDataDto getCoin(@RequestParam(name = "symbol", required = false) String symbol)
            throws ServiceException {
        return cryptoCoinService.get(symbol);
    }

}
