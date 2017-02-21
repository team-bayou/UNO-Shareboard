package com.bayou.views.impl;

import com.bayou.domains.Category;

/**
 * File: CategoryView
 * Package: com.bayou.views.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public class CategoryView extends BaseEntityView {
    private String title;
    private String color;
    private String description;
    private CategoryView parentCategory;

    public CategoryView() {
        super();
    }

    public CategoryView(Category domain) {
        setId(domain.getId());
        setTitle(domain.getTitle());
        setColor(domain.getColor());
        setDescription(domain.getDescription());

        Category parent = domain.getParentCategory();
        // Be aware of recursion!!
        if (parent != null)
            setParentCategory(new CategoryView(parent));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryView getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryView parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CategoryView that = (CategoryView) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return parentCategory != null ? parentCategory.equals(that.parentCategory) : that.parentCategory == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
        return result;
    }
}
