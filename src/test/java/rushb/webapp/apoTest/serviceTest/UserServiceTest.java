package rushb.webapp.apoTest.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rushb.webapp.dao.UserDao;
import rushb.webapp.model.User;
import rushb.webapp.service.UserService;
import rushb.webapp.service.UserServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    private UserService service;

    @MockBean
    private UserDao userDao;

    // Test configuration
    private final String username = "jonah";
    private final String email = "jonah@gmail.com";
    private final String userId = "userId";
    private final String password = "password";
    private final User user = new User();

    @Before
    public void setUP(){
        //inject the MockBean into the UserService
        service = new UserServiceImpl(userDao);

        //Set up the test case User
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        //set up the test case User list
        List<User> userList = Collections.singletonList(user);

        //set up the return content for the non-void methods
        Mockito.when(userDao.list()).thenReturn(userList);
        Mockito.when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(userDao.findById(user.getUserId())).thenReturn(user);
        Mockito.when(userDao.findByName(user.getUsername())).thenReturn(user);

    }


    /**
     * Test if list can return a list of user
     */
    @Test
    public void testList(){
        List<User> result = service.list();
        assertThat(result.get(0).getUsername()).isEqualTo(user.getUsername());
    }


    /**
     * Test if findByEmail is functional
     */
    @Test
    public void testFindByEmail(){
        User test = service.findByEmail(user.getEmail());
        assertThat(test).isEqualTo(user);
    }

    /**
     * Test if findByUserName is functional
     */
    @Test
    public void testFindByUsername(){
        User test = service.findByName(user.getUsername());
        assertThat(test).isEqualTo(user);
    }

    /**
     * Test if findByUserId is functional
     */
    @Test
    public void testFindByUserId(){
        User test = service.findById(user.getUserId());
        assertThat(test).isEqualTo(user);
    }

    /**
     * Test if updateUser is functional
     */
    @Test
    public void testUpdateUser(){
        service.updateUser(user);
        verify(userDao, times(1)).updateUser(user);
    }

    /**
     * Test if save is functional
     */
    @Test
    public void testSave(){
        service.save(user);
        verify(userDao, times(1)).save(user);
    }

    /**
     * Test if delete is functional
     */
    @Test
    public void testDelete(){
        service.delete(user.getUserId());
        verify(userDao, times(1)).delete(user.getUserId());
    }
}
