package cinepro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private String url="jdbc:mysql://localhost:3306/cinepro";
    private String login="root";
    private String pwd="";
    private Connection connection;
    private static MyConnection instance;

    private MyConnection() {
        try {
            connection = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }


}
