package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.LoginView;
import com.bayou.views.UserView;
import javassist.NotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * File: LoginControllerTests
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/21/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginControllerTests {
    @Autowired
    private TestRestTemplate rest;

    @Test
    @Ignore
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
