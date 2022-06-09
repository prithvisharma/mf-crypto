package com.mf.crypto.database.common;

public enum DatabaseTableEnum {

    TEST("test");

    private String table;

    private DatabaseTableEnum(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}
