package rushb.webapp.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.model.Article;
import rushb.webapp.service.ArticleService;

import java.util.List;

@Api(tags = "Article related")
@RestController
public class ArticleController {

    ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("api/articles")
    public ResponseEntity<List<Article>> listArticles(){
        List<Article> articleList = articleService.list();
        return ResponseEntity.ok().body(articleList);
    }

    @GetMapping("api/articles")
    public ResponseEntity<List<Article>> listByAuthorName(String name){
        List<Article> articleList = articleService.listByAuthorName(name);
        return ResponseEntity.ok().body(articleList);
    }

    @GetMapping("api/articles")
    public ResponseEntity<List<Article>> listByAuthorId(@RequestParam(name = "id") Long authorId){
        List<Article> articleList = articleService.listByAuthorId(authorId);
        return ResponseEntity.ok().body(articleList);
    }

    @GetMapping("api/articles")
    public ResponseEntity<List<Article>> listByTag(@RequestParam(name = "tag") String tag){
        List<Article> articleList = articleService.listByTag(tag);
        return ResponseEntity.ok().body(articleList);
    }

    @GetMapping("api/article/{id}")
    public ResponseEntity<Article> findById(@PathVariable Long id){
        Article article = articleService.findById(id);
        return ResponseEntity.ok().body(article);
    }

    @GetMapping("api/article")
    public ResponseEntity<Article> findByName(@RequestParam(name = "name") String name){
        Article article = articleService.findByName(name);
        return ResponseEntity.ok().body(article);
    }

    @DeleteMapping("api/article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return ResponseEntity.ok().body("Article " + id + " has been successful deleted");
    }

    @PutMapping("api/article/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id){
        articleService.updateArticle(id);
        return ResponseEntity.ok().body("Article " + id + " has been successful updated");
    }

    @PostMapping("api/article")
    public ResponseEntity<?> save(@RequestBody Article article){
        articleService.save(article);
        return ResponseEntity.ok().body("Article " + article.getId() + " has been successful updated");
    }



}
