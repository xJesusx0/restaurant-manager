package co.jesus.RestaurantManager.rest;

import co.jesus.RestaurantManager.database.operations.UsersOperations;
import co.jesus.RestaurantManager.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    .body(new LoginResponse("","Campos vacios",""));
        }
        
        User user = UsersOperations.validateLogin(username,password);

        if(user == null){
            return ResponseEntity.status(401)
                    .body(new LoginResponse("","Datos invalidos",""));
        }

        int id = user.getId();
        String name = user.getUsername();
        int roleId = user.getRoleId();

        String token = JwtUtil.generateToken(id,name,roleId);
        System.out.println(token);
        return ResponseEntity.status(200)
                .body(new LoginResponse(token,"Login exitoso",String.valueOf(roleId)));
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

class LoginResponse{
    private String access_token;
    private String message;
    private String roleId;

    public LoginResponse(String access_token, String message,String roleId){
        this.access_token = access_token;
        this.message = message;
        this.roleId = roleId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return roleId;
    }

    public void setRole(String roleId) {
        this.roleId = roleId;
    }
}
