package com.mf.crypto.config.service;

public enum ServiceConfigEndpoint {
    CRYPTO("/crypto");

    private ServiceConfigEndpoint(String endpoint) {
        this.setEndpoint(endpoint);
    }

    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
