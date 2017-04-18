package com.bayou.converters;

import com.bayou.domains.Category;
import com.bayou.utils.DomainMocks;
import com.bayou.utils.ViewMocks;
import com.bayou.views.CategoryView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * File: CategoryConverterTests
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CategoryConverterTests {
    @Autowired
    CategoryConverter converter;

    @Test
    public void testConvertToView() {
        Category domain = DomainMocks.createCategory();

        CategoryView view = converter.convertToView(domain);

        assertEquals(domain.getId(), view.getId());
        assertEquals(domain.getTitle(), view.getTitle());
        assertEquals(domain.getColor(), view.getColor());
        assertEquals(domain.getDescription(), view.getDescription());
        assertEquals(domain.getParentCategoryId(), view.getParentCategoryId());
    }

    @Test
    public void testConvertToDomain() {
        CategoryView view = ViewMocks.createCategory();

        Category domain = converter.convertToDomain(view);

        assertEquals(view.getId(), domain.getId());
        assertEquals(view.getTitle(), domain.getTitle());
        assertEquals(view.getColor(), domain.getColor());
        assertEquals(view.getDescription(), domain.getDescription());
        assertEquals(view.getParentCategoryId(), domain.getParentCategoryId());
    }
}
