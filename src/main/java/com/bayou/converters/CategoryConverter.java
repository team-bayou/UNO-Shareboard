package com.bayou.converters;

import com.bayou.domains.Category;
import com.bayou.views.impl.CategoryView;
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

        Category parent = domain.getParentCategory();
        if (parent != null)
            view.setParentCategory(new CategoryView(parent));

        return view;
    }

    public Category convertToDomain(CategoryView view) {
        Category domain = new Category();
        domain.setId(view.getId());
        domain.setTitle(view.getTitle());
        domain.setColor(view.getColor());
        domain.setDescription(view.getDescription());

        CategoryView parent = view.getParentCategory();
        if (parent != null)
            domain.setParentCategory(new Category(parent));

        return domain;
    }
}
