package com.mf.crypto.model;

import com.mf.crypto.vendor.m2p.dto.response.PriceTickerResponseDto;

public class Coin {
    private String id;
    private String symbol;
    private double price;

    public Coin() {
    }

    public Coin(String symbol, double price) {
        setSymbol(symbol);
        setPrice(price);
    }

    public Coin(PriceTickerResponseDto priceTickerResponseDto) {
        setSymbol(priceTickerResponseDto.getSymbol());
        setPrice(Double.parseDouble(priceTickerResponseDto.getPrice()));
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
