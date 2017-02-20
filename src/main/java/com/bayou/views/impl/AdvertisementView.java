package com.bayou.views.impl;

import com.bayou.types.UserType;
import com.bayou.views.IView;

import java.util.Date;

/**
 * File: AdvertisementView
 * Package: com.bayou.views.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public class AdvertisementView implements IView {
    private Long id;
    private String title;
    private String description;
    private Integer categoryId;
    private UserView owner;
    private Date timePublished;
    private Date expirationDate;
    private UserType adType;
    private Double price;
    private String tradeItem;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public UserView getOwner() {
        return owner;
    }

    public void setOwner(UserView owner) {
        this.owner = owner;
    }

    public Date getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(Date timePublished) {
        this.timePublished = timePublished;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public UserType getAdType() {
        return adType;
    }

    public void setAdType(UserType adType) {
        this.adType = adType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvertisementView that = (AdvertisementView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (timePublished != null ? !timePublished.equals(that.timePublished) : that.timePublished != null)
            return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        if (adType != that.adType) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return tradeItem != null ? tradeItem.equals(that.tradeItem) : that.tradeItem == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (timePublished != null ? timePublished.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (adType != null ? adType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (tradeItem != null ? tradeItem.hashCode() : 0);
        return result;
    }
}
