package co.jesus.RestaurantManager.rest;

import co.jesus.RestaurantManager.database.operations.DishesOperations;
import co.jesus.RestaurantManager.entities.Dish;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

    @GetMapping("/get-dishes")
    public ResponseEntity getDishes(){
        List<Dish> dishes = DishesOperations.getDishes();

        return ResponseEntity.status(200)
                .body(dishes);

    }

}
