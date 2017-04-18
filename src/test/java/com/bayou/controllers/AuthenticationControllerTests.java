package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.LoginView;
import com.bayou.views.UnverifiedUserView;
import com.bayou.views.UserView;
import com.bayou.views.VerifyUserView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * File: AuthenticationControllerTests
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 4/1/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthenticationControllerTests {
    private static final String RESOURCE_URL = "/auth";
    private static final String USER_URL = "/users";
    private static final String UNVERIFIED_USER_URL = "/unverified_users";

    @Autowired
    private TestRestTemplate rest;
    private HttpHeaders headers = Server.createHeadersAuthJson();

    private UserView userView;

    @Before
    public void setup() {
        // Create user view and add user to db.
        userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USER_URL + "/add",
                new HttpEntity<>(userView, headers), Long.class);
        userView.setId(entity.getBody());
    }

    @After
    public void cleanup() {
        // Delete test data.
        rest.exchange(
                Server.url() + USER_URL + "/" + userView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
    }

    @Test
    public void testLoginByAccountName() {
        VerifyUserView view = Mocks.createVerifyUserViewForLoginByAccountName(userView);

        // Login user by account name.
        ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/login",
                new HttpEntity<>(view, headers), LoginView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testLoginByEmail() {
        VerifyUserView view = Mocks.createVerifyUserViewForLoginByEmail(userView);

        // Login user by email.
        ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/login",
                new HttpEntity<>(view, headers), LoginView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testVerify() {
        // Create unverified user view and add unverified user to db.
        UnverifiedUserView unverifiedUserView = Mocks.createUnverifiedUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + UNVERIFIED_USER_URL + "/add",
                new HttpEntity<>(unverifiedUserView, headers), Long.class);
        unverifiedUserView.setId(entity.getBody());

        // We need to fetch the unverified user from the db because some attributes (e.g. verification code) which are
        // required for the verify user are missing in the current unverified user object.
        ResponseEntity<UnverifiedUserView> unverifiedUserEntity = rest.exchange(
                Server.url() + UNVERIFIED_USER_URL + "/" + unverifiedUserView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), UnverifiedUserView.class);

        VerifyUserView view = Mocks.createVerifyUserViewForVerification(unverifiedUserEntity.getBody());

        // Verify user.
        ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/verify",
                new HttpEntity<>(view, headers), LoginView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);

        // Delete test data.
        rest.exchange(
                Server.url() + UNVERIFIED_USER_URL + "/" + unverifiedUserView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
    }

    @Test
    public void testForgotPassword() {
        VerifyUserView view = Mocks.createVerifyUserViewForForgotPassword(userView);

        // Forget user's password and send verification code to user's email.
        ResponseEntity responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/forgotPass",
                new HttpEntity<>(view, headers), Object.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testResetPassword() {
        VerifyUserView view = Mocks.createVerifyUserViewForForgotPassword(userView);

        // Hit 'Forgot password' endpoint first. Otherwise, the verification code of the user is not be set.
        ResponseEntity responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/forgotPass",
                new HttpEntity<>(view, headers), Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Get user by id.
        // We need to fetch the user from the db because some attributes (e.g. verification code) which are
        // required for the verify user are missing in the current user object.
        ResponseEntity<UserView> responseUserEntity = rest.exchange(
                Server.url() + USER_URL + "/" + userView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), UserView.class);

        view = Mocks.createVerifyUserViewForResetPassword(responseUserEntity.getBody());

        // Reset user's password and verification code.
        responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/resetPass",
                new HttpEntity<>(view, headers),
                Object.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
