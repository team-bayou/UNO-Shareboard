package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.impl.AdvertisementView;
import com.bayou.views.impl.CategoryView;
import com.bayou.views.impl.UserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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

    private TestRestTemplate rest = new TestRestTemplate();

    @Test
    public void testGetAdvertisements() {
        // Create user view and add user to db.
        UserView userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add", new HttpEntity<>(userView, Server.createHeadersJson()), Long.class);
        userView.setId(entity.getBody());

        // Create category view and add category to db.
        CategoryView categoryView = Mocks.createCategoryView();
        entity = rest.postForEntity(
                Server.url() + CATEGORIES_URL + "/add", new HttpEntity<>(categoryView, Server.createHeadersJson()), Long.class);
        categoryView.setId(entity.getBody());

        // Create advertisement view and add advertisement to db.
        AdvertisementView view = Mocks.createAdvertisementView();
        view.setOwnerId(userView.getId());
        view.setCategoryId(categoryView.getId());
        entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);
        view.setId(entity.getBody());

        // Get list of categories.
        ResponseEntity<List> responseEntity = rest.getForEntity(
                Server.url() + RESOURCE_URL, List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);

        rest.delete(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete", String.class);
    }

    @Test
    public void testGetAdvertisementById() {
        // Create user view and add user to db.
        UserView userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add", new HttpEntity<>(userView, Server.createHeadersJson()), Long.class);
        userView.setId(entity.getBody());

        // Create category view and add category to db.
        CategoryView categoryView = Mocks.createCategoryView();
        entity = rest.postForEntity(
                Server.url() + CATEGORIES_URL + "/add", new HttpEntity<>(categoryView, Server.createHeadersJson()), Long.class);
        categoryView.setId(entity.getBody());

        // Create advertisement view and add advertisement to db.
        AdvertisementView view = Mocks.createAdvertisementView();
        view.setOwnerId(userView.getId());
        view.setCategoryId(categoryView.getId());

        entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);
        view.setId(entity.getBody());

        // Get advertisement by id.
        ResponseEntity<AdvertisementView> responseEntity = rest.getForEntity(
                Server.url() + RESOURCE_URL + "/" + view.getId(), AdvertisementView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);

        rest.delete(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete", String.class);
    }

    @Test
    public void testAddAdvertisement() {
        // Create user view and add user to db.
        UserView userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add", new HttpEntity<>(userView, Server.createHeadersJson()), Long.class);
        userView.setId(entity.getBody());

        // Create category view and add category to db.
        CategoryView categoryView = Mocks.createCategoryView();
        entity = rest.postForEntity(
                Server.url() + CATEGORIES_URL + "/add", new HttpEntity<>(categoryView, Server.createHeadersJson()), Long.class);
        categoryView.setId(entity.getBody());

        // Create advertisement view and add advertisement to db.
        AdvertisementView view = Mocks.createAdvertisementView();
        view.setOwnerId(userView.getId());
        view.setCategoryId(categoryView.getId());
        entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue(entity.getBody() != null);
    }

    @Test
    public void testUpdateAdvertisement() {
        // TODO Implement
    }

    @Test
    public void testDeleteAdvertisement() {
        // Create user view and add user to db.
        UserView userView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add", new HttpEntity<>(userView, Server.createHeadersJson()), Long.class);
        userView.setId(entity.getBody());

        // Create category view and add category to db.
        CategoryView categoryView = Mocks.createCategoryView();
        entity = rest.postForEntity(
                Server.url() + CATEGORIES_URL + "/add", new HttpEntity<>(categoryView, Server.createHeadersJson()), Long.class);
        categoryView.setId(entity.getBody());

        // Create advertisement view and add advertisement to db.
        AdvertisementView view = Mocks.createAdvertisementView();
        view.setOwnerId(userView.getId());
        view.setCategoryId(categoryView.getId());
        entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);
        view.setId(entity.getBody());

        // Delete advertisement by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, Server.createHeadersJson()), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
