package com.mf.crypto.vendor.m2p.dto.response;

public class PriceTickerResponseDto {
    private String symbol; // "LTCBTC"
    private String price; // "4.00000200"

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
