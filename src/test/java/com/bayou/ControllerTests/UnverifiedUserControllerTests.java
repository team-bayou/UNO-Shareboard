package com.bayou.ControllerTests;

import com.bayou.utils.Mocks;
import com.bayou.utils.Server;
import com.bayou.views.impl.UnverifiedUserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rachelguillory on 2/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UnverifiedUserControllerTests {
    private static final String RESOURCE_URL = "/unverified_users";

    private TestRestTemplate rest = new TestRestTemplate();

    @Test
    public void testGetUnverifiedUserById() {
        // Create unverified user view and add unverified user to db.
        UnverifiedUserView view = Mocks.createUnverifiedUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);
        view.setId(entity.getBody());

        // Get unverified user by id.
        ResponseEntity<UnverifiedUserView> responseEntity = rest.getForEntity(
                Server.url() + RESOURCE_URL + "/" + view.getId(), UnverifiedUserView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);

        rest.delete(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete", String.class);
    }

    @Test
    public void testGetUnverifiedUserByEmail() {
        // Create unverified user view and add unverified user to db.
        UnverifiedUserView view = Mocks.createUnverifiedUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);
        view.setId(entity.getBody());

        // Get unverified user by email.
        ResponseEntity<UnverifiedUserView> responseEntity = rest.getForEntity(
                Server.url() + RESOURCE_URL + "/email/" + view.getEmail(), UnverifiedUserView.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);

        rest.delete(Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete", String.class);
    }

    @Test
    public void testAddUnverifiedUser() {
        // Create unverified user view and add unverified user to db.
        UnverifiedUserView view = Mocks.createUnverifiedUserView();
        ResponseEntity<Long> responseEntity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    public void testDeleteUnverifiedUser() {
        // Create unverified user view and add user to db.
        UnverifiedUserView view = Mocks.createUnverifiedUserView();
        ResponseEntity<Long> entity = rest.postForEntity(
                Server.url() + RESOURCE_URL + "/add", new HttpEntity<>(view, Server.createHeadersJson()), Long.class);
        view.setId(entity.getBody());

        // Delete unverified user by id.
        ResponseEntity responseEntity = rest.exchange(
                Server.url() + RESOURCE_URL + "/" + view.getId() + "/delete",
                HttpMethod.DELETE, new HttpEntity<>(view, Server.createHeadersJson()), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
