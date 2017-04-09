package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.LoginView;
import com.bayou.views.UnverifiedUserView;
import com.bayou.views.UserView;
import com.bayou.views.VerifyUserView;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    private UnverifiedUserView unverifiedUserView;

    @Before
    public void setup() {
        // Create user view and add user to db.
        userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USER_URL + "/add",
                new HttpEntity<>(userView, headers), Long.class);
        userView.setId(entity.getBody());

        // Create unverified user view and add unverified user to db.
        unverifiedUserView = Mocks.createUnverifiedUserView();
        entity = rest.postForEntity(
                Server.url() + UNVERIFIED_USER_URL + "/add",
                new HttpEntity<>(unverifiedUserView, headers), Long.class);
        unverifiedUserView.setId(entity.getBody());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @After
    public void cleanup() {
        // Delete test data.
        rest.exchange(Server.url() + USER_URL + "/" + userView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        rest.exchange(Server.url() + UNVERIFIED_USER_URL + "/" + unverifiedUserView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
    }

    @Test
    public void testLoginByAccountName() {
        VerifyUserView view = new VerifyUserView();
        view.setAccountName(userView.getAccountName());
        view.setEnteredPasswordHash(userView.getPasswordHash());

        // Login user by account name.
        ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/login",
                new HttpEntity<>(view, headers), LoginView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testLoginByEmail() {
        VerifyUserView view = Mocks.createVerifyUserView(userView);

        // Login user by email.
        ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/login",
                new HttpEntity<>(view, headers), LoginView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testVerify() {
        VerifyUserView view = Mocks.createVerifyUserView(unverifiedUserView);
        UnverifiedUserView unvView;

        ResponseEntity<UnverifiedUserView> getCodeEntity = rest.exchange(
                Server.url() + UNVERIFIED_USER_URL + "/" + unverifiedUserView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), UnverifiedUserView.class);

        unvView = getCodeEntity.getBody();
        assertEquals(HttpStatus.OK, getCodeEntity.getStatusCode());
        assertTrue(getCodeEntity.getBody() != null);

        view.setId(unvView.getId());
        view.setEnteredVerificationCode(unvView.getVerificationCode());

        // Verify user.
        ResponseEntity<LoginView> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/verify",
                new HttpEntity<>(view, headers), LoginView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    @Ignore
    public void testResetAndForgotPassword() {
        UserView resultView;
        VerifyUserView email = new VerifyUserView();
        email.setEmail(userView.getEmail());

        ResponseEntity<Integer> resetRespEntity = rest.postForEntity(Server.url()
            + RESOURCE_URL + "/forgotPass",  new HttpEntity<>(email, headers), Integer.class);
        assertEquals(HttpStatus.OK, resetRespEntity.getStatusCode());

        ResponseEntity<Integer> checkVerifNullEntity = rest.exchange(Server.url() + USER_URL +
                "/" + userView.getEmail() + "/codeCheck", HttpMethod.GET, new HttpEntity<>(headers),
                Integer.class);
        assertEquals(HttpStatus.OK, checkVerifNullEntity.getStatusCode());

        ResponseEntity<UserView> getUserEntity = rest.exchange(Server.url() + USER_URL +
                "/email/" + email.getEmail(), HttpMethod.GET, new HttpEntity<>(headers), UserView.class);
        assertEquals(HttpStatus.OK, getUserEntity.getStatusCode());
        resultView = getUserEntity.getBody();
        assertTrue(resultView.getVerificationCode() != null);

        email.setEnteredVerificationCode(resultView.getVerificationCode());
        email.setEnteredPasswordHash("abc123");
        email.setEnteredPasswordSalt("123abc");

        ResponseEntity<Integer> resetPassEntity = rest.postForEntity(Server.url() + RESOURCE_URL +
            "/resetPass", new HttpEntity<>(email, headers), Integer.class);
        assertEquals(HttpStatus.OK, resetPassEntity.getStatusCode());

        checkVerifNullEntity = rest.exchange(Server.url() + USER_URL +
                "/" + email.getEmail() + "/codeCheck", HttpMethod.GET, new HttpEntity<>(headers),
                Integer.class);
        assertEquals(HttpStatus.NO_CONTENT, checkVerifNullEntity.getStatusCode());

        getUserEntity = rest.exchange(Server.url() + USER_URL +
                "/" + userView.getId(), HttpMethod.GET, new HttpEntity<>(headers), UserView.class);
        assertEquals(HttpStatus.OK, getUserEntity.getStatusCode());
        resultView = getUserEntity.getBody();
        assertTrue(resultView.getVerificationCode() == null);
        assertEquals(email.getPasswordHash(), resultView.getPasswordHash());
        assertEquals(email.getPasswordSalt(), resultView.getPasswordSalt());
    }
}
