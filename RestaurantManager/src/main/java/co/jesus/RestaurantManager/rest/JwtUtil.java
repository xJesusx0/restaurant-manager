package co.jesus.RestaurantManager.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtil {

   // private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    public static String generateToken(int id,String username,int roleId) {
        Dotenv dotenv = Dotenv.load();

        String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");
        System.out.println(SECRET_KEY);
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .setSubject(username)
                .setSubject(String.valueOf(roleId))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}