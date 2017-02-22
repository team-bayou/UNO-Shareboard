package com.bayou.domains;


import com.bayou.views.impl.CategoryView;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Advertisement> advertisements = new HashSet<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (title != null ? !title.equals(category.title) : category.title != null) return false;
        if (color != null ? !color.equals(category.color) : category.color != null) return false;
        if (description != null ? !description.equals(category.description) : category.description != null)
            return false;
        return parentCategory != null ? parentCategory.equals(category.parentCategory) : category.parentCategory == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
        return result;
    }
}
