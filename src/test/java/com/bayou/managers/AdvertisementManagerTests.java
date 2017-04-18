package com.bayou.managers;

import com.bayou.managers.impl.AdvertisementManager;
import com.bayou.managers.impl.CategoryManager;
import com.bayou.managers.impl.UserManager;
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
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.NotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * File: AdvertisementManagerTests
 * Package: com.bayou.managers
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AdvertisementManagerTests {
    private static final int PAGE_NUMBER = 1;

    @Autowired
    private UserManager userManager;
    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private AdvertisementManager advertisementManager;

    private UserView userView;
    private CategoryView categoryView;
    private AdvertisementView advertisementView;

    @Before
    public void setup() {
        // Create user view and add user to db.
        userView = ViewMocks.createUser();
        Long id = userManager.add(userView);
        userView.setId(id);

        // Create category view and add category to db.
        categoryView = ViewMocks.createCategory();
        id = categoryManager.add(categoryView);
        categoryView.setId(id);

        // Create advertisement view and add advertisement to db.
        advertisementView = ViewMocks.createAdvertisement();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());
        id = advertisementManager.add(advertisementView);
        advertisementView.setId(id);
    }

    @After
    public void cleanup() {
        // Delete test data.
        advertisementManager.delete(advertisementView.getId());
        userManager.delete(userView.getId());
        categoryManager.delete(categoryView.getId());
    }

    @Test
    public void testGetAdvertisements() {
        List<AdvertisementView> views = null;

        // Get list of advertisements.
        try {
            views = advertisementManager.getAll();
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetAdvertisementsByPage() {
        List<AdvertisementView> views = null;

        // Get list of advertisements.
        try {
            views = advertisementManager.getAll(PAGE_NUMBER);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetAdvertisementById() {
        AdvertisementView view = null;

        // Get advertisement by id.
        try {
            view = advertisementManager.get(advertisementView.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testGetUserAdvertisements() {
        List<AdvertisementView> views = null;

        // Get list of user's advertisements.
        try {
            views = advertisementManager.getAllByOwner(userView.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetUserAdvertisementsByPage() {
        List<AdvertisementView> views = null;

        // Get list of user's advertisements by page number.
        try {
            views = advertisementManager.getAllByOwner(userView.getId(), PAGE_NUMBER);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetCategoryAdvertisements() {
        List<AdvertisementView> views = null;

        // Get list of category's advertisements.
        try {
            views = advertisementManager.getAllByCategory(categoryView.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetCategoryAdvertisementsByPage() {
        List<AdvertisementView> views = null;

        // Get list of category's advertisements by page number.
        try {
            views = advertisementManager.getAllByCategory(categoryView.getId(), PAGE_NUMBER);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testAddAdvertisement() {
        // Create advertisement view and add advertisement to db.
        AdvertisementView advertisementView = ViewMocks.createAdvertisement();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());
        Long id = advertisementManager.add(advertisementView);
        advertisementView.setId(id);

        assertTrue(id != null && id > 0);

        // Delete test data.
        advertisementManager.delete(advertisementView.getId());
    }

    @Test
    public void testUpdateAdvertisement() {
        // Update some information of advertisement and save it to db.
        advertisementView.setTitle(advertisementView.getTitle() + " updated");
        Long id = advertisementManager.update(advertisementView);

        assertEquals(advertisementView.getId(), id);
    }

    @Test
    public void testDeleteAdvertisement() {
        // Create advertisement view and add advertisement to db.
        AdvertisementView advertisementView = ViewMocks.createAdvertisement();
        advertisementView.setOwnerId(userView.getId());
        advertisementView.setCategoryId(categoryView.getId());
        Long id = advertisementManager.add(advertisementView);
        advertisementView.setId(id);

        assertTrue(id != null && id > 0);

        // Delete advertisement by id.
        advertisementManager.delete(advertisementView.getId());

        try {
            advertisementManager.get(advertisementView.getId());
            fail();
        } catch (NotFoundException e) {
            // Test passed.
        }
    }
}
