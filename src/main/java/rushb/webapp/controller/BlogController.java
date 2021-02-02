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
    public ResponseEntity<List<Blog>> list(
            @RequestParam(required = false, name = "authorId") String authorId,
            @RequestParam(required = false, name = "authorName") String authorName,
            @RequestParam(required = false, name = "tagName") String tagName
    ){
        List<Blog> blogList;
        if(authorId != null)
            blogList=blogService.listByAuthorId(authorId);
        else if(authorName != null)
            blogList=blogService.listByAuthorName(authorName);
        else if(tagName != null)
            blogList=blogService.listByTag(tagName);
        else
            blogList=blogService.list();
        if(blogList == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(blogList);
    }

    @ApiOperation("find blog by Id")
    @GetMapping("api/blog/{id}")
    public ResponseEntity<Blog> findById(@PathVariable String id){
        Blog blog = blogService.findById(id);
        if(blog == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(blog);
    }

    @ApiOperation("find blog by title")
    @GetMapping("api/blog")
    public ResponseEntity<Blog> findByTitle(@RequestParam(name = "title") String title){
        Blog blog = blogService.findByTitle(title);
        if(blog == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(blog);
    }


    @ApiOperation("list all of the tags from database")
    @GetMapping("api/tags")
    public ResponseEntity<List<Tag>> listTags(@RequestParam(required = false, name = "number") Integer num){
        List<Tag> tags;
        if(num == null)
            tags = blogService.listTags();
        else
            tags = blogService.mostNPopular(num);

        if(tags == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(tags);
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
