package com.mf.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mf.crypto.common.SpringApplicationContext;
import com.mf.crypto.database.common.DatabaseInitializer;
import com.mf.crypto.exception.ServiceException;

@SpringBootApplication
public class MfCryptoApplication {

    public static void main(String[] args) throws ServiceException {
        SpringApplication.run(MfCryptoApplication.class, args);
        SpringApplicationContext.getBean(DatabaseInitializer.class).load();
    }

}
