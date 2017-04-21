package com.bayou.managers;

import com.bayou.exceptions.ValidationException;
import com.bayou.managers.impl.UserManager;
import com.bayou.utils.ViewMocks;
import com.bayou.views.UserView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.NotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * File: UserManagerTests
 * Package: com.bayou.managers
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserManagerTests {
    @Autowired
    private UserManager manager;

    private UserView view;

    @Before
    public void setup() throws ValidationException {
        // Create user view and add user to db.
        view = ViewMocks.createUser();
        Long id = manager.add(view);
        view.setId(id);
    }

    @After
    public void cleanup() {
        // Delete test data.
        manager.delete(view.getId());
    }

    @Test
    public void testGetUsers() {
        List<UserView> views = null;

        // Get list of users.
        try {
            views = manager.getAll();
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetUserById() {
        UserView view = null;

        // Get user by id.
        try {
            view = manager.get(this.view.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testGetUserByAccountName() {
        UserView view = null;

        // Get user by account name.
        try {
            view = manager.getByAccountName(this.view.getAccountName());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testGetUserByEmail() {
        UserView view = null;

        // Get user by email.
        try {
            view = manager.getByEmail(this.view.getEmail());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testAddUser() throws ValidationException {
        // Create user view and add user to db.
        UserView view = ViewMocks.createUser();
        Long id = manager.add(view);
        view.setId(id);

        assertTrue(id != null && id > 0);

        // Delete test data.
        manager.delete(view.getId());
    }

    @Test
    public void testUpdateUser() throws ValidationException {
        // Update some information of user and save it to db.
        view.setFirstName(view.getFirstName() + " updated");
        Long id = manager.update(view);

        assertEquals(view.getId(), id);
    }

    @Test
    public void testDeleteUser() throws ValidationException {
        // Create user view and add user to db.
        UserView view = ViewMocks.createUser();
        Long id = manager.add(view);
        view.setId(id);

        // Delete user by id.
        manager.delete(view.getId());

        try {
            manager.get(view.getId());
            fail();
        } catch (NotFoundException e) {
            // Test passed.
        }
    }
}
