package rushb.webapp.apoTest.daoTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rushb.webapp.dao.BlogDao;
import rushb.webapp.dao.BlogDaoImpl;
import rushb.webapp.mapper.BlogMapper;
import rushb.webapp.mapper.BlogTagMapper;
import rushb.webapp.mapper.TagMapper;
import rushb.webapp.model.Blog;
import rushb.webapp.model.Tag;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class BlogDaoTest {
    private BlogDao blogDao;

    @MockBean
    private BlogMapper blogMapper;

    @MockBean
    private TagMapper tagMapper;

    @MockBean
    private BlogTagMapper blogTagMapper;

    // Test configuration
    private final Blog blog = new Blog();
    private final Date testDate = new Date();
    private final String blogId = "test-id-for-blog-service";
    private final String title = "testTitle";
    private final Date createDate = testDate;
    private final Date modifyDate = new Date(testDate.getTime()+120*1000);
    private final Set<Tag> hashTag = new HashSet<>();
    private final String status = "testStatus";
    private final String authorId = "test-authorId-for-blog-service";
    private final String content = "testContent";

    private final Tag tag = new Tag();
    private final String tagId = "test-tagId-for-blog-service";
    private final String tagName = "testTagName";
    private final int count = 521024;

    @Before
    public void setUp(){
        blogDao = new BlogDaoImpl(blogMapper,blogTagMapper,tagMapper);

        // set up the test configuration
        tag.setTagId(tagId);
        tag.setName(tagName);
        tag.setCount(count);

        hashTag.add(tag);
        blog.setBlogId(blogId);
        blog.setTitle(title);
        blog.setCreateDate(createDate);
        blog.setModifyDate(modifyDate);
        blog.setHashTag(hashTag);
        blog.setStatus(status);
        blog.setAuthorId(authorId);
        blog.setContent(content);

        List<Blog> blogList = Collections.singletonList(blog);
        Set<String> tagIds = Collections.singleton(tagId);
        Set<String> blogIds = Collections.singleton(blogId);
        Set<Tag> tagList = Collections.singleton(tag);

        // set up return content form MockBean
        // blogMapper
        Mockito.when(blogMapper.list()).thenReturn(blogList);
        Mockito.when(blogMapper.listByAuthorId(blog.getAuthorId())).thenReturn(blogList);
        Mockito.when(blogMapper.findById(blog.getBlogId())).thenReturn(blog);
        Mockito.when(blogMapper.findByTitle(blog.getTitle())).thenReturn(blog);


        // tagMapper
        Mockito.when(tagMapper.list()).thenReturn(tagList);
        Mockito.when(tagMapper.findById(tag.getTagId())).thenReturn(tag);
        Mockito.when(tagMapper.findByName(tag.getName())).thenReturn(tag);


        // blogTagMapper
        Mockito.when(blogTagMapper.listByBlogId(blogId)).thenReturn(tagIds);
        Mockito.when(blogTagMapper.listByTagId(tagId)).thenReturn(blogIds);
    }

    @Test
    public void testList(){
        List<Blog> blogList = blogDao.list();
        assertThat(blogList.get(0)).isEqualTo(blog);
    }

    @Test
    public void testListByAuthorId(){
        List<Blog> blogList = blogDao.listByAuthorId(blog.getAuthorId());
        assertThat(blogList.get(0)).isEqualTo(blog);
    }

    @Test
    public void testListByTag(){
        List<Blog> blogList = blogDao.listByTag(tag.getTagId());
        assertThat(blogList.get(0)).isEqualTo(blog);
    }

    @Test
    public void testListBlogTags(){
        Set<String> tags = blogDao.listBlogTags(blog.getBlogId());
        assertThat(tags.iterator().next()).isEqualTo(tagId);
    }

    @Test
    public void testFindById(){
        assertThat(blogDao.findById(blog.getBlogId())).isEqualTo(blog);
    }

    @Test
    public void testFindByTitle(){
        assertThat(blogDao.findByTitle(blog.getTitle())).isEqualTo(blog);
    }

    @Test
    public void testUpdateBlog(){
        blogDao.updateBlog(blog);
        verify(blogTagMapper, times(1)).deleteBlog(blog.getBlogId());
        verify(blogTagMapper, times(hashTag.size())).save(blog.getBlogId(), tag.getTagId());
        verify(tagMapper, times(hashTag.size())).findByName(tag.getName());
        verify(blogMapper, times(1)).updateBlog(blog);
    }

    @Test
    public void testSave(){
        blogDao.save(blog);
        verify(blogMapper, times(1)).save(blog);
        verify(tagMapper, times(blog.getHashTag().size())).findByName(tag.getName());
        verify(blogTagMapper, times(blog.getHashTag().size())).save(blog.getBlogId(), tag.getTagId());
    }

    @Test
    public void testDelete(){
        blogDao.delete(blog.getBlogId());
        verify(blogMapper, times(1)).deleteBlog(blog.getBlogId());
    }

}
