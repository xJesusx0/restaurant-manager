package co.jesus.RestaurantManager.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static co.jesus.RestaurantManager.rest.JwtUtil.generateToken;

@RestController
public class MainController {

    @GetMapping("/token")
    public String token(){

        String token2 = generateToken(1,"jesus",1);

        return token2;

    }



}
