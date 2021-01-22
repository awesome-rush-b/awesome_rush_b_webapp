package rushb.webapp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rushb.webapp.controller.SubscriberController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class  SubscriberControllerTest {

    private SubscriberController subscriberController;

    @Autowired
    public SubscriberControllerTest(SubscriberController subscriberController){
        this.subscriberController = subscriberController;
    }

    /**
     * Test if the controller is injected before the test methods are run.
     * @throws Exception
     */
    @Test
    public void contextLoads() throws Exception{
        assertThat(subscriberController).isNotNull();
    }


}
