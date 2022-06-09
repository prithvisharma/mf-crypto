package com.mf.crypto.config.env;

public enum EnvType {

    DEV("dev"),
    TEST("test"),
    UAT("uat"),
    STAGING("staging"),
    PROD("prod");

    private String env;

    private EnvType(String env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return env;
    }
}
