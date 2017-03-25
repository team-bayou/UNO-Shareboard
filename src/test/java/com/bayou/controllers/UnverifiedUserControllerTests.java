package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.UnverifiedUserView;
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
 * File: UnverifiedUserControllerTests
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/21/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UnverifiedUserControllerTests {
    private static final String RESOURCE_URL = "/unverified_users";

    @Autowired
    private TestRestTemplate rest;
    private HttpHeaders headers = Server.createHeadersAuthJson();

    private UnverifiedUserView view;

    @Before
    public void setup() {
        // Create category view and add category to db.
        view = Mocks.createUnverifiedUserView();
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
    public void testGetUnverifiedUserById() {
        // Get unverified user by id.
        ResponseEntity<UnverifiedUserView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), UnverifiedUserView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetUnverifiedUserByEmail() {
        // Get unverified user by email.
        ResponseEntity<UnverifiedUserView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/email/" + view.getEmail(),
                HttpMethod.GET, new HttpEntity<>(headers), UnverifiedUserView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testAddUnverifiedUser() {
        // Create unverified user view and add unverified user to db.
        UnverifiedUserView view = Mocks.createUnverifiedUserView();
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
    public void testDeleteUnverifiedUser() {
        // Create unverified user view and add user to db.
        UnverifiedUserView view = Mocks.createUnverifiedUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, headers), Long.class);
        view.setId(entity.getBody());

        // Delete unverified user by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, headers), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
