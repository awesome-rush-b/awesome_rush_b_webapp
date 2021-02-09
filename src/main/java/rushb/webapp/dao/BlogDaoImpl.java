package rushb.webapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rushb.webapp.mapper.BlogMapper;
import rushb.webapp.mapper.BlogTagMapper;
import rushb.webapp.mapper.TagMapper;
import rushb.webapp.model.Blog;
import rushb.webapp.model.Tag;

import java.util.ArrayList;
import java.util.HashSet;
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

    /**TODO 封装到DAO层可能会导致更多的bug，比如service层如果想要做一个删除的操作，就得现找到这个数据，然后进行删除。这里其实并不需要输出数据
     *  而是需要提取数据进行再处理，也就不需要进行封装，which might cost extra operations and time。而且在封装的过程中一部分数据可能已经
     *  从数据库删除，会导致封装过程中找不到数据并且报错
     */

    @Override
    public Blog findById(String id) {
        Blog blog = blogMapper.findById(id);
        Set<String> tags = blogTagMapper.listByBlogId(blog.getBlogId());
        Set<Tag> tagSet = new HashSet<>();
        for(String tagId : tags){
            tagSet.add(tagMapper.findById(tagId));
        }
        blog.setHashTag(tagSet);
        return blog;
    }

    @Override
    public List<Blog> findByTitle(String title) {
        List<Blog> blogs = blogMapper.findByTitle(title);
        for(Blog blog : blogs){
            Set<String> tags = blogTagMapper.listByBlogId(blog.getBlogId());
            Set<Tag> tagSet = blog.getHashTag();
            for(String tagId : tags){
                tagSet.add(tagMapper.findById(tagId));
            }
            blog.setHashTag(tagSet);
        }
        return blogs;
    }

    @Override
    public void updateBlog(Blog blog) {

        blogTagMapper.deleteBlog(blog.getBlogId());
        for(Tag tag : blog.getHashTag()){
            tag.setTagId(tagMapper.findByName(tag.getName()).getTagId());
            blogTagMapper.save(blog.getBlogId(), tag.getTagId());
        }
        blogMapper.updateBlog(blog);
    }

    @Override
    public void save(Blog blog) {
        blogMapper.save(blog);
//        blog.setBlogId(this.findByTitle(blog.getTitle()).getBlogId());
        for(Tag tag : blog.getHashTag()){
            tag.setTagId(tagMapper.findByName(tag.getName()).getTagId());
            blogTagMapper.save(blog.getBlogId(), tag.getTagId());
        }
    }

    @Override
    public void delete(String blogId) {
        blogMapper.deleteBlog(blogId);
//        blogTagMapper.deleteBlog(blogId);
    }

    @Override
    public Set<String> listBlogTags(String blogId) {
        return blogTagMapper.listByBlogId(blogId);
    }
}
