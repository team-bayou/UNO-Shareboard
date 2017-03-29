package com.bayou.controllers;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.ReviewView;
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
 * File: ReviewControllerTests
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReviewControllerTests {
    private static final String USERS_URL = "/users";
    private static final String RESOURCE_URL = "/reviews";

    @Autowired
    private TestRestTemplate rest;
    private HttpHeaders headers = Server.createHeadersAuthJson();

    private UserView reviewerView;
    private UserView revieweeView;
    private ReviewView reviewView;

    @Before
    public void setup() {
        // Create user view and add user to db.
        reviewerView = Mocks.createUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add",
                new HttpEntity<>(reviewerView, headers), Long.class);
        reviewerView.setId(entity.getBody());

        // Create user view and add user to db.
        revieweeView = Mocks.createUserView();
        entity = rest.postForEntity(
                Server.url() + USERS_URL + "/add",
                new HttpEntity<>(revieweeView, headers), Long.class);
        revieweeView.setId(entity.getBody());

        // Create review view and add review to db.
        reviewView = Mocks.createReviewView();
        reviewView.setReviewerId(reviewerView.getId());
        reviewView.setRevieweeId(revieweeView.getId());
        entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(reviewView, headers), Long.class);
        reviewView.setId(entity.getBody());
    }

    @After
    public void cleanup() {
        // Delete test data.
        rest.exchange(Server.url() + RESOURCE_URL + "/" + reviewView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(reviewView, headers), String.class);
        rest.exchange(Server.url() + USERS_URL + "/" + reviewerView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(reviewerView, headers), String.class);
        rest.exchange(Server.url() + USERS_URL + "/" + revieweeView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(revieweeView, headers), String.class);
    }

    @Test
    public void testGetReviews() {
        // Get list of reviews.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetReviewById() {
        // Get review by id.
        ResponseEntity<ReviewView> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + reviewView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), ReviewView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetReviewsByReviewer() {
        // Get review by id.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/reviewer/" + reviewerView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testGetReviewsByReviewee() {
        // Get review by id.
        ResponseEntity<List> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/reviewee/" + revieweeView.getId(),
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testAddReview() {
        // Create review view and add review to db.
        ReviewView reviewView = Mocks.createReviewView();
        reviewView.setReviewerId(reviewerView.getId());
        reviewView.setRevieweeId(revieweeView.getId());

        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(reviewView, headers), Long.class);
        reviewView.setId(entity.getBody());

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue(entity.getBody() != null);

        // Delete test data.
        rest.exchange(Server.url() + RESOURCE_URL + "/" + reviewView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(reviewView, headers), String.class);
    }

    @Test
    public void testUpdateReview() {
        // Update some information of user and save it to db.
        reviewView.setComments(reviewView.getComments() + " updated");
        ResponseEntity<Long> responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/update",
                HttpMethod.PUT, new HttpEntity<>(reviewView, headers), Long.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(), reviewView.getId());
    }

    @Test
    public void testDeleteReview() {
        // Create review view and add review to db.
        ReviewView reviewView = Mocks.createReviewView();
        reviewView.setReviewerId(reviewerView.getId());
        reviewView.setRevieweeId(revieweeView.getId());

        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add",
                new HttpEntity<>(reviewView, headers), Long.class);
        reviewView.setId(entity.getBody());

        // Delete review by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + reviewView.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(reviewView, headers), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
