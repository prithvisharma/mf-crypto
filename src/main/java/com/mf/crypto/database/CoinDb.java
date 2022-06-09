package com.mf.crypto.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mf.crypto.constant.CustomError;
import com.mf.crypto.database.common.DatabaseHelper;
import com.mf.crypto.database.common.DatabaseTableEnum;
import com.mf.crypto.database.mapper.CoinRowMapper;
import com.mf.crypto.exception.ServiceException;
import com.mf.crypto.model.Coin;

@Component
public class CoinDb {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DatabaseHelper databaseHelper;

    private final String table = DatabaseTableEnum.COIN.getTable();

    public boolean insert(Coin coin) {
        final String query = "INSERT INTO %s (symbol, price) VALUES (?, ?);";
        final String sql = databaseHelper.addTableToQuery(table, query);
        final int result = jdbcTemplate.update(sql, coin.getSymbol(), coin.getPrice());
        return result == 1;
    }

    public boolean update(Coin coin) {
        final String query = "UPDATE %s SET price = ? WHERE symbol = ?;";
        final String sql = databaseHelper.addTableToQuery(table, query);
        final int result = jdbcTemplate.update(sql, coin.getPrice(), coin.getSymbol());
        return result == 1;
    }

    public boolean upsert(Coin coin) {
        final String symbol = coin.getSymbol();
        final double price = coin.getPrice();

        final String query = "INSERT INTO %s (symbol, price) VALUES (?, ?) "
                + "ON CONFLICT (symbol) DO UPDATE SET price = ?;";
        final String sql = databaseHelper.addTableToQuery(table, query);
        final int result = jdbcTemplate.update(sql, symbol, price, price);
        return result == 1;
    }

    public Coin findBySymbol(String symbol) throws ServiceException {
        final String query = "SELECT * FROM %s WHERE symbol = ?;";
        final String sql = databaseHelper.addTableToQuery(table, query);
        try {
            final Coin coin = jdbcTemplate.queryForObject(sql, new CoinRowMapper(), symbol);
            return coin;
        } catch (EmptyResultDataAccessException e) {
            throw new ServiceException(CustomError.COIN_NOT_FOUND);
        }
    }

    public List<Coin> findAll() {
        final String query = "SELECT * FROM %s;";
        final String sql = databaseHelper.addTableToQuery(table, query);
        final List<Coin> coinList = jdbcTemplate.query(sql, new CoinRowMapper());
        return coinList;
    }

    public List<Coin> findAllByLimit(int limit) {
        final String query = "SELECT * FROM %s ORDER BY symbol ASC LIMIT " + limit + ";";
        final String sql = databaseHelper.addTableToQuery(table, query);
        final List<Coin> coinList = jdbcTemplate.query(sql, new CoinRowMapper());
        return coinList;
    }

    public List<Coin> findAllByRange(String symbol, String id, int limit) {
        final String query = "SELECT * FROM %s WHERE (symbol, id) > (?, uuid(?)) "
                + " ORDER BY symbol ASC LIMIT " + limit + ";";
        final String sql = databaseHelper.addTableToQuery(table, query);
        final List<Coin> coinList = jdbcTemplate.query(sql, new CoinRowMapper(), symbol, id);
        return coinList;
    }
}