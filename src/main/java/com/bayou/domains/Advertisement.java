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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
        this.adType = adType;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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
}
