package com.bayou.managers;

import com.bayou.managers.impl.CategoryManager;
import com.bayou.utils.ViewMocks;
import com.bayou.views.CategoryView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.NotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * File: CategoryManagerTests
 * Package: com.bayou.managers
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CategoryManagerTests {
    @Autowired
    private CategoryManager manager;

    private CategoryView view;

    @Before
    public void setup() {
        // Create category view and add category to db.
        view = ViewMocks.createCategory();
        Long id = manager.add(view);
        view.setId(id);
    }

    @After
    public void cleanup() {
        // Delete test data.
        manager.delete(view.getId());
    }

    @Test
    public void testGetCategories() {
        List<CategoryView> views = null;

        // Get list of categories.
        try {
            views = manager.getAll();
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetCategoryById() {
        CategoryView view = null;

        // Get category by id.
        try {
            view = manager.get(this.view.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testAddCategory() {
        // Create category view and add category to db.
        CategoryView view = ViewMocks.createCategory();
        Long id = manager.add(view);
        view.setId(id);

        assertTrue(id != null && id > 0);

        // Delete test data.
        manager.delete(view.getId());
    }

    @Test
    public void testUpdateCategory() throws URISyntaxException {
        // Update some information of category and save it to db.
        view.setTitle(view.getTitle() + " updated");
        Long id = manager.update(view);

        assertEquals(view.getId(), id);
    }

    @Test
    public void testDeleteCategory() {
        // Create category view and add category to db.
        CategoryView view = ViewMocks.createCategory();
        Long id = manager.add(view);
        view.setId(id);

        // Delete category by id.
        manager.delete(view.getId());

        try {
            manager.get(view.getId());
            fail();
        } catch (NotFoundException e) {
            // Test passed.
        }
    }
}
