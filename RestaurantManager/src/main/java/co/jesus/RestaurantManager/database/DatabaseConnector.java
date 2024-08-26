package co.jesus.RestaurantManager.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class DatabaseConnector {

    public static Connection connect(){
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("URL");
        String user = dotenv.get("MYSQL_USER");
        String password = dotenv.get("MYSQL_PASSWORD");

        Connection connection;

        try{
            connection = DriverManager.getConnection(url,user,password);

            if(connection != null){
                System.out.println("Conexion con la base de datos exitosa");
            }

            return connection;

        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error ");
            e.printStackTrace();
        }

        System.out.println("No se ha podido conectar con la base de datos");
        return null;
    }
}
