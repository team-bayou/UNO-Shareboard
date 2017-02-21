package com.bayou.converters;

import com.bayou.domains.Advertisement;
import com.bayou.views.impl.AdvertisementView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * File: AdvertisementConverter
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Component("AdvertisementConverter")
public class AdvertisementConverter {
    @Autowired
    UserConverter userConverter = new UserConverter();

    @Autowired
    CategoryConverter categoryConverter = new CategoryConverter();

    public AdvertisementView convertToView(Advertisement domain) {
        AdvertisementView view = new AdvertisementView();
        view.setId(domain.getId());
        view.setTitle(domain.getTitle());
        view.setDescription(domain.getDescription());
        view.setCategory(categoryConverter.convertToView(domain.getCategory()));
        view.setOwner(userConverter.convertToView(domain.getOwner()));
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
        domain.setCategory(categoryConverter.convertToDomain(view.getCategory()));
        domain.setOwner(userConverter.convertToDomain(view.getOwner()));
        domain.setTimePublished(view.getTimePublished());
        domain.setExpirationDate(view.getExpirationDate());
        domain.setAdType(view.getAdType());
        domain.setPrice(view.getPrice());
        domain.setTradeItem(view.getTradeItem());

        return domain;
    }
}

