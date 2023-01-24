package api.repositories.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnexion {
    
    public Connection conn;
    private String driver = "com.mysql.cj.jdbc.Driver";

    // Con xampp - port: 3306
    private String url = "jdbc:mysql://localhost:8889/java_tests_servlets?" +
            "useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    public MysqlConnexion() {
        if (conn == null) {
            this.getConnection();
        }
    }

    private void getConnection() {
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

    }
    

}
