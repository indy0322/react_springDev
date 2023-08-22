package reactspringdev.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactspringdev.security.JwtTokenUtil;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${jwt.token.secret}")//springframework(Lombok 아님), ${jwt.token.secret}은 application.properties의 값을 가져옴.
    private String key;

    private Long expireTimeMs = 1000 * 60 * 3L;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("userName") String userName){
        String token = JwtTokenUtil.createToken(userName, key, expireTimeMs);
        return ResponseEntity.ok().body(token);
    }

}
