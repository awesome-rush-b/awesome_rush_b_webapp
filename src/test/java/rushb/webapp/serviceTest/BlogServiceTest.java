//package rushb.webapp.serviceTest;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import rushb.webapp.dao.BlogDao;
//import rushb.webapp.dao.TagDao;
//import rushb.webapp.dao.UserDao;
//import rushb.webapp.model.Blog;
//import rushb.webapp.model.Tag;
//import rushb.webapp.model.User;
//import rushb.webapp.service.BlogService;
//import rushb.webapp.service.BlogServiceImpl;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import java.util.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class BlogServiceTest {
//
//    private BlogService blogService;
//
//    @MockBean
//    private BlogDao blogDao;
//
//    @MockBean
//    private TagDao tagDao;
//
//    @MockBean
//    private UserDao userDao;
//
//    // Test configuration
//    private final Blog blog = new Blog();
//    private final Date testDate = new Date();
//    private final String blogId = "test-id-for-blog-service";
//    private final String title = "testTitle";
//    private final Date createDate = testDate;
//    private final Date modifyDate = new Date(testDate.getTime()+120*1000);
//    private final Set<Tag> hashTag = new HashSet<>();
//    private final String status = "testStatus";
//    private final String authorId = "test-authorId-for-blog-service";
//    private final String content = "testContent";
//
//    private final Tag tag = new Tag();
//    private final String tagId = "test-tagId-for-blog-service";
//    private final String tagName = "testTagName";
//    private final int count = 521024;
//
//    private final User user = new User();
//    private final String username = "testUserUsername";
//    private final String email = "testUserEmail@test.com";
//    private final String password = "testUserPassword";
//    private final String userId = "test-userId-for-blog-service";
//
//    @Before
//    public void setUp(){
//        // inject the bean that need to be tested
//        blogService = new BlogServiceImpl(blogDao,tagDao,userDao);
//
//        // set up the test configuration
//        tag.setTagId(tagId);
//        tag.setName(tagName);
//        tag.setCount(count);
//
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setUserId(userId);
//
//        hashTag.add(tag);
//        blog.setBlogId(blogId);
//        blog.setTitle(title);
//        blog.setCreateDate(createDate);
//        blog.setModifyDate(modifyDate);
//        blog.setHashTag(hashTag);
//        blog.setStatus(status);
//        blog.setAuthorId(authorId);
//        blog.setContent(content);
//
//        // set up the mock methods start --------------------------------------------------
//        // ****************** tagDao ******************
//        Mockito.when(tagDao.list()).thenReturn(hashTag);
//        Mockito.when(tagDao.findById(tagId)).thenReturn(tag);
//        Mockito.when(tagDao.findByName(tagName)).thenReturn(tag);
//        Mockito.when(tagDao.mostNPopular(1)).thenReturn(Arrays.asList(tag));
//        Mockito.when(tagDao.mostNPopular(0)).thenReturn(Arrays.asList());
//        // updateTag returns void
//        // save returns void
//        // delete returns void
//        // detachTag returns void
//
//        // ****************** userDao ******************
//        Mockito.when(userDao.list()).thenReturn(Arrays.asList(user));
//        Mockito.when(userDao.findByName(username)).thenReturn(user);
//        Mockito.when(userDao.findById(userId)).thenReturn(user);
//        Mockito.when(userDao.findByEmail(email)).thenReturn(user);
//        // updateUser returns void
//        // save returns void
//        // delete returns void
//
//        // ****************** blogDao ******************
//        Mockito.when(blogDao.list()).thenReturn(Arrays.asList(blog));
//        Mockito.when(blogDao.listByAuthorId(authorId)).thenReturn(Arrays.asList(blog));
//        Mockito.when(blogDao.listByTag(tagName)).thenReturn(Arrays.asList(blog));
//        Mockito.when(blogDao.findById(blogId)).thenReturn(blog);
//        Mockito.when(blogDao.findByTitle(title)).thenReturn(blog);
//
//        Set<String> tagNames = new HashSet<>();
//        tagNames.add(tag.getName());
//        Mockito.when(blogDao.listBlogTags(blogId)).thenReturn(tagNames);
//        // updateBlog returns void
//        // save returns void
//        // delete returns void
//
//        // set up the mock methods end --------------------------------------------------
//    }
//
//    /**
//     * Test if list can return all blogs
//     */
//    @Test
//    public void testList(){
//        List<Blog> result = blogService.list();
//        assertThat(result.get(0)).isEqualTo(blog);
//    }
//
//    /**
//     * Test if listByAuthorId can return all blogs by (authorId)
//     */
//    @Test
//    public void testListByAuthorId(){
//        List<Blog> result = blogService.listByAuthorId(authorId);
//        assertThat(result.get(0)).isEqualTo(blog);
//    }
//
//    /**
//     * Test if listByAuthorName can return all blogs by (username)
//     */
//    @Test
//    public void testListByAuthorName(){
//        List<Blog> result = blogService.listByAuthorName(username);
//        assertThat(result.get(0)).isEqualTo(blog);
//    }
//
//    /**
//     * Test if listByTag can return all blogs by (tagName)
//     */
//    @Test
//    public void testListByTag(){
//        List<Blog> result = blogService.listByTag(tagName);
//        assertThat(result.get(0)).isEqualTo(blog);
//    }
//
//    /**
//     * Test if findByTitle can return blog by (title)
//     */
//    @Test
//    public void testFindByTitle(){
//        Blog result = blogService.findByTitle(title);
//        assertThat(result).isEqualTo(blog);
//    }
//
//    /**
//     * Test if updateBlog is correctly executed by (blog)
//     * The function should add a new tag (modifiedTag)
//     * The function should detach a old tag (tag)
//     */
//    @Test
//    public void testUpdateBlog(){
//        // create a modified blog
//        // there will be 1 new tags and detach one old tag
//        Blog modifiedBlog = new Blog();
//        modifiedBlog.setBlogId(blogId);
//        modifiedBlog.setTitle(title);
//        modifiedBlog.setCreateDate(createDate);
//        modifiedBlog.setModifyDate(modifyDate);
//        modifiedBlog.setStatus(status);
//        modifiedBlog.setAuthorId(authorId);
//        modifiedBlog.setContent(content);
//
//        Set<Tag> modifiedTags = new HashSet<>();
//        Tag modifiedTag = new Tag();
//        modifiedTag.setName("newTag");
//        modifiedTag.setTagId(tagId+"modified");
//        modifiedTags.add(modifiedTag);
//
//        modifiedBlog.setHashTag(modifiedTags);
//
//        blogService.updateBlog(modifiedBlog);
//
//        verify(blogDao,times(1)).listBlogTags(modifiedBlog.getBlogId());
//        verify(tagDao,times(1)).detachTag(tag);
//        verify(tagDao,times(1)).save(modifiedTag);
//    }
//
//    /**
//     * Test if save can save blog correctly by (blog)
//     * The function should keep the old tag that have been add before
//     * The function should add new tag that didn't appear before
//     */
//    @Test
//    public void testSave(){
//        // The function should keep the old tag that have been add before
//        blogService.save(blog);
//        verify(tagDao,times(1)).updateTag(tag);
//        verify(blogDao,times(1)).save(blog);
//
//        // The function should add new tag that didn't appear before
//        // create a modified blog
//        // there will be 1 new tags and detach one old tag
//        Blog modifiedBlog = new Blog();
//        modifiedBlog.setBlogId(blogId+"modified");
//        modifiedBlog.setTitle(title);
//        modifiedBlog.setCreateDate(createDate);
//        modifiedBlog.setModifyDate(modifyDate);
//        modifiedBlog.setStatus(status);
//        modifiedBlog.setAuthorId(authorId);
//        modifiedBlog.setContent(content);
//
//        Set<Tag> modifiedTags = new HashSet<>();
//        Tag modifiedTag = new Tag();
//        modifiedTag.setName("newTag");
//        modifiedTag.setTagId(tagId+"modified");
//        modifiedTags.add(modifiedTag);
//
//        modifiedBlog.setHashTag(modifiedTags);
//
//        blogService.save(modifiedBlog);
//        verify(tagDao,times(1)).save(modifiedTag);
//        verify(blogDao,times(1)).save(modifiedBlog);
//
//    }
//
//    /**
//     * Test if the delete can delete the blog by (blogId)
//     */
//    @Test
//    public void testDelete(){
//        blogService.delete(blogId);
//        verify(blogDao,times(1)).delete(blogId);
//        verify(tagDao,times(1)).detachTag(tag);
//    }
//
//    @Test
//    public void testMostNPopular(){
//        blogService.mostNPopular(1);
//        verify(tagDao,times(1)).mostNPopular(1);
//    }
//
//    /**
//     * Test if the listTags can return all tags about blog in a list
//     */
//    @Test
//    public void testListTags(){
//        List<Tag> result = blogService.listTags();
//        assertThat(result.get(0)).isEqualTo(tag);
//    }
//
//}
