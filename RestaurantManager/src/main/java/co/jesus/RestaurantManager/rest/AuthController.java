package co.jesus.RestaurantManager.rest;

import co.jesus.RestaurantManager.database.DatabaseOperation;
import co.jesus.RestaurantManager.database.DatabaseOperationHandler;
import co.jesus.RestaurantManager.database.operations.UsersOperations;
import co.jesus.RestaurantManager.entities.User;
import com.mysql.cj.log.Log;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/test")
    public String test(){

        String names = UsersOperations.getUsers();

        return names;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginBody loginBody){

        String username = loginBody.getUsername();
        String password = loginBody.getPassword();

        if(username == null || password == null || username.isBlank() || password.isBlank()){
            return ResponseEntity.status(401)
                    .body(new AccessTokenResponse(" "));
        }
        
        User user = UsersOperations.validateLogin(username,password);

        if(user == null){
            return ResponseEntity.status(401)
                    .body(new AccessTokenResponse(""));
        }

        int id = user.getId();
        String name = user.getUsername();
        int roleId = user.getRoleId();

        String token = JwtUtil.generateToken(id,name,roleId);
        System.out.println(token);
        return ResponseEntity.status(200)
                .body(new AccessTokenResponse(token));
    }

}

class LoginBody {
    private String username;
    private String password;

    public LoginBody(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class AccessTokenResponse{
    private String access_token;

    public AccessTokenResponse(String access_token){
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
