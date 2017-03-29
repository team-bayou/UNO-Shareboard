package com.bayou.controllers;

import com.bayou.domains.Category;
import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.CategoryView;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * File: CategoryControllerTests
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/21/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CategoryControllerTests {
    private static final String RESOURCE_URL = "/categories";

    @Autowired
    private TestRestTemplate rest;
    private HttpHeaders headers = Server.createHeadersAuthJson();

    private CategoryView view;

    @Before
    public void setup() {
        // Create category view and add category to db.
        view = Mocks.createCategoryView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(view, headers), Long.class);
        view.setId(entity.getBody());
    }

    @After
    public void cleanup() {
        // Delete test data.
        rest.exchange(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, headers), String.class);
    }

    @Test
    public void testGetCategories() {
        // Get list of categories.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetCategoryById() {
        // Get category by id.
        ResponseEntity<CategoryView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), CategoryView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testAddCategory() {
        // Create category view and add category to db.
        CategoryView view = Mocks.createCategoryView();
        ResponseEntity<Long> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(view, headers), Long.class);
        view.setId(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);

        rest.exchange(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, headers), String.class);
    }

    //TODO:needs work
    @Ignore
    @Test
    public void testUpdateCategory() throws URISyntaxException {
        // Create category view and add category to db.
        CategoryView view = Mocks.createCategoryView();
        ResponseEntity<Long> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);
        view.setId(responseEntity.getBody());

        view.setDescription("Description updated");

        URI uri = new URI(Server.url() + RESOURCE_URL + "/update");

        HttpEntity entity =  new HttpEntity<> (view, Server.createHeadersJson());

        ResponseEntity<CategoryView> updatedResponseEntity =  rest.exchange(uri , HttpMethod.PUT, entity, CategoryView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(updatedResponseEntity.getBody().getDescription().equals("Description update"));

        // Delete test data.
        rest.delete(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete", String.class);
    }

    @Test
    public void testDeleteCategory() {
        // Create category view and add category to db.
        CategoryView view = Mocks.createCategoryView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(view, headers), Long.class);
        view.setId(entity.getBody());

        // Delete category by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, headers), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
