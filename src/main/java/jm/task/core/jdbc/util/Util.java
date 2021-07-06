package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection settingConnection() {
        Connection connection = null;
        String URL = "jdbc:mysql://localhost:3306/preproject";
        Properties connectionProps = new Properties();
        String userName = "root";
        String password = "root";
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try {
            connection = DriverManager.getConnection(URL, connectionProps);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

}
