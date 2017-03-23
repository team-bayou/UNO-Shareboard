package com.bayou.ras.impl;

import com.bayou.domains.Category;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.ICategoryRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

/**
 * File: CategoryResourceAccessor
 * Package: com.bayou.ras.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Service
public class CategoryResourceAccessor implements IResourceAccessor<Category> {
    @Autowired
    ICategoryRepository repo;

    @Override
    public Category find(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Long add(Category entity) {
        return repo.save(entity).getId();
    }

    @Override
    public Long update(Category entity) {

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
