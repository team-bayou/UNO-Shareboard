package com.bayou.ras.impl;

import com.bayou.domains.Category;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void update(Category entity) {

    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
