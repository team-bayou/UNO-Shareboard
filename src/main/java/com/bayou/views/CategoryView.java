package com.bayou.views;

/**
 * File: CategoryView
 * Package: com.bayou.views
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public class CategoryView extends BaseEntityView {
    private String title;
    private String color;
    private String description;
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
        if (!super.equals(o)) return false;

        CategoryView that = (CategoryView) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return parentCategoryId != null ? parentCategoryId.equals(that.parentCategoryId) : that.parentCategoryId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentCategoryId != null ? parentCategoryId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CategoryView{" +
                "title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", parentCategory=" + parentCategoryId +
                "} " + super.toString();
    }
}
