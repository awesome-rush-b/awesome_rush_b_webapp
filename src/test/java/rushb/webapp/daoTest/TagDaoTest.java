package rushb.webapp.daoTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rushb.webapp.dao.TagDao;
import rushb.webapp.dao.TagDaoImpl;
import rushb.webapp.mapper.BlogTagMapper;
import rushb.webapp.mapper.TagMapper;
import rushb.webapp.model.Tag;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class TagDaoTest {

    private TagDao tagDao;

    @MockBean
    private TagMapper tagMapper;

    @MockBean
    private BlogTagMapper blogTagMapper;

    // Test configuration
    private final Tag tag = new Tag();
    private final String tagId = "test-tagId-for-blog-service";
    private final String tagName = "testTagName";
    private final int count = 2;

    @Before
    public void setUp(){
        // set up configuration
        tag.setTagId(tagId);
        tag.setName(tagName);
        tag.setCount(count);
        Set<Tag> sample = Collections.singleton(tag);
        // set up return content for mockbean
        Mockito.when(tagMapper.list()).thenReturn(sample);
        Mockito.when(tagMapper.findByName(tag.getName())).thenReturn(tag);
        Mockito.when(tagMapper.findById(tag.getTagId())).thenReturn(tag);

        tagDao = new TagDaoImpl(tagMapper);
    }

    @Test
    public void testList(){
        Set<Tag> tags = tagDao.list();
        assertThat(tags.iterator().next()).isEqualTo(tag);
    }

    @Test
    public void testFindById(){
        assertThat(tagDao.findById(tag.getTagId())).isEqualTo(tag);
    }

    @Test
    public void testFindByName(){
        assertThat(tagDao.findByName(tag.getName())).isEqualTo(tag);
    }

    @Test
    public void testUpdateTag(){
        Tag test = new Tag();
        test.setCount(0);
        test.setTagId("testTagId");
        test.setName("testTagName");
        tagDao.updateTag(test);
        verify(tagMapper, times(1)).delete(test.getTagId());

        tagDao.updateTag(tag);
        verify(tagMapper, times(1)).update(tag);
    }

    @Test
    public void testSave(){
        tagDao.save(tag);
        verify(tagMapper, times(1)).save(tag);
    }

    @Test
    public void testDelete(){
        tagDao.delete(tag.getTagId());
        verify(tagMapper, times(1)).delete(tag.getTagId());
    }

    @Test
    public void testMostNPopular(){
        int n = 10;

        // generate some test case into Dao
        for(int i = 0; i < n; i++){
            Tag test = new Tag();
            test.setTagId("testid"+i);
            test.setName("test"+i);
            test.setCount(i+10);
            tagDao.save(test);
        }

        tagDao.save(tag);

        // test if the mostNPopular return a descending order list
        List<Tag> tagList = tagDao.mostNPopular(11);
        assertThat(tagList.get(0).getCount()).isEqualTo(19);
        assertThat(tagList.get(n).getCount()).isEqualTo(tag.getCount());

        // test if the mostNPopular handle the input correctly
        tagList = tagDao.mostNPopular(1000);
        assertThat(tagList.size()).isEqualTo(n+1);


    }

    @Test
    public void testDetachTag(){
        tagDao.detachTag(tag);
        assertThat(tag.getCount()).isEqualTo(count-1);
        tagDao.detachTag(tag);
        verify(tagMapper, times(1)).delete(tag.getTagId());
    }



}
