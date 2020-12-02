package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    //Conexion a la base de Datos MySQL
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String user = "root";
        String pass =  "root";
        String url = "jdbc:mysql://localhost:3306/grupo1_db?serverTimezone=America/Lima";

        return DriverManager.getConnection(url, pass, user);
    }

}
