package rushb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.model.User;
import rushb.webapp.service.UserService;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.ok().body("Username has been changed successfully");
    }

    @GetMapping("api/user/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("api/user")
    public ResponseEntity<User> findByName(@RequestParam(name = "name") String name){
        User user = userService.findByName(name);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok().body(userService.list());
    }

    /////////////////////// TESTING ONLY BBBBBAAAAAADDDDD PRACTICE //////////////////////////
    @DeleteMapping("api/user/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.ok().body("user "+id+" has been deleted");
    }

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
