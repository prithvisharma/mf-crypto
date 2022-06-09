package com.mf.crypto.database.common;

import org.springframework.stereotype.Component;

@Component
public class DatabaseHelper {

    public String addTableToQuery(String table, String sql) {
        return String.format(sql, table);
    }

}
