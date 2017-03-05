package com.bayou.managers.impl;

import com.bayou.converters.AdvertisementConverter;
import com.bayou.converters.CategoryConverter;
import com.bayou.converters.UserConverter;
import com.bayou.domains.Advertisement;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.AdvertisementResourceAccessor;
import com.bayou.ras.impl.CategoryResourceAccessor;
import com.bayou.ras.impl.UserResourceAccessor;
import com.bayou.views.impl.AdvertisementView;
import com.bayou.views.impl.CategoryView;
import com.bayou.views.impl.UserView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * File: AdvertisementManager
 * Package: com.bayou.managers.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Service
public class AdvertisementManager implements IManager<AdvertisementView> {
    @Autowired
    AdvertisementResourceAccessor advertisementRas = new AdvertisementResourceAccessor();

    @Autowired
    CategoryResourceAccessor categoryRas = new CategoryResourceAccessor();

    @Autowired
    UserResourceAccessor userRas = new UserResourceAccessor();

    @Autowired
    AdvertisementConverter converter = new AdvertisementConverter();

    @Autowired
    CategoryConverter categoryConverter = new CategoryConverter();

    @Autowired
    UserConverter userConverter = new UserConverter();

    public AdvertisementView get(Long id) throws NotFoundException {
        AdvertisementView adView;
        Advertisement ad = advertisementRas.find(id);

        if (ad == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            adView = prepare(ad);
        }

        return adView;
    }

    @Override
    public List<AdvertisementView> getAll() throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findAll())
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByOwner(Long ownerId) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByOwner(ownerId))
            views.add(prepare(ad));

        return views;
    }

    @Override
    public Long add(AdvertisementView view) {
        Long id = -1L;
        try {
            id = advertisementRas.add(converter.convertToDomain(view));
        } catch (DataIntegrityViolationException e) {
            System.err.println("Advertisement: " + view.getTitle() + " already exist");
        }

        return id;
    }

    @Override
    public Long update(AdvertisementView view) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            advertisementRas.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The advertisement with ID:" + id + " does not exist in the database");
        }
    }

    private AdvertisementView prepare(Advertisement ad) {
        AdvertisementView adView = converter.convertToView(ad);

        CategoryView catView = categoryConverter.convertToView(categoryRas.find(ad.getCategoryId()));
        adView.setCategory(catView);

        UserView userView = userConverter.convertToView(userRas.find(ad.getOwner()));
        adView.setOwner(userView);

        return adView;
    }
}
