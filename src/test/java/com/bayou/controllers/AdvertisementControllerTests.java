package com.bayou.controllers;

import com.bayou.utils.Server;
import com.bayou.utils.ViewMocks;
import com.bayou.views.AdvertisementView;
import com.bayou.views.CategoryView;
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
 * File: AdvertisementControllerTests
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/21/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AdvertisementControllerTests {
    private static final String USERS_URL = "/users";
    private static final String CATEGORIES_URL = "/categories";
    private static final String RESOURCE_URL = "/advertisements";
    private static final String PAGE_URL = "/page/1";

    @Autowired
    private TestRestTemplate rest;
    private HttpHeaders headers = Server.createHeadersAuthJson();

    private UserView userView;
    private CategoryView categoryView;
    private AdvertisementView advertisementView;

    @Before
    public void setup() {
        // Create user view and add user to db.
        userView = ViewMocks.createUser();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add", new HttpEntity<>(userView, headers), Long.class);
        userView.setId(entity.getBody());

        // Create category view and add category to db.
        categoryView = ViewMocks.createCategory();
        entity = rest.postForEntity(
                Server.url() + CATEGORIES_URL + "/add", new HttpEntity<>(categoryView, headers), Long.class);
        categoryView.setId(entity.getBody());

        // Create advertisement view and add advertisement to db.
        advertisementView = ViewMocks.createAdvertisement();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());
        entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(advertisementView, headers), Long.class);
        advertisementView.setId(entity.getBody());
    }

    @After
    public void cleanup() {
        // Delete test data.
        rest.exchange(Server.url() + RESOURCE_URL + "/" + advertisementView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        rest.exchange(Server.url() + USERS_URL + "/" + userView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        rest.exchange(Server.url() + CATEGORIES_URL + "/" + categoryView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
    }

    @Test
    public void testGetAdvertisements() {
        // Get list of advertisements.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetAdvertisementsByPage() {
        // Get list of advertisements by page number.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + PAGE_URL,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetAdvertisementById() {
        // Get advertisement by id.
        ResponseEntity<AdvertisementView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + advertisementView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), AdvertisementView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetUserAdvertisements() {
        // Get list of user's advertisements.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + USERS_URL + "/" + userView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetUserAdvertisementsByPage() {
        // Get list of user's advertisements by page number.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + USERS_URL + "/" + userView.getId() + PAGE_URL,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetCategoryAdvertisements() {
        // Get list of category's advertisements.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + CATEGORIES_URL + "/" + categoryView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetCategoryAdvertisementsByPage() {
        // Get list of category's advertisements by page number.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + CATEGORIES_URL + "/" + categoryView.getId() + PAGE_URL,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testAddAdvertisement() {
        // Create advertisement view and add advertisement to db.
        AdvertisementView advertisementView = ViewMocks.createAdvertisement();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());

        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(advertisementView, headers), Long.class);
        advertisementView.setId(entity.getBody());

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue(entity.getBody() != null);

        // Delete test data.
        rest.exchange(Server.url() + RESOURCE_URL + "/" + advertisementView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
    }

    @Test
    public void testUpdateAdvertisement() {
        // Update some information of advertisement and save it to db.
        advertisementView.setTitle(advertisementView.getTitle() + " updated");
        ResponseEntity<Long> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/update",
                HttpMethod.PUT, new HttpEntity<>(advertisementView, headers), Long.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(advertisementView.getId(), responseEntity.getBody());
    }

    @Test
    public void testDeleteAdvertisement() {
        // Create advertisement view and add advertisement to db.
        AdvertisementView advertisementView = ViewMocks.createAdvertisement();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());

        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(advertisementView, headers), Long.class);
        advertisementView.setId(entity.getBody());

        // Delete advertisement by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + advertisementView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
