package com.bayou.managers.impl;

import com.bayou.converters.CategoryConverter;
import com.bayou.domains.Category;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.CategoryResourceAccessor;
import com.bayou.views.CategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
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
    private CategoryResourceAccessor categoryRas;

    @Autowired
    private CategoryConverter categoryConverter;

    public CategoryView get(Long id) throws NotFoundException {
        CategoryView categoryView;
        Category category = categoryRas.find(id);

        if (category == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            categoryView = categoryConverter.convertToView(category);
        }

        return categoryView;
    }

    @Override
    public List<CategoryView> getAll() throws NotFoundException {
        List<CategoryView> views = new ArrayList<>();

        for (Category c : categoryRas.findAll())
            views.add(categoryConverter.convertToView(c));

        return views;
    }

    @Override
    public Long add(CategoryView view) {
        Long id = -1L;
        try {
            id = categoryRas.add(categoryConverter.convertToDomain(view));
        } catch (DataIntegrityViolationException e) {
            System.err.println("Advertisement: " + view.getTitle() + "already exist");
        }

        return id;
    }

    @Override
    public Long update(CategoryView categoryView) {

        Category category = categoryConverter.convertToDomain(categoryView);    //converts the category view to the category domain Object

        if (categoryView.getId() == null) {  //triggers a no content if the id is null
            return -1L;
        }

        Category retrievedCategory = categoryRas.find(categoryView.getId());    //get the category we are updating

        if (retrievedCategory == null) {    //if the requested category doesn't exist
            throw new javax.ws.rs.NotFoundException();
        }

        category.setVersion(retrievedCategory.getVersion());   //gets the record's we are updating version number

        category = categoryConverter.updateConversion(category, retrievedCategory);

        return categoryRas.update(category);
    }

    @Override
    public void delete(Long id) {
        try {
            categoryRas.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The category with ID:" + id + " does not exist in the database ");
        }
    }
}
