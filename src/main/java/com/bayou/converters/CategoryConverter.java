package com.bayou.converters;

import com.bayou.domains.Category;
import com.bayou.views.CategoryView;
import org.springframework.stereotype.Component;

/**
 * File: CategoryConverter
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Component("CategoryConverter")
public class CategoryConverter {
    public CategoryView convertToView(Category domain) {
        CategoryView view = new CategoryView();
        view.setId(domain.getId());
        view.setTitle(domain.getTitle());
        view.setColor(domain.getColor());
        view.setDescription(domain.getDescription());
        view.setParentCategoryId(domain.getParentCategoryId());

        return view;
    }

    public Category convertToDomain(CategoryView view) {
        Category domain = new Category();
        domain.setId(view.getId());
        domain.setTitle(view.getTitle());
        domain.setColor(view.getColor());
        domain.setDescription(view.getDescription());
        domain.setParentCategoryId(view.getParentCategoryId());

        return domain;
    }

    public Category updateConversion(Category updatedCategoryState, Category oldCategoryState) {

        if(updatedCategoryState.getTitle() == null ) { updatedCategoryState.setTitle(oldCategoryState.getTitle()); }
        if(updatedCategoryState.getColor() == null ) { updatedCategoryState.setColor(oldCategoryState.getColor()); }
        if(updatedCategoryState.getDescription() == null) { updatedCategoryState.setDescription(oldCategoryState.getDescription());}
        if(updatedCategoryState.getParentCategoryId() == null) { updatedCategoryState.setParentCategoryId(oldCategoryState.getParentCategoryId());}

        return updatedCategoryState;
    }
}

