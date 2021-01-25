package rushb.webapp.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rushb.webapp.dao.SubscriberDao;
import rushb.webapp.model.Subscriber;
import rushb.webapp.service.SubscriberService;
import rushb.webapp.service.SubscriberServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class SubscriberServiceTest {

    private SubscriberService subscriberService;

    @MockBean
    private SubscriberDao subscriberDao;

    // Test configuration
    private final String subscriberId = "test-id-for-subscriber-service";
    private final String email = "test@test.com";
    private final String userId = "testUserId";
    private final String name = "testSubscriberService";
    private final Subscriber subscriber = new Subscriber();

    @Before
    public void setUp(){
        // inject the bean that need to be tested
        subscriberService = new SubscriberServiceImpl(subscriberDao);

        // set up the test subscriber
        subscriber.setSubscriberId(subscriberId);
        subscriber.setEmail(email);
        subscriber.setUserId(userId);
        subscriber.setName(name);

        // set up the test subscriber list
        List<Subscriber> allSubscriber = Arrays.asList(subscriber);

        // set up the mock methods start --------------------------------------------------
        Mockito.when(subscriberDao.list()).thenReturn(allSubscriber);

        Mockito.when(subscriberDao.listByAuthorId(subscriber.getUserId())).thenReturn(allSubscriber);

        Mockito.when(subscriberDao.findByName(subscriber.getName())).thenReturn(subscriber);

        Mockito.when(subscriberDao.findById(subscriber.getSubscriberId())).thenReturn(subscriber);

        Mockito.when(subscriberDao.findByEmail(subscriber.getEmail())).thenReturn(subscriber);

        // updateSub returns void

        // save returns void

        // delete returns void

        // set up the mock methods end --------------------------------------------------
    }

    /**
     * Test if list can return a list of subscriber
     */
    @Test
    public void testList(){
        List<Subscriber> result = subscriberService.list();
        assertThat(result.get(0).getName()).isEqualTo(name);
    }

    /**
     * Test if listByAuthorId can return the correct result by authorid (userId)
     */
    @Test
    public void testListByAuthorId(){
        // test if the function can return the list by finding correct subscriber
        List<Subscriber> resultWithSubscriber = subscriberService.listByAuthorId(userId);
        assertThat(resultWithSubscriber.get(0).getName()).isEqualTo(name);

        // test if the function can return the correct list without finding the subscriber.
        List<Subscriber> resultWithoutSubscriber = subscriberService.listByAuthorId("");
        assertThat(resultWithoutSubscriber.size()).isEqualTo(0);
    }

    /**
     * Test if findByName can return the correct subscriber by subscriber's name (name)
     */
    @Test
    public void testFindByName(){
        // Test if the function can return the correct subscriber
        Subscriber result = subscriberService.findByName(name);
        assertThat(result.getName()).isEqualTo(name);
    }

    /**
     * Test if findById can return the correct subscriber by subscriber's id (subscriberId)
     */
    @Test
    public void testFindById(){
        // Test if the function can return the correct subscriber
        Subscriber result = subscriberService.findById(subscriberId);
        assertThat(result.getSubscriberId()).isEqualTo(subscriberId);
    }

    /**
     * Test if findByEmail can return the correct subscriber by subscriber's email (email)
     */
    @Test
    public void testFindByEmail(){
        // Test if the function can return the correct subscriber
        Subscriber result = subscriberService.findByEmail(email);
        assertThat(result.getEmail()).isEqualTo(email);
    }

    /**
     * Test if updateSub is executed by inputting (subscriber)
     */
    @Test
    public void testUpdateSub(){
        // Test if updateSub is executed by inputting (subscriber)
        subscriberService.updateSub(subscriber);
        verify(subscriberDao,times(1)).updateSub(subscriber);
    }

    /**
     * Test if updateSub is executed by inputting (subscriber)
     */
    @Test
    public void testSave(){
        // Test if updateSub is executed by inputting (subscriber)
        subscriberService.save(subscriber);
        verify(subscriberDao,times(1)).save(subscriber);
    }

    /**
     * Test if updateSub is executed by inputting (subscriber)
     */
    @Test
    public void testDelete(){
        // Test if updateSub is executed by inputting (subscriber)
        subscriberService.delete(subscriberId);
        verify(subscriberDao,times(1)).delete(subscriberId);
    }
}
