package com.mf.crypto.constant;

import com.mf.crypto.config.env.Env;

public class EnvConstant {
    public static final String SERVICE_ENV = Env.get("SERVICE_ENV");

    public static final String RDS_HOSTNAME = Env.get("RDS_HOSTNAME");
    public static final String RDS_PORT = Env.get("RDS_PORT");
    public static final String RDS_DB_NAME = Env.get("RDS_DB_NAME");
    public static final String RDS_USERNAME = Env.get("RDS_USERNAME");
    public static final String RDS_PASSWORD = Env.get("RDS_PASSWORD");

    public static final String BINANCE_BASE_URL = Env.get("BINANCE_BASE_URL");
}
