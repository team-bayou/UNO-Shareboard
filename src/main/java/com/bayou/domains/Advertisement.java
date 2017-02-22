package com.bayou.domains;


import com.bayou.types.AdType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * File: Advertisement
 * Package: com.bayou.domains
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Entity(name = "Advertisement")
@Table(name = "ads")
@AttributeOverride(name = "id", column = @Column(name = "ad_id"))
public class Advertisement extends BaseEntity {
    @Column(name = "title", columnDefinition = "VARCHAR")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "owner", nullable = false)
    private Long owner;

    @Column(name = "time_published", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePublished;

    @Column(name = "expiration_date", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    @Column(name = "ad_type")
    @Type(type = "com.bayou.types.PGEnumUserType", parameters = {@org.hibernate.annotations.Parameter(name = "enumClassName", value = "com.bayou.types.AdType")})
    private AdType adType;

    @Column(name = "price", columnDefinition = "MONEY")
    private Double price;

    @Column(name = "trade_item", columnDefinition = "VARCHAR")
    private String tradeItem;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
        this.adType = adType;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
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

        Advertisement that = (Advertisement) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
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
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (timePublished != null ? timePublished.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (adType != null ? adType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (tradeItem != null ? tradeItem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + categoryId +
                ", owner=" + owner +
                ", timePublished=" + timePublished +
                ", expirationDate=" + expirationDate +
                ", adType=" + adType +
                ", price=" + price +
                ", tradeItem='" + tradeItem + '\'' +
                "} " + super.toString();
    }
}
