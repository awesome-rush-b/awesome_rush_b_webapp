package rushb.webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @GetMapping("api/articles")
    public ResponseEntity listArticles(){
        return ResponseEntity.ok().body("Hello from Spring Boot");
    }


}
