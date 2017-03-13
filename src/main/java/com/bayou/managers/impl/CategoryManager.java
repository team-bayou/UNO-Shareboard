package com.bayou.managers.impl;

import com.bayou.converters.CategoryConverter;
import com.bayou.domains.Category;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.CategoryResourceAccessor;
import com.bayou.views.CategoryView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * File: AdvertisementManager
 * Package: com.bayou.managers.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Service
public class CategoryManager implements IManager<CategoryView> {
    @Autowired
    CategoryResourceAccessor ras = new CategoryResourceAccessor();

    @Autowired
    CategoryConverter converter = new CategoryConverter();

    public CategoryView get(Long id) throws NotFoundException {
        CategoryView categoryView;
        Category category = ras.find(id);

        if (category == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            categoryView = converter.convertToView(category);
        }

        return categoryView;
    }

    @Override
    public List<CategoryView> getAll() throws NotFoundException {
        List<CategoryView> views = new ArrayList<>();

        for (Category c : ras.findAll())
            views.add(converter.convertToView(c));

        return views;
    }

    @Override
    public Long add(CategoryView view) {
        Long id = -1L;
        try {
            id = ras.add(converter.convertToDomain(view));
        } catch (DataIntegrityViolationException e) {
            System.err.println("Advertisement: " + view.getTitle() + "already exist");
        }

        return id;
    }

    @Override
    public Long update(CategoryView view) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            ras.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The category with ID:" + id + " does not exist in the database ");
        }
    }
}
