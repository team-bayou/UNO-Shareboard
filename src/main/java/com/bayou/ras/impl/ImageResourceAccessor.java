package com.bayou.ras.impl;

import com.bayou.domains.Image;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Rachel on 3/19/2017.
 */
public class ImageResourceAccessor implements IResourceAccessor<Image> {

    @Autowired
    IImageRepository repo;

    @Override
    public Long update(Image img) {
        return new Long(-1L);
    }

    @Override
    public Long add(Image entity) {
        return repo.save(entity).getId();
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

    @Override
    public Iterable<Image> findAll() {
        return repo.findAll();
    }

    @Override
    public Image find(Long id) {
        return repo.findOne(id);
    }

}
