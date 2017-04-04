package com.bayou.ras.impl;

import com.bayou.domains.Image;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IImageRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

/**
 * Created by Rachel on 3/19/2017.
 */
@Service
public class ImageResourceAccessor implements IResourceAccessor<Image> {

    @Autowired
    IImageRepository repo;

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

    public Iterable<Image> findByOwner(Long id) { return repo.findByOwner(id); }

    @Override
    public Long update(Image entity) {

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
}
