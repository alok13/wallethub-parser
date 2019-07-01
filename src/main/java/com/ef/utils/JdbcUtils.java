package com.ef.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.ef.utils.Constants.*;


public class JdbcUtils {

    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(JDBC_DRIVER);
             connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
       return connection;
    }
}
