package rushb.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.model.Blog;
import rushb.webapp.model.Tag;
import rushb.webapp.service.BlogService;

import java.util.List;

@Api(tags = "Blog related. \n Frontend is responsible for the Tag.Count increment!!")
@RestController
public class BlogController {

    BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @ApiOperation("list all of the blogs from database")
    @GetMapping("api/blogs")
    public ResponseEntity<List<Blog>> list(){
        return ResponseEntity.ok().body(blogService.list());
    }

    @ApiOperation("list all of the blogs from database created by the same author")
    @GetMapping("api/blogs")
    public ResponseEntity<List<Blog>> listByAuthorId(@RequestParam(value = "authorId") String authorId){
        return ResponseEntity.ok().body(blogService.listByAuthorId(authorId));
    }

    @ApiOperation("list all of the blogs from database created by the same author")
    @GetMapping("api/blogs")
    public ResponseEntity<List<Blog>> listByAuthorName(@RequestParam(value = "authorName") String authorName){
        return ResponseEntity.ok().body(blogService.listByAuthorName(authorName));
    }

    @ApiOperation("list all of the blogs from database attached with the same target tag")
    @GetMapping("api/blogs")
    public ResponseEntity<List<Blog>> listByTag(@RequestParam(value = "tag") String tagName){
        return ResponseEntity.ok().body(blogService.listByTag(tagName));
    }

    @ApiOperation("find blog by Id")
    @GetMapping("api/blog/{id}")
    public ResponseEntity<Blog> findById(@PathVariable String id){
        return ResponseEntity.ok().body(blogService.findById(id));
    }

    @ApiOperation("find blog by title")
    @GetMapping("api/blog")
    public ResponseEntity<Blog> findByTitle(@RequestParam(value = "title") String title){
        return ResponseEntity.ok().body(blogService.findByTitle(title));
    }

    @ApiOperation("list all top ' number ' of most used tags from database")
    @GetMapping("api/tags")
    public ResponseEntity<List<Tag>> mostNPopular(@RequestParam(value = "number") Integer num){
        return ResponseEntity.ok().body(blogService.mostNPopular(num));
    }

    @ApiOperation("list all of the tags from database")
    @GetMapping("api/tags")
    public ResponseEntity<List<Tag>> listTags(){
        return ResponseEntity.ok().body(blogService.listTags());
    }

    @ApiOperation("update the target blog")
    @PutMapping("api/blog/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable String id, @RequestBody Blog blog){
        blogService.updateBlog(blog);
        return ResponseEntity.ok().body("Blog "+id+" has been updated");
    }

    @ApiOperation("Create a blog")
    @PostMapping("api/blog")
    public ResponseEntity<?> saveBlog(@RequestBody Blog blog){
        blogService.save(blog);
        return ResponseEntity.ok().body("Blog "+blog.getBlogId()+" has been created");
    }

    @ApiOperation("delete a blog")
    @DeleteMapping("api/blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable String id){
        blogService.delete(id);
        return ResponseEntity.ok().body("Blog "+id+" has been deleted");
    }


}
