package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.AdvertisementView;
import com.bayou.views.CategoryView;
import com.bayou.views.UserView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private TestRestTemplate rest;

    @MockBean
    private UserView userView;
    @MockBean
    private CategoryView categoryView;
    @MockBean
    private AdvertisementView advertisementView;

    @Before
    public void prepare() {
        // Create user view and add user to db.
        userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add", new HttpEntity<>(userView, Server.createHeadersJson()), Long.class);
        userView.setId(entity.getBody());

        // Create category view and add category to db.
        categoryView = Mocks.createCategoryView();
        entity = rest.postForEntity(
                Server.url() + CATEGORIES_URL + "/add", new HttpEntity<>(categoryView, Server.createHeadersJson()), Long.class);
        categoryView.setId(entity.getBody());

        // Create advertisement view and add advertisement to db.
        advertisementView = Mocks.createAdvertisementView();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());
        entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(advertisementView, Server.createHeadersJson()), Long.class);
        advertisementView.setId(entity.getBody());
    }

    @After
    public void cleanUp() {
        // Delete test data.
        rest.delete(Server.url() + RESOURCE_URL + "/" + advertisementView.getId() + "/delete", String.class);
        rest.delete(Server.url() + USERS_URL + "/" + userView.getId() + "/delete", String.class);
        rest.delete(Server.url() + CATEGORIES_URL + "/" + categoryView.getId() + "/delete", String.class);
    }

    @Test
    public void testGetAdvertisements() {
        // Get list of advertisements.
        ResponseEntity<List> responseEntity = rest.getForEntity(
                Server.url() + RESOURCE_URL, List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetAdvertisementById() {
        // Get advertisement by id.
        ResponseEntity<AdvertisementView> responseEntity = rest.getForEntity(
                Server.url() + RESOURCE_URL + "/" + advertisementView.getId(), AdvertisementView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testAddAdvertisement() {
        // Create advertisement view and add advertisement to db.
        AdvertisementView advertisementView = Mocks.createAdvertisementView();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());

        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(advertisementView, Server.createHeadersJson()), Long.class);
        advertisementView.setId(entity.getBody());

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue(entity.getBody() != null);

        // Delete test data.
        rest.delete(Server.url() + RESOURCE_URL + "/" + advertisementView.getId() + "/delete", String.class);
    }

    @Test
    public void testUpdateAdvertisement() {
        // TODO Implement
    }

    @Test
    public void testDeleteAdvertisement() {
        // Create advertisement view and add advertisement to db.
        AdvertisementView advertisementView = Mocks.createAdvertisementView();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());

        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(advertisementView, Server.createHeadersJson()), Long.class);
        advertisementView.setId(entity.getBody());

        // Delete advertisement by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + advertisementView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(advertisementView, Server.createHeadersJson()), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
