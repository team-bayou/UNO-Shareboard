package com.bayou.domains;


import com.bayou.views.impl.CategoryView;

import javax.persistence.*;
import java.util.Set;

/**
 * File: Category
 * Package: com.bayou.domains
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Entity(name = "Category")
@Table(name = "categories")
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class Category extends BaseEntity {
    @Column(name = "title", columnDefinition = "VARCHAR")
    private String title;

    @Column(name = "color", columnDefinition = "CHARACTER")
    private String color;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
    private Set<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Advertisement> advertisements;

    public Category() {
        super();
    }

    public Category(CategoryView view) {
        setId(view.getId());
        setTitle(view.getTitle());
        setColor(view.getColor());
        setDescription(view.getDescription());

        CategoryView parent = view.getParentCategory();
        // Be aware of recursion!!
        if (parent != null)
            setParentCategory(new Category(parent));
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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
