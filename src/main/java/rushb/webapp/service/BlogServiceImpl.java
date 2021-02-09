package rushb.webapp.service;

import org.springframework.stereotype.Service;
import rushb.webapp.dao.BlogDao;
import rushb.webapp.dao.TagDao;
import rushb.webapp.dao.UserDao;
import rushb.webapp.model.Blog;
import rushb.webapp.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<Blog> findByTitle(String title) {
        return blogDao.findByTitle(title);
    }

    //TODO implement the logic: If blog detached a tag from original copy, detect it and do detach operation!!!!
    @Override
    public void updateBlog(Blog blog) {
        Set<Tag> tags = blog.getHashTag();
        Set<String> tagIds = blogDao.listBlogTags(blog.getBlogId());
        for(Tag tag : tags) {
            //if the tag dose not contains tagId, then it is a new tag, create a record for it
            if (tagDao.findById(tag.getTagId()) == null)
                tagDao.save(tag);
            //if the tag contains a tagId, then it has records, update then
            else {
                tagDao.updateTag(tag);
                tagIds.remove(tag.getTagId());
            }
        }

        //if there is tag in original blog, but not appears in the new version, then detach it
        for(String tagId : tagIds){
            tagDao.detachTag(tagDao.findById(tagId));
        }

        blogDao.updateBlog(blog);
    }

    @Override
    public void save(Blog blog) {

        Set<Tag> tags = blog.getHashTag();
        for(Tag tag : tags)
            if(tagDao.findByName(tag.getName()) == null)
                tagDao.save(tag);
            else
                tagDao.updateTag(tag);
        blogDao.save(blog);
    }

    @Override
    public void delete(String blogId) {
        Blog blog = blogDao.findById(blogId);
        blogDao.delete(blogId);
        Set<Tag> tags = blog.getHashTag();
        for(Tag tag : tags)
            tagDao.detachTag(tag);
    }

    //TODO implement API in TagDAO
    @Override
    public List<Tag> mostNPopular(int n) {
        return tagDao.mostNPopular(n);
    }

    @Override
    public List<Tag> listTags() {
        return new ArrayList<Tag>(tagDao.list());
    }
}
