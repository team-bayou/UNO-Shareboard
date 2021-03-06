package com.bayou.managers.impl;

import com.bayou.converters.AdvertisementConverter;
import com.bayou.converters.CategoryConverter;
import com.bayou.converters.UserConverter;
import com.bayou.domains.Advertisement;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.AdvertisementResourceAccessor;
import com.bayou.ras.impl.CategoryResourceAccessor;
import com.bayou.ras.impl.UserResourceAccessor;
import com.bayou.types.AdType;
import com.bayou.views.AdvertisementView;
import com.bayou.views.CategoryView;
import com.bayou.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
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
    private AdvertisementResourceAccessor advertisementRas;

    @Autowired
    private CategoryResourceAccessor categoryRas;

    @Autowired
    private UserResourceAccessor userRas;

    @Autowired
    private AdvertisementConverter advertisementConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private UserConverter userConverter;

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

    public List<AdvertisementView> getAll(Integer page) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findAll(page))
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByOwner(Long id) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByOwner(id))
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByOwner(Long id, Integer page) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByOwner(id, page))
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByCategory(Long id) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByCategory(id))
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByCategory(Long id, Integer page) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByCategory(id, page))
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByCategories(Long[] ids, Integer page) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByCategoryIn(ids, page))
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByAdType(String adType) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByAdType(AdType.valueOf(adType)))
            views.add(prepare(ad));

        return views;
    }

    public List<AdvertisementView> getAllByAdType(String adType, Integer page) throws NotFoundException {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.findByAdType(AdType.valueOf(adType), page))
            views.add(prepare(ad));

        return views;
    }

    public Integer countByOwner(Long ownerID) {
        return advertisementRas.countByOwner(ownerID);
    }

    @Override
    public Long add(AdvertisementView view) {
        Long id = -1L;
        view.convertStrIDsToLong();
        try {
            id = advertisementRas.add(advertisementConverter.convertToDomain(view));
        } catch (DataIntegrityViolationException e) {
            System.err.println("Advertisement: " + view.getTitle() + " already exist");
        }

        return id;
    }

    public void addImage(Long adID, Long imageID) throws NotFoundException {
        AdvertisementView view = get(adID);
        view.addImage(imageID);
        update(view);
    }

    public void removeImage(Long adID, Long imageID) throws NotFoundException {
        AdvertisementView view = get(adID);
        view.removeImage(imageID);
        update(view);
    }

    @Override
    public Long update(AdvertisementView view) {
        view.convertStrIDsToLong();
        Advertisement ad = advertisementConverter.convertToDomain(view);    //converts the ad view to the user domain Object
        if (view.getId() == null) {   //triggers a no content if the id is null
            return -1L;
        }

        Advertisement retrievedAd = advertisementRas.find(view.getId());    //get the ad we are updating

        if (retrievedAd == null) {    //if the requested ad doesn't exist
            throw new javax.ws.rs.NotFoundException();
        }

        ad.setVersion(retrievedAd.getVersion());   //gets the record's we are updating version number

        ad = advertisementConverter.updateConversion(ad, retrievedAd); //adds values to any null properties that were not sent in the request on a partial update

        return advertisementRas.update(ad);
    }

    @Override
    public void delete(Long id) {
        try {
            advertisementRas.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The advertisement with ID:" + id + " does not exist in the database");
        }
    }

    public List<AdvertisementView> search(Long[] categoryIds, String title, String desc, AdType type, Integer page) {
        List<AdvertisementView> views = new ArrayList<>();

        for (Advertisement ad : advertisementRas.search(categoryIds, title, desc, type, page))
            views.add(prepare(ad));

        return views;

    }

    private AdvertisementView prepare(Advertisement ad) {
        AdvertisementView adView = advertisementConverter.convertToView(ad);

        CategoryView catView = categoryConverter.convertToView(categoryRas.find(ad.getCategoryId()));
        adView.setCategory(catView);

        UserView userView = userConverter.convertToView(userRas.find(ad.getOwner()));
        adView.setOwner(userView);

        return adView;
    }
}
