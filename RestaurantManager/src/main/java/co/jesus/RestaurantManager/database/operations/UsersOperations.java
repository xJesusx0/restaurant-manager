package co.jesus.RestaurantManager.database.operations;

import co.jesus.RestaurantManager.database.DatabaseOperation;
import co.jesus.RestaurantManager.database.DatabaseOperationHandler;
import co.jesus.RestaurantManager.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersOperations {
    public static String getUsers(){

        List<String> names = new ArrayList<>();

        DatabaseOperation GetUsers = new DatabaseOperation() {
            @Override
            public void execute(Connection connection) throws SQLException {
                String query = "SELECT * FROM users";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    System.out.println(resultSet.getString("username"));
                    names.add(resultSet.getString("username"));
                }
            }
        };

        DatabaseOperationHandler.handreOperation(GetUsers);
        return String.join(",", names);
    }


    public static User validateLogin(String username,String password){

        User user = null;

        User[] users = {user};

        DatabaseOperation ValidateLogin = new DatabaseOperation() {
            @Override
            public void execute(Connection connection) throws SQLException {
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";

                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1,username);
                    statement.setString(2,password);

                    ResultSet resultSet = statement.executeQuery();

                    if(resultSet.next()){
                        int id = resultSet.getInt("id");
                        String username = resultSet.getString("username");
                        int roleId = resultSet.getInt("role_id");
                        User user = new User(id,username,roleId);
                        users[0] = user;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        DatabaseOperationHandler.handreOperation(ValidateLogin);

        return users[0];
    }
}
