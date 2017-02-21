package com.bayou.managers.impl;

import com.bayou.converters.AdvertisementConverter;
import com.bayou.domains.Advertisement;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.AdvertisementResourceAccessor;
import com.bayou.views.impl.AdvertisementView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    AdvertisementResourceAccessor ras = new AdvertisementResourceAccessor();

    @Autowired
    AdvertisementConverter converter = new AdvertisementConverter();

    public AdvertisementView get(Long id) throws NotFoundException {
        AdvertisementView adView;
        Advertisement ad = ras.find(id);

        if (ad == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            adView = converter.convertToView(ad);
        }

        return adView;
    }

    @Override
    public List<AdvertisementView> getAll() throws NotFoundException {
        return null;
    }

    @Override
    public HttpStatus add(AdvertisementView view) {
        HttpStatus status = HttpStatus.OK;

        try {
            ras.add(converter.convertToDomain(view));
        } catch (DataIntegrityViolationException e) {
            status = HttpStatus.CONFLICT;
            System.out.println("Advertisement: " + view.getTitle() + "already exist");
        }

        return status;
    }

    @Override
    public AdvertisementView update(AdvertisementView view) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            ras.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("The advertisement with ID:" + id + " does not exist in the database ");
        }
    }
}
