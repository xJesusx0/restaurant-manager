package co.jesus.RestaurantManager.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseOperationHandler {
    public static void handreOperation(DatabaseOperation operation){
        Connection connection = DatabaseConnector.connect();

        if (connection == null){
            return;
        }

        try{
            connection.setAutoCommit(false);
            operation.execute(connection);
            connection.commit();
        } catch (SQLException e) {
            try{
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Error durante la operacion de la base de datos");
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
