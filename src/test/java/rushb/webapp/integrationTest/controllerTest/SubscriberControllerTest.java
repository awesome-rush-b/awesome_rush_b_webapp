//package rushb.webapp.integrationTest.controllerTest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.json.JSONObject;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SubscriberControllerTest {
//
//    protected static final Log logger = LogFactory.getLog(this.getClass());
//
//    @LocalServerPort
//    private static int port;
//
//    @Autowired
//    TestRestTemplate restTemplate;
//
////    |**** Test class should have exactly one public zero-argument constructor ****|
//
//    /**
//     * Create an user for subscriber controller test
//     * It will be deleted at the end of the subscriber controller test
//     *
//     * If this test doesn't work, please check the controller of "post /api/user"
//     */
//    private static String username;
//    private static String email;
//    private static String password;
//    private static String userId;
//    @BeforeClass
//    public static void init() throws Exception {
//        logger.info("------------------SubscriberController test start------------------");
//        logger.info("Create test user");
//
//        // Test configuration -----------------------------------------
//        this.username = "test_user_subscriber_controller_test";
//        this.email = "test_user_subscriber_controller_test@test.com";
//        this.password = "test_user_subscriber_controller_test";
//
//        String testApi = "/api/use";
//        String testUrl = "http://localhost:" + port + testApi;
//
//        // set the Content-Type header to application/json.
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // wrap the form variables into json object map
//        JSONObject map = new JSONObject();
//        map.put("username", username);
//        map.put("email", email);
//        map.put("password", password);
//
//        // create request entity
//        HttpEntity<String> request = new HttpEntity<>(map.toString(), headers);
//
//        // send request and gain response
//        ResponseEntity<String> response = restTemplate.exchange(testUrl, HttpMethod.POST, request, String.class);
//
//        // get uuid
//        userId = response.getBody().split(" ")[1];
//
//        logger.info("create test user: "+userId);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//
//    /**
//     * Test create subscription api
//     */
//    @Test
//    public void createSubscription() throws Exception {
//        logger.info("test create subscription api: 'post /api/sub'");
//
//        // Test configuration -----------------------------------------
//        String testApi = "/api/sub";
//        String testUrl = "http://localhost:" + port + testApi;
//
//        String name = "test_create_subscriber";
//        String email = "test_create_subscriber@test.com";
////        String testUserId = userId;
//        String testUserId = "userId";
//
//        // set the Content-Type header to application/json.
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // wrap the form variables into json object map
//        JSONObject map = new JSONObject();
//        map.put("name", name);
//        map.put("email", email);
//        map.put("userId", testUserId);
//
//        // create request entity
//        HttpEntity<String> request = new HttpEntity<>(map.toString(), headers);
//
//        // send request and gain response
//        ResponseEntity<String> response = restTemplate.exchange(testUrl, HttpMethod.POST, request, String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//    }
//
//    @Test
//    public void cancelSubscription() throws Exception {
//
//    }
//
//    @Test
//    public void listAllOfTheSubscribersFromDatabase() throws Exception {
//
//    }
//
//    /**
//     * delete the temporary user
//     *
//     * If this test doesn't pass, please check the controller of "delete /api/user/{id}"
//     */
//    @AfterClass
//    public static void teardown() {
////        String testApi = "api/user/" + userId;
////        String testUrl = "http://localhost:" + port + testApi;
////
////
////        // set the Content-Type header to application/json.
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////
////        HttpEntity<String> request = new HttpEntity<>(headers);
////
////        ResponseEntity<String> response = restTemplate.exchange(testUrl, HttpMethod.DELETE, request, String.class);
////
////        logger.info(userId);
////
////        assertThat(response.getBody()).contains(userId);
////        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
////
////        logger.info("Deleting test user");
////        logger.info("------------------SubscriberController test end------------------");
//    }
//}
