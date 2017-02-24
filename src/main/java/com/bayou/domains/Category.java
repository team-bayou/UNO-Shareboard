package com.bayou.domains;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

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

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
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
        return parentCategoryId != null ? parentCategoryId.equals(category.parentCategoryId) : category.parentCategoryId == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentCategoryId != null ? parentCategoryId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                "} " + super.toString();
    }
}
