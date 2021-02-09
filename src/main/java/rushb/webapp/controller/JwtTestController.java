package rushb.webapp.controller;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.utils.JwtTokenUtil;
import springfox.documentation.annotations.ApiIgnore;

import javax.security.sasl.AuthenticationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiIgnore()
@Deprecated
@RestController
@CrossOrigin
@RequestMapping(value = "/testApi")
public class JwtTestController {
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtTestController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ApiOperation("login with username and password, the token will expire in 60 seconds")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, Object> jsonObject) {
        // 1. verify the username and password based on database
        // Todo
        // 2. verified user and generate the token for this user
        String username = (String) jsonObject.get("username");
        String token = jwtTokenUtil.generateToken(username);

        Map<String, Object> returnObjects = new HashMap<>();
        returnObjects.put("token", token);

        return new ResponseEntity<Map<String, Object>>(returnObjects, HttpStatus.OK);
    }

    @ApiOperation("get username by token")
    @GetMapping("/getUserInfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestHeader("Authorization") String authHeader) throws AuthenticationException {
        Map<String, Object> returnObjects = new HashMap<>();

        // forbidden token
        List<String> blacklistToken = Arrays.asList("Forbidden token");
        Claims claims = jwtTokenUtil.getClaimByToken(authHeader);
        if (claims == null || JwtTokenUtil.isTokenExpired(claims.getExpiration()) || blacklistToken.contains(authHeader)) {
//            throw new AuthenticationException("token is expired!");
            returnObjects.put("message", "Forbidden token");
            return new ResponseEntity<Map<String, Object>>(returnObjects, HttpStatus.BAD_REQUEST);
        }

        String userId = (String) claims.get("username");
        returnObjects.put("username", userId);
        return new ResponseEntity<Map<String, Object>>(returnObjects, HttpStatus.OK);
    }
}
