package com.bayou.ControllerTests;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
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
        // Create user view and add user to db.
        UserView userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + "/users/add", new HttpEntity<>(userView, Server.createHeadersJson()), Long.class);
        userView.setId(entity.getBody());

        // Create login view from user view and perform login.
        LoginView view = new LoginView();
        view.setAccountName(userView.getAccountName());
        view.setEmail(userView.getEmail());

        ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + "/login", new HttpEntity<>(view, Server.createHeadersJson()), LoginView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
