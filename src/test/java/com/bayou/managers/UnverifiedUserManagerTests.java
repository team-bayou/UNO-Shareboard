package com.bayou.managers;

import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.utils.ViewMocks;
import com.bayou.views.UnverifiedUserView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.NotFoundException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * File: UnverifiedUserManagerTests
 * Package: com.bayou.managers
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UnverifiedUserManagerTests {
    @Autowired
    private UnverifiedUserManager manager;

    private UnverifiedUserView view;

    @Before
    public void setup() {
        // Create unverified user view and add unverified user to db.
        view = ViewMocks.createUnverifiedUser();
        Long id = manager.add(view);
        view.setId(id);
    }

    @After
    public void cleanup() {
        // Delete test data.
        manager.delete(view.getId());
    }

    @Test
    public void testGetUnverifiedUserById() {
        UnverifiedUserView view = null;

        // Get unverified user by id.
        try {
            view = manager.get(this.view.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testGetUnverifiedUserByEmail() {
        UnverifiedUserView view = null;

        // Get unverified user by email.
        try {
            view = manager.getByEmail(this.view.getEmail());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testAddUnverifiedUser() {
        // Create unverified user view and add unverified user to db.
        UnverifiedUserView view = ViewMocks.createUnverifiedUser();
        Long id = manager.add(view);
        view.setId(id);

        assertTrue(id != null && id > 0);

        // Delete test data.
        manager.delete(view.getId());
    }

    @Test
    public void testDeleteUnverifiedUser() {
        // Create unverified user view and add unverified user to db.
        UnverifiedUserView view = ViewMocks.createUnverifiedUser();
        Long id = manager.add(view);
        view.setId(id);

        // Delete unverified user by id.
        manager.delete(view.getId());

        try {
            manager.get(view.getId());
            fail();
        } catch (NotFoundException e) {
            // Test passed.
        }
    }
}
