package rushb.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.model.Subscriber;
import rushb.webapp.service.SubscriberService;

import java.util.List;

@Api(tags = "Subscriber Controller")
@RestController
public class SubscriberController {
    SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @ApiOperation("list all of the subscribers from database")
    @GetMapping("api/subs")
    public ResponseEntity<List<Subscriber>> list(){
        return ResponseEntity.ok(subscriberService.list());
    }

    @ApiOperation("list all of the subscribers subscribed the target user from database")
    @GetMapping("api/subs")
    public ResponseEntity<List<Subscriber>> listByAuthorId(@RequestParam(value = "authorId") String authorId){
        return ResponseEntity.ok(subscriberService.listByAuthorId(authorId));
    }

    @ApiOperation("find the subscriber by id")
    @GetMapping("api/sub/{id}")
    public ResponseEntity<Subscriber> findById(@PathVariable String id){
        return ResponseEntity.ok(subscriberService.findById(id));
    }

    @ApiOperation("find the subscriber by name")
    @GetMapping("api/sub")
    public ResponseEntity<Subscriber> findByName(@RequestParam(value = "name") String name){
        return ResponseEntity.ok(subscriberService.findByName(name));
    }

    @ApiOperation("find the subscriber by email")
    @GetMapping("api/sub")
    public ResponseEntity<Subscriber> findByEmail(@RequestParam(value = "email") String email){
        return ResponseEntity.ok(subscriberService.findByEmail(email));
    }

    @ApiOperation("create subscription")
    @PostMapping("api/sub")
    public ResponseEntity<?> save(@RequestBody Subscriber subscriber){
        return ResponseEntity.ok("Subscription with "+subscriber.getSubscriberId()+" has been created");
    }

    @ApiOperation("cancle subscription")
    @DeleteMapping("api/sub/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return ResponseEntity.ok("Subscription with "+id+" has been deleted");
    }
}
