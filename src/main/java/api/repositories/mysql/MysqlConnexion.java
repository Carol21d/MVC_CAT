package api.repositories.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnexion {
    // declarmos aqui
    public Connection conn;
    // esto hara la conexion
    private String driver = "com.mysql.cj.jdbc.Driver";

    // Con xampp - port: 3306
    // la conexion a nuestra base de datos
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
            //password debe estar vacia
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

    }
    

}
