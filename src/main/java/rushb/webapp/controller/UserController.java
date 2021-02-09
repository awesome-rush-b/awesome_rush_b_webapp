package rushb.webapp.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.exception.UserAlreadyExistException;
import rushb.webapp.exception.UserNotFoundException;
import rushb.webapp.model.ResponseResult;
import rushb.webapp.model.User;
import rushb.webapp.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


@Api(tags = "2 User Related")
//Provide User related portal.
@CrossOrigin
@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(
            value = "Get all of the User records from database",
            produces = "application/json")
    @GetMapping("api/users")
    public ResponseEntity<ResponseResult<List<User>>> list() {
        return ResponseEntity.ok(new ResponseResult<List<User>>(
                new Date(),
                true,
                "All users' information",
                "Get all of the User(Blog creator) records from database",
                userService.list()
        ));
    }

    @ApiOperation(
            value = "Get the User by User name",
            produces = "application/json")
    @GetMapping("api/user")
    public ResponseEntity<ResponseResult<User>> findByName(@NotBlank @RequestParam(name = "username") String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new UserNotFoundException("User cannot be found by username: " + username);
        }
        return ResponseEntity.ok(new ResponseResult<>(
                new Date(),
                true,
                "Get user detail by username",
                "Return user's detail",
                user
        ));
    }

    @ApiOperation(
            value = "Update the User detail",
            notes = "Update the User with data included in request body",
            produces = "application/json")
    @PutMapping("api/user")
    public ResponseEntity<ResponseResult<User>> updateUser(@Valid @RequestBody User user) {

        if (userService.findByName(user.getUsername()) == null) {
            throw new UserNotFoundException("User cannot be found by username: " + user.getUsername());
        }

        userService.updateUser(user);
        return ResponseEntity.ok(new ResponseResult<>(
                new Date(),
                true,
                "Username has been changed successfully",
                "Changed user's info is in resultData",
                user
        ));
    }

    /////////////////////// TESTING ONLY BBBBBAAAAAADDDDD PRACTICE //////////////////////////
    @ApiOperation(
            value = "(TEST ONLY)Delete the User by userId.",
            produces = "application/json"
    )
    @DeleteMapping("api/user/{username}")
    public ResponseEntity<ResponseResult<String>> delete(@PathVariable String username) {

        if (userService.findByName(username) == null) {
            throw new UserNotFoundException("The user cannot be found by username: " + username);
        }

        userService.deleteByUsername(username);
        return ResponseEntity.ok(new ResponseResult<String>(
                new Date(),
                true,
                "Delete success!",
                "User with username: " + username,
                "User has been deleted!"
        ));
    }

    @ApiOperation(
            value = "(TEST ONLY)Create an User.",
            produces = "application/json")
    @PostMapping("api/user")
    public ResponseEntity<ResponseResult<User>> save(@RequestBody User user) {
        if (userService.findByName(user.getUsername()) != null) {
            throw new UserAlreadyExistException("The user with username " + user.getUsername() + " already exist!");
        }

        userService.save(user);

        return ResponseEntity.ok(new ResponseResult<User>(
                new Date(),
                true,
                "User created success",
                "User detail can be found in resultData",
                user
        ));
    }

    // Username should be unique So this api has been deprecated
    @ApiIgnore
    @Deprecated
    @ApiOperation(
            value = "Get the User by User Id",
            produces = "application/json")
    @GetMapping("api/user/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        User user = userService.findById(id);
        if (user == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(user);
    }

//
//    @GetMapping("api/user/articles")
//    public ResponseEntity<List<Blog>> listArticles(){
//        List<Blog> blogList = userService.getAllArticle();
//        return ResponseEntity.ok().body(blogList);
//    }


}
