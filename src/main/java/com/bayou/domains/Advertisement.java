package com.bayou.domains;


import com.bayou.types.UserType;
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
public class Advertisement {
    @Id
    @Column(name = "ad_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "category_id", columnDefinition = "INTEGER")
    private Integer categoryId;

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
    @Type(type = "com.bayou.types.PGEnumAdType", parameters = {@org.hibernate.annotations.Parameter(name = "enumClassName", value = "com.bayou.types.AdType")})
    private UserType adType;

    @Column(name = "price", columnDefinition = "MONEY")
    private Double price;

    @Column(name = "trade_item", columnDefinition = "VARCHAR")
    private String tradeItem;


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UserType getAdType() {
        return adType;
    }

    public void setAdType(UserType adType) {
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
