package thanhtran.lab2;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    private static Connection conn;

    public static Connection getConnection(String url, String username, String password) {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
