package com.bayou.converters;

import com.bayou.domains.Advertisement;
import com.bayou.views.AdvertisementView;
import org.springframework.stereotype.Component;

/**
 * File: AdvertisementConverter
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Component("AdvertisementConverter")
public class AdvertisementConverter {

    public AdvertisementView convertToView(Advertisement domain) {
        AdvertisementView view = new AdvertisementView();
        view.setId(domain.getId());
        view.setTitle(domain.getTitle());
        view.setDescription(domain.getDescription());
        view.setCategoryId(domain.getCategoryId());
        view.setOwnerId(domain.getOwner());
        view.setTimePublished(domain.getTimePublished());
        view.setExpirationDate(domain.getExpirationDate());
        view.setAdType(domain.getAdType());
        view.setPrice(domain.getPrice());
        view.setTradeItem(domain.getTradeItem());

        return view;
    }

    public Advertisement convertToDomain(AdvertisementView view) {
        Advertisement domain = new Advertisement();
        domain.setId(view.getId());
        domain.setTitle(view.getTitle());
        domain.setDescription(view.getDescription());
        domain.setCategoryId(view.getCategoryId());
        domain.setOwner(view.getOwnerId());
        domain.setTimePublished(view.getTimePublished());
        domain.setExpirationDate(view.getExpirationDate());
        domain.setAdType(view.getAdType());
        domain.setPrice(view.getPrice());
        domain.setTradeItem(view.getTradeItem());

        return domain;
    }

    public Advertisement updateConversion(Advertisement updatedAdState, Advertisement oldAdState) {

        if(updatedAdState.getTitle() == null) {updatedAdState.setTitle(oldAdState.getTitle());}
        if(updatedAdState.getDescription() == null) updatedAdState.setDescription(oldAdState.getDescription());
        if(updatedAdState.getCategoryId() == null) updatedAdState.setCategoryId(oldAdState.getCategoryId());
        if(updatedAdState.getOwner() == null) updatedAdState.setOwner(oldAdState.getOwner());
        if(updatedAdState.getTimePublished() == null)  updatedAdState.setTimePublished(oldAdState.getTimePublished());
        if(updatedAdState.getExpirationDate() == null) updatedAdState.setExpirationDate(oldAdState.getExpirationDate());
        if(updatedAdState.getAdType() == null)  updatedAdState.setAdType(oldAdState.getAdType());
        if(updatedAdState.getPrice() == null)  updatedAdState.setPrice(oldAdState.getPrice());
        if(updatedAdState.getTradeItem() == null)  updatedAdState.setTradeItem(oldAdState.getTradeItem());

        return updatedAdState;
    }
}

