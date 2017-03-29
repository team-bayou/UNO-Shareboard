package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.UserView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * File: UserControllerTests
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/21/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTests {
    private static final String RESOURCE_URL = "/users";

    @Autowired
    private TestRestTemplate rest;
    private HttpHeaders headers = Server.createHeadersAuthJson();
    private UserView view;

    @Before
    public void setup() {
        // Create category view and add category to db.
        view = Mocks.createUserView();

        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, headers), Long.class);
        view.setId(entity.getBody());
    }

    @After
    public void cleanup() {
        // Delete test data.
        rest.exchange(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, headers), String.class);
    }

    @Test
    public void testGetUsers() {
        // Get list of users.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetUserById() {
        // Get user by id.
        ResponseEntity<UserView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), UserView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetUserByAccountName() {
        // Get user by account name.
        ResponseEntity<UserView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/accountName/" + view.getAccountName(),
                HttpMethod.GET, new HttpEntity<>(headers), UserView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetUserByEmail() {
        // Get user by email.
        ResponseEntity<UserView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/email/" + view.getEmail(),
                HttpMethod.GET, new HttpEntity<>(headers), UserView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testAddUser() {
        // Create user view and add user to db.
        UserView view = Mocks.createUserView();
        ResponseEntity<Long> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, headers), Long.class);
        view.setId(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);

        // Delete test data.
        rest.exchange(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, headers), String.class);
    }

    @Test
    public void testUpdateUser() {
        // Update some information of user and save it to db.
        view.setFirstName(view.getFirstName() + " updated");
        ResponseEntity<Long> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/update",
                HttpMethod.PUT, new HttpEntity<>(view, headers), Long.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(), view.getId());
    }

    @Test
    public void testDeleteUser() {
        // Create user view and add user to db.
        UserView view = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(view, headers), Long.class);
        view.setId(entity.getBody());

        // Delete user by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, headers), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
