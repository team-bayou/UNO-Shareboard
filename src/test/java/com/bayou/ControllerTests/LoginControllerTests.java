package com.bayou.ControllerTests;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.impl.LoginView;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by joshuaeaton on 2/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginControllerTests {
    private TestRestTemplate rest = new TestRestTemplate();

    @Test
    public void testLoginUser() throws NotFoundException {
        final LoginView mockView = Mocks.getLoginView();
        HttpEntity<LoginView> entity = new HttpEntity<>(mockView, Server.createHeadersJson());

        final ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + "/login", entity, LoginView.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
