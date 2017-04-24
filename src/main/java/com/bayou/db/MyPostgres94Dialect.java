package com.bayou.db;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

/**
 * Created by golde on 4/24/2017.
 */
public class MyPostgres95Dialect extends PostgreSQL95Dialect {

    public MyPostgres95Dialect() {
        super();
        registerFunction("or_like_operation", new StandardSQLFunction("or_like_operation"));
    }
}
