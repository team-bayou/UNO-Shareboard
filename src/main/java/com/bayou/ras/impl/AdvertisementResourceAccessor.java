package com.bayou.ras.impl;

import com.bayou.domains.Advertisement;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * File: AdvertisementResourceAccessor
 * Package: com.bayou.ras
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
    public List<Advertisement> findAll() {
        return null;
    }

    @Override
    public void add(Advertisement entity) {
        repo.save(entity);
    }

    @Override
    public void update(Advertisement entity) {

    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
