package rushb.webapp.service;

import org.springframework.stereotype.Service;
import rushb.webapp.dao.BlogDao;
import rushb.webapp.dao.TagDao;
import rushb.webapp.dao.UserDao;
import rushb.webapp.model.Blog;
import rushb.webapp.model.Tag;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogDao blogDao;
    private TagDao tagDao;
    private UserDao userDao;

    public BlogServiceImpl(BlogDao blogDao, TagDao tagDao, UserDao userDao) {
        this.blogDao = blogDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
    }


    @Override
    public List<Blog> list() {
        return blogDao.list();
    }

    @Override
    public List<Blog> listByAuthorId(String authorId) {
        return blogDao.listByAuthorId(authorId);
    }

    @Override
    public List<Blog> listByAuthorName(String username) {
        String userId = userDao.findByName(username).getUserId();
        return this.listByAuthorId(userId);
    }

    @Override
    public List<Blog> listByTag(String tag) {
        return blogDao.listByTag(tag);
    }

    @Override
    public Blog findById(String blogId) {
        return blogDao.findById(blogId);
    }

    @Override
    public Blog findByTitle(String title) {
        return blogDao.findByTitle(title);
    }

    //TODO implement the logic: Updating Tag along with Updating Blog. When a Tag is detached, Tag.Count--, On the opposite, when a Tag is attached, Tag.Count++;
    @Override
    public void updateBlog(Blog blog) {
        blogDao.updateBlog(blog);
    }

    //TODO implement the logic: Updating Tag along with Updating Blog. When a Tag is detached, Tag.Count--, On the opposite, when a Tag is attached, Tag.Count++;
    @Override
    public void save(Blog blog) {

    }

    //TODO implement the logic: Updating Tag along with Updating Blog. When a Tag is detached, Tag.Count--, On the opposite, when a Tag is attached, Tag.Count++;
    @Override
    public void delete(String blogId) {

    }

    //TODO implement API in TagDAO
    @Override
    public List<Tag> mostNPopular(int n) {
        return null;
    }
}
