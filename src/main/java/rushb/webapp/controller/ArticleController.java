package rushb.webapp.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Article related")
@RestController
public class ArticleController {

//    ArticleService articleService;
//
//    @Autowired
//    public ArticleController(ArticleService articleService) {
//        this.articleService = articleService;
//    }
//
//    @GetMapping("api/articles")
//    public ResponseEntity<List<Blog>> listArticles(){
//        List<Blog> blogList = articleService.list();
//        return ResponseEntity.ok().body(blogList);
//    }
//
//    @GetMapping("api/articles")
//    public ResponseEntity<List<Blog>> listByAuthorName(String name){
//        List<Blog> blogList = articleService.listByAuthorName(name);
//        return ResponseEntity.ok().body(blogList);
//    }
//
//    @GetMapping("api/articles")
//    public ResponseEntity<List<Blog>> listByAuthorId(@RequestParam(name = "id") String authorId){
//        List<Blog> blogList = articleService.listByAuthorId(authorId);
//        return ResponseEntity.ok().body(blogList);
//    }
//
//    @GetMapping("api/articles")
//    public ResponseEntity<List<Blog>> listByTag(@RequestParam(name = "tag") String tag){
//        List<Blog> blogList = articleService.listByTag(tag);
//        return ResponseEntity.ok().body(blogList);
//    }
//
//    @GetMapping("api/article/{id}")
//    public ResponseEntity<Blog> findById(@PathVariable String id){
//        Blog blog = articleService.findById(id);
//        return ResponseEntity.ok().body(blog);
//    }
//
//    @GetMapping("api/article")
//    public ResponseEntity<Blog> findByName(@RequestParam(name = "name") String name){
//        Blog blog = articleService.findByName(name);
//        return ResponseEntity.ok().body(blog);
//    }
//
//    @DeleteMapping("api/article/{id}")
//    public ResponseEntity<?> deleteArticle(@PathVariable String id){
//        articleService.deleteArticle(id);
//        return ResponseEntity.ok().body("Article " + id + " has been successful deleted");
//    }
//
//    @PutMapping("api/article/{id}")
//    public ResponseEntity<?> updateArticle(@PathVariable String id, @RequestBody Blog blog){
//        articleService.updateArticle(id);
//        return ResponseEntity.ok().body("Article " + id + " has been successful updated");
//    }
//
//    @PostMapping("api/article")
//    public ResponseEntity<?> save(@RequestBody Blog blog){
//        articleService.save(blog);
//        return ResponseEntity.ok().body("Article " + blog.getBlogId() + " has been successful updated");
//    }



}
