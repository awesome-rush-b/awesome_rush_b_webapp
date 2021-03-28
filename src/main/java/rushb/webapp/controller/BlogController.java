package rushb.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rushb.webapp.exception.BlogNotFoundException;
import rushb.webapp.exception.TagNotFoundException;
import rushb.webapp.model.Blog;
import rushb.webapp.model.ResponseResult;
import rushb.webapp.model.Tag;
import rushb.webapp.service.BlogService;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

@Api(tags = "3 Blog Related")
//Frontend is responsible for the Tag.Count increment!!
@CrossOrigin
@RestController
public class BlogController {

    BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @ApiOperation(
            value = "list all of the blogs from database",
            produces = "application/json")
    @GetMapping("api/blogs")
    public ResponseEntity<ResponseResult<List<Blog>>> list() {
        List<Blog> blogList;
        blogList = blogService.list();
        return ResponseEntity.ok(new ResponseResult<List<Blog>>(
                new Date(),
                true,
                "All blogs detail",
                "Get the list of all blogs",
                blogList
        ));
    }
    @ApiOperation(
            value = "add one count for a particular blog by blogId",
            produces = "application/json")
    @PutMapping("api/blogViewPlusOne/id/{blogId}")
    public ResponseEntity<ResponseResult<String>> blogViewPlusOne(@PathVariable String blogId){
        blogService.blogViewPlusOne(blogId);
        return ResponseEntity.ok(new ResponseResult<String>(
                new Date(),
                true,
                "Blog views plus one",
                "Blog: "+blogId+" views plus one",
                null
        ));

    }

    @ApiOperation(
            value = "list all of the blogs by author name from database",
            produces = "application/json")
    @GetMapping("api/blogs/authorName/{authorName}")
    public ResponseEntity<ResponseResult<List<Blog>>> listBlogsByAuthorName(@PathVariable String authorName) {
        List<Blog> blogList;
        blogList = blogService.listByAuthorName(authorName);
        return ResponseEntity.ok(new ResponseResult<List<Blog>>(
                new Date(),
                true,
                "All blogs detail",
                "Get the list of all blogs from author: " + authorName,
                blogList
        ));
    }

    @ApiOperation(
            value = "list all of the blogs by author name from database",
            produces = "application/json")
    @GetMapping("api/blogs/tagName/{tagName}")
    public ResponseEntity<ResponseResult<List<Blog>>> listBlogsByTagName(@PathVariable String tagName) {
        List<Blog> blogList;
        blogList = blogService.listByAuthorName(tagName);
        return ResponseEntity.ok(new ResponseResult<List<Blog>>(
                new Date(),
                true,
                "All blogs detail",
                "Get the list of all blogs by tag name: " + tagName,
                blogList
        ));
    }

    @ApiOperation(
            value = "find blog by Id",
            produces = "application/json")
    @GetMapping("api/blog/id/{id}")
    public ResponseEntity<ResponseResult<Blog>> findById(@PathVariable String id) {
        Blog blog = blogService.findById(id);
        if (blog == null)
            throw new BlogNotFoundException("No blog id match " + id + " !");
        return ResponseEntity.ok(new ResponseResult<Blog>(
                new Date(),
                true,
                "Find blog by blogId",
                "Return blog's detail by blogId",
                blog
        ));
    }

    @ApiOperation(
            value = "(fuzzy search)find blog by title",
            produces = "application/json")
    @GetMapping("api/blog/title/{title}")
    public ResponseEntity<ResponseResult<List<Blog>>> findByTitle(@PathVariable String title) {
        List<Blog> blogs = blogService.findByTitle(title);
        if (blogs.size() == 0)
            throw new BlogNotFoundException("No blog title start or end with " + title + " !");
        return ResponseEntity.ok(new ResponseResult<List<Blog>>(
                new Date(),
                true,
                "Fuzzy search for blogs",
                "List all blogs that title start with or end with " + title,
                blogs
        ));
    }


    @ApiOperation(
            value = "list all of the tags from database",
            produces = "application/json")

    @GetMapping("api/tags")
    public ResponseEntity<ResponseResult<List<Tag>>> listTags(
            @ApiParam(
                    name = "number",
                    type = "Integer",
                    value = "It's not require. If there is a integer value, the result will return the top 'number' tags",
                    required = false)
            @RequestParam(required = false) Integer number) {
        List<Tag> tags;
        if (number == null)
            tags = blogService.listTags();
        else
            tags = blogService.mostNPopular(number);

        if (tags == null)
            throw new TagNotFoundException("No tag found!");
        return number == null ? ResponseEntity.ok(new ResponseResult<List<Tag>>(
                new Date(),
                true,
                "List all of the tags",
                "Return all the tags",
                tags
        )) : ResponseEntity.ok(new ResponseResult<List<Tag>>(
                new Date(),
                true,
                "List all of the tags",
                "Return the top " + number + " tags",
                tags
        ));
    }

    @ApiOperation(
            value = "update the target blog",
            produces = "application/json")
    @PutMapping("api/blog")
    public ResponseEntity<ResponseResult<Blog>> updateBlog(@RequestBody Blog blog) {
        if (blogService.findById(blog.getBlogId()) == null) {
            throw new BlogNotFoundException("The blog " + blog.getBlogId() + " is not found!");
        }
        blogService.updateBlog(blog);
        return ResponseEntity.ok(new ResponseResult<Blog>(
                new Date(),
                true,
                "Update blog success!",
                "Blog " + blog.getBlogId() + " has been updated",
                blog
        ));
    }

    @ApiOperation(
            value = "create a blog",
            produces = "application/json")
    @PostMapping("api/blog")
    public ResponseEntity<ResponseResult<Blog>> saveBlog(@RequestBody Blog blog) {
        blogService.save(blog);
        return ResponseEntity.ok(new ResponseResult<Blog>(
                new Date(),
                true,
                "Create blog success!",
                "Blog " + blog.getBlogId() + " has been created",
                blog
        ));
    }

    @ApiOperation(
            value = "delete a blog",
            produces = "application/json")
    @DeleteMapping("api/blog/id/{id}")
    public ResponseEntity<ResponseResult<Blog>> deleteBlog(@PathVariable String id) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            throw new BlogNotFoundException("The blog " + id + " is not found!");
        }

        blogService.delete(id);
        return ResponseEntity.ok(new ResponseResult<Blog>(
                new Date(),
                true,
                "Delete blog success!",
                "Blog " + id + " has been deleted",
                blog
        ));
    }




}
