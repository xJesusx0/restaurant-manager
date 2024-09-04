package co.jesus.RestaurantManager.database.operations;

import co.jesus.RestaurantManager.database.DatabaseOperation;
import co.jesus.RestaurantManager.database.DatabaseOperationHandler;
import co.jesus.RestaurantManager.entities.Dish;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DishesOperations {

    public static List<Dish> getDishes(){
        List<Dish> dishes = new ArrayList<>();

        DatabaseOperation GetDishes = new DatabaseOperation() {
            @Override
            public void execute(Connection connection) throws SQLException {
                String query = "SELECT * FROM dishes WHERE is_available = true";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    Dish dish = new Dish();
                    dish.setDishId(resultSet.getInt("dish_id"));
                    dish.setName(resultSet.getString("name"));
                    dish.setDescription(resultSet.getString("description"));
                    dish.setPrice(resultSet.getBigDecimal("price"));
                    dish.setCategory(resultSet.getString("category"));
                    dish.setAvailable(resultSet.getBoolean("is_available"));
                    dish.setCreatedAt(resultSet.getTimestamp("created_at"));
                    dish.setUpdatedAt(resultSet.getTimestamp("updated_at"));

                    dishes.add(dish);
                }

            }
        };

        DatabaseOperationHandler.handleOperation(GetDishes);

        return dishes;
    }

}
