package rushb.webapp.service;


import rushb.webapp.model.Blog;
import rushb.webapp.model.Tag;

import java.util.List;

public interface BlogService {

    List<Blog> list();

    List<Blog> listByAuthorId(String authorId);

    List<Blog> listByAuthorName(String username);

    List<Blog> listByTag(String tag);

    Blog findById(String blogId);

    Blog findByTitle(String title);

    void updateBlog(Blog blog);

    void save(Blog blog);

    void delete(String blogId);

//    void permDeleteTag(String tagId);

    List<Tag> mostNPopular(int n);
}
