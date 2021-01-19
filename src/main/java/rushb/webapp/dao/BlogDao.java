package rushb.webapp.dao;

import rushb.webapp.model.Blog;

import java.util.List;

public interface BlogDao {
    List<Blog> list();

    List<Blog> listByAuthorId(String authorId);

//    List<Blog> listByAuthorName(String name);

    List<Blog> listByTag(String tag);

    Blog findById(String id);

    Blog findByTitle(String title);

    void updateBlog(Blog blog);

    void save(Blog blog);

    void delete(String blogId);
}
