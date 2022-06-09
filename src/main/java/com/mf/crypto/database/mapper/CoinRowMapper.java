package com.mf.crypto.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mf.crypto.model.Coin;

public class CoinRowMapper implements RowMapper<Coin> {

    @Override
    public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Coin coin = new Coin();
        coin.setId(rs.getString("id"));
        coin.setSymbol(rs.getString("symbol"));
        coin.setPrice(rs.getDouble("price"));
        return coin;
    }
}