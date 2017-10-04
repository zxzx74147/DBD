package com.zxzx74147.modules_dbd.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zhengxin on 15/7/3.
 */
public class DBService {
    private static DBService mInstance = null;
    private ComboPooledDataSource cpds = new ComboPooledDataSource();

    public static DBService sharedInstance(){
        if(mInstance == null){
            mInstance = new DBService();
        }
        return mInstance;
    }

    private DBService(){

    }

    public boolean init(){
        try {
            cpds.setDriverClass(DBConfig.JDBC_DRIVER);
            cpds.setJdbcUrl(DBConfig.DB_URL);
            cpds.setUser(DBConfig.DB_UNAME);
            cpds.setPassword(DBConfig.DB_PASS);

            cpds.setMaxStatements(300);
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(10);
            return true;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close(){
        cpds.close();
    }

    public Connection getConnection(){
        try {
            Connection connection = cpds.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
