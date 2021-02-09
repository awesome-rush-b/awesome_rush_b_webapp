package rushb.webapp.controller;


import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.model.JwtResponse;
import rushb.webapp.model.ResponseResult;
import rushb.webapp.model.User;
import rushb.webapp.service.JwtUserDetailsService;
import rushb.webapp.model.JwtRequest;
import rushb.webapp.utils.JwtTokenUtil;

import javax.validation.Valid;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
@Api(tags = {"1 Authorization Related"})
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    private JwtTokenUtil jwtTokenUtil;

    private AuthenticationManager authenticationManager;

    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public AuthController(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            // username or password is wrong
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @ApiOperation(
            value = "Request for a 30 minutes token",
            notes = "Username and password should be valid!",
            produces = "application/json"
    )
    @PostMapping("/login")
    public ResponseEntity<ResponseResult<JwtResponse>> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception {
        // Check if the use if valid
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Load User By Username
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // return the token
        // 200 response
        return ResponseEntity.ok(new ResponseResult<JwtResponse>(
                new Date(),
                true,
                "Login success!",
                "30 mins token has been generated!",
                new JwtResponse(token)
        ));
    }
    @ApiOperation(
            value = "Request for validating the token",
            notes = "200 status code will be returned if token is valid",
            produces = "application/json"
    )
    @PostMapping("/isValid")
    public ResponseEntity<ResponseResult<String>> isExpired(){
        return ResponseEntity.ok(new ResponseResult<>(
                new Date(),
                true,
                "Token is valid",
                "Token is validated by server",
                "Token is valid"
        ));
    }

    @ApiOperation(
            value = "(TEST ONLY)Register a new user",
            notes = "The role of this user is blog creator",
            produces = "application/json")
    @PostMapping("/register")
    public ResponseEntity<ResponseResult<User>> saveUser(@RequestBody @Valid User user) throws Exception {
        // register new user
        jwtUserDetailsService.save(user);

        // 200 response
        return ResponseEntity.ok(new ResponseResult<User>(
                new Date(),
                true,
                "Login success!",
                "30 mins token has been generated!",
                user
        ));
    }




}
