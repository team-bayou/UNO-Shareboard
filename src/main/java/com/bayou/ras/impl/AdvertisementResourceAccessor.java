package com.bayou.ras.impl;

import com.bayou.domains.Advertisement;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IAdvertisementRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

/**
 * File: AdvertisementResourceAccessor
 * Package: com.bayou.ras.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class AdvertisementResourceAccessor implements IResourceAccessor<Advertisement> {
    @Autowired
    IAdvertisementRepository repo;

    @Override
    public Advertisement find(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Iterable<Advertisement> findAll() {
        return repo.findAll();
    }

    public Iterable<Advertisement> findByOwner(Long id) {
        return repo.findByOwner(id);
    }

    public Iterable<Advertisement> findByCategory(Long id) {
        return repo.findByCategoryId(id);
    }

    public Integer countByOwner(Long id) {
        return repo.countByOwner(id);
    }

    @Override
    public Long add(Advertisement entity) {
        return repo.save(entity).getId();
    }

    //TODO:implement
    @Override
    public Long update(Advertisement entity) {

        Long returnedID = -1L;
        if (entity.getId() == null) {    //handles the case of a null id being given for a update
            return -1L;
        }
        try {
            entity = repo.save(entity);
            returnedID = entity.getId();
        }   //below catches the exceptions, need not do anything as returnedID will now stay -1L which is the flag for stale data
        catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            // Ignore
        }

        return returnedID;
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
