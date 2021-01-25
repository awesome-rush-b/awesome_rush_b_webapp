package rushb.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.model.User;
import rushb.webapp.service.UserService;

import java.util.List;


@Api(tags = "User Controller. Provide User related portal. ")
@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Update the User with data included in request body")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", value = "the id of the user to be updated"),
            @ApiImplicitParam(name = "user", dataType = "User", value = "the user to be updated, userId included")
    })
    @PutMapping("api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.ok().body("Username has been changed successfully");
    }

    @ApiOperation("Get the User by User Id")
    @GetMapping("api/user/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation("Get the User by User name")
    @GetMapping("api/user")
    public ResponseEntity<User> findByName(@RequestParam(name = "name") String name){
        User user = userService.findByName(name);
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation("Get all of the User records from database")
    @GetMapping("api/users")
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok().body(userService.list());
    }

    /////////////////////// TESTING ONLY BBBBBAAAAAADDDDD PRACTICE //////////////////////////
    @ApiOperation("Delete the User by userId. (TEST ONLY BAD PRACTICE)")
    @DeleteMapping("api/user/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.ok().body("user "+id+" has been deleted");
    }

    @ApiOperation("Create an User. (TEST ONLY BAD PRACTICE)")
    @PostMapping("api/user")
    public ResponseEntity<?> save(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok().body("user "+user.getUserId()+" has been created");
    }

//
//    @GetMapping("api/user/articles")
//    public ResponseEntity<List<Blog>> listArticles(){
//        List<Blog> blogList = userService.getAllArticle();
//        return ResponseEntity.ok().body(blogList);
//    }




}
