package com.mf.crypto.config.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mf.crypto.constant.EnvConstant;

@Configuration
public class DataSourceConfig {

    private final String RDS_HOSTNAME = EnvConstant.RDS_HOSTNAME;
    private final String RDS_PORT = EnvConstant.RDS_PORT;
    private final String RDS_DB_NAME = EnvConstant.RDS_DB_NAME;
    private final String RDS_USERNAME = EnvConstant.RDS_USERNAME;
    private final String RDS_PASSWORD = EnvConstant.RDS_PASSWORD;
    private final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private final String DRIVER = "postgresql";
    private final String JDBC_URL = "jdbc:" + DRIVER + "://" + RDS_HOSTNAME + ":" + RDS_PORT + "/" + RDS_DB_NAME;

    @Primary
    @Bean("dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(RDS_USERNAME);
        dataSource.setPassword(RDS_PASSWORD);
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
