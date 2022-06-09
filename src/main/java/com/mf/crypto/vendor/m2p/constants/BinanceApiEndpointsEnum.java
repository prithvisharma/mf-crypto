package com.mf.crypto.vendor.m2p.constants;

public enum BinanceApiEndpointsEnum {
    PRICE_TICKER("ticker/price");

    private final String endpoint;

    private BinanceApiEndpointsEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
