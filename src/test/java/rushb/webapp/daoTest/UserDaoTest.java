package rushb.webapp.daoTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rushb.webapp.dao.UserDao;
import rushb.webapp.dao.UserDaoImpl;
import rushb.webapp.mapper.UserMapper;
import rushb.webapp.model.User;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
    private UserDao userDao;

    @MockBean
    private UserMapper userMapper;

    // Test configuration
    private final String username = "jonah";
    private final String email = "jonah@gmail.com";
    private final String userId = "userId";
    private final String password = "password";

    private final User user = new User();

    @Before
    public void setUp(){
        userDao = new UserDaoImpl(userMapper);

        //Set up the test case User
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        //set up the test case User list
        List<User> userList = Collections.singletonList(user);

        // set up the return content for MockBean
        Mockito.when(userMapper.list()).thenReturn(userList);
        Mockito.when(userMapper.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(userMapper.findByName(user.getUsername())).thenReturn(user);
        Mockito.when(userMapper.findById(user.getUserId())).thenReturn(user);

    }

    @Test
    public void testList(){
        List<User> userList = userDao.list();
        assertThat(userList.get(0)).isEqualTo(user);
    }

    @Test
    public void testFindByEmail(){
        User test = userDao.findByEmail(user.getEmail());
        assertThat(test).isEqualTo(user);
    }

    @Test
    public void testFindByUserName(){
        User test = userDao.findByName(user.getUsername());
        assertThat(test).isEqualTo(user);
    }

    @Test
    public void testFindByUserId(){
        User test = userDao.findById(user.getUserId());
        assertThat(test).isEqualTo(user);
    }

    @Test
    public void testUpdateUser(){
        userDao.updateUser(user);
        verify(userMapper, times(1)).updateUser(user);
    }

    @Test
    public void testSave(){
        userDao.save(user);
        verify(userMapper, times(1)).save(user);
    }

    @Test
    public void testDelete(){
        userDao.delete(user.getUserId());
        verify(userMapper, times(1)).deleteUser(user.getUserId());
    }
}
