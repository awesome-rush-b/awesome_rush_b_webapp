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
    public ResponseEntity<List<Subscriber>> list(@RequestParam(required = false, name = "authorId") String authorId){
        List<Subscriber> subscriberList;
        if(authorId == null)
            subscriberList = subscriberService.list();
        else
            subscriberList = subscriberService.listByAuthorId(authorId);
        if(subscriberList == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(subscriberList);
    }

    @ApiOperation("find the subscriber by id")
    @GetMapping("api/sub/{id}")
    public ResponseEntity<Subscriber> findById(@PathVariable String id){
        Subscriber subscriber = subscriberService.findById(id);
        if(subscriber == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(subscriber);
    }

    @ApiOperation("find the subscriber by name")
    @GetMapping("api/sub")
    public ResponseEntity<?> findByEmailOrName(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "email") String email){
        if(name == null && email == null)
            return ResponseEntity.badRequest().body("Name param OR Email param is needed!");
        Subscriber subscriber;
        if(name != null)
            subscriber = subscriberService.findByName(name);
        else
            subscriber = subscriberService.findByEmail(email);

        if(subscriber == null)
            return ResponseEntity.status(404).body("resource not found");
        return ResponseEntity.ok(subscriber);
    }

    @ApiOperation("create subscription")
    @PostMapping("api/sub")
    public ResponseEntity<?> save(@RequestBody Subscriber subscriber){
        return ResponseEntity.ok("Subscription with "+subscriber.getSubscriberId()+" has been created");
    }

    @ApiOperation("cancel subscription")
    @DeleteMapping("api/sub/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return ResponseEntity.ok("Subscription with "+id+" has been deleted");
    }
}
