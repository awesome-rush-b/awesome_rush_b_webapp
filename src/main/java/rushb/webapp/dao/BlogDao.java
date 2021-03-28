package rushb.webapp.dao;

import rushb.webapp.model.Blog;

import java.util.List;
import java.util.Set;

public interface BlogDao {
    List<Blog> list();

    List<Blog> listByAuthorId(String authorId);

    List<Blog> listByTag(String tag);

    Blog findById(String id);

    List<Blog> findByTitle(String title);

    void updateBlog(Blog blog);

    void save(Blog blog);

    void delete(String blogId);

    Set<String> listBlogTags(String blogId);

    void blogViewPlusOne(String blogId);
}
