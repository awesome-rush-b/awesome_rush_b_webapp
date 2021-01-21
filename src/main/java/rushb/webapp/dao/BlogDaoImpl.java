package rushb.webapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rushb.webapp.mapper.BlogMapper;
import rushb.webapp.mapper.BlogTagMapper;
import rushb.webapp.mapper.TagMapper;
import rushb.webapp.model.Blog;
import rushb.webapp.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class BlogDaoImpl implements BlogDao{
    private final BlogMapper blogMapper;
    private final BlogTagMapper blogTagMapper;
    private final TagMapper tagMapper;

    @Autowired
    public BlogDaoImpl(BlogMapper blogMapper, BlogTagMapper blogTagMapper, TagMapper tagMapper) {
        this.blogMapper = blogMapper;
        this.blogTagMapper = blogTagMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<Blog> list() {
        List<Blog> blogList = blogMapper.list();
        for(Blog blog : blogList){
            Set<String> tags = blogTagMapper.listByBlogId(blog.getBlogId());
            Set<Tag> tagSet = blog.getHashTag();
            for(String tagId : tags){
                tagSet.add(tagMapper.findById(tagId));
            }
            blog.setHashTag(tagSet);
        }
        return blogList;
    }

    @Override
    public List<Blog> listByAuthorId(String authorId) {
        List<Blog> blogList = blogMapper.listByAuthorId(authorId);
        for(Blog blog : blogList){
            Set<String> tags = blogTagMapper.listByBlogId(blog.getBlogId());
            Set<Tag> tagSet = blog.getHashTag();
            for(String tagId : tags){
                tagSet.add(tagMapper.findById(tagId));
            }
            blog.setHashTag(tagSet);
        }
        return blogList;
    }

    @Override
    public List<Blog> listByTag(String tag) {
        Set<String> blogIdList = blogTagMapper.listByTagId(tag);
        List<Blog> blogList = new ArrayList<>();
        for(String blogId : blogIdList){
            Blog blog = blogMapper.findById(blogId);
            Set<String> tags = blogTagMapper.listByBlogId(blog.getBlogId());
            Set<Tag> tagSet = blog.getHashTag();
            for(String tagId : tags){
                tagSet.add(tagMapper.findById(tagId));
            }
            blog.setHashTag(tagSet);
            blogList.add(blog);
        }
        return blogList;
    }

    @Override
    public Blog findById(String id) {
        Blog blog = blogMapper.findById(id);
        Set<String> tags = blogTagMapper.listByBlogId(blog.getBlogId());
        Set<Tag> tagSet = blog.getHashTag();
        for(String tagId : tags){
            tagSet.add(tagMapper.findById(tagId));
        }
        blog.setHashTag(tagSet);
        return blog;
    }

    @Override
    public Blog findByTitle(String title) {
        Blog blog = blogMapper.findByTitle(title);
        Set<String> tags = blogTagMapper.listByBlogId(blog.getBlogId());
        Set<Tag> tagSet = blog.getHashTag();
        for(String tagId : tags){
            tagSet.add(tagMapper.findById(tagId));
        }
        blog.setHashTag(tagSet);
        return blog;
    }

    @Override
    public void updateBlog(Blog blog) {

        blogTagMapper.deleteBlog(blog.getBlogId());
        for(Tag tag : blog.getHashTag()){
            blogTagMapper.save(blog.getBlogId(), tag.getTagId());
        }
        blogMapper.updateBlog(blog);
    }

    @Override
    public void save(Blog blog) {
        blogMapper.save(blog);
        for(Tag tag : blog.getHashTag()){
            blogTagMapper.save(blog.getBlogId(), tag.getTagId());
        }
    }

    @Override
    public void delete(String blogId) {
        blogMapper.deleteBlog(blogId);
        blogTagMapper.deleteBlog(blogId);
    }
}
