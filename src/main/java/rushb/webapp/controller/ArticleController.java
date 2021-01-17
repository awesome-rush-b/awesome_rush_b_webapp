package rushb.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "Article related")
@RequestMapping("api/article")
@RestController
public class ArticleController {
    @GetMapping("helloWorld")
    public ResponseEntity hello(){
        return ResponseEntity.ok().body("hello world");
    }

    @ApiOperation("得到对应 creator 的所有 article")
    @GetMapping("/{creatorId}")
    public ResponseEntity listArticles(@PathVariable("creatorId") String creatorId){
        return ResponseEntity.ok().body("得到对应 creator 的所有 article");
    }

    @ApiOperation("得到对应 creator 的指定 article")
    @GetMapping("/{creatorId}/{articleId}")
    public ResponseEntity getArticle(@PathVariable("creatorId") String creatorId,@PathVariable("articleId") String articleId){
        return ResponseEntity.ok().body("得到对应 creator 的指定 article");
    }

    @ApiOperation("使用指定用户发布新文章")
    @PostMapping("/{creatorId}")
    public ResponseEntity postArticle(@RequestBody Map<String, Object> jsonObject){
        return ResponseEntity.ok().body("使用指定用户发布新文章");
    }

    @ApiOperation("使用指定用户更新对应文章")
    @PutMapping("/{creatorId}/{articleId}")
    public ResponseEntity putArticle(@PathVariable("creatorId") String creatorId,@PathVariable("articleId") String articleId){
        return ResponseEntity.ok().body("使用指定用户更新对应文章");
    }

    @ApiOperation("删除对应用户的对应文章（将文章状态改为\"回收站\"）")
    @DeleteMapping("/{creatorId}/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable("creatorId") String creatorId,@PathVariable("articleId") String articleId){
        return ResponseEntity.ok().body("删除对应用户的对应文章（将文章状态改为\"回收站\"）");
    }

}
