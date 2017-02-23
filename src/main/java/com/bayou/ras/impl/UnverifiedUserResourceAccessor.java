package com.bayou.ras.impl;

import com.bayou.domains.UnverifiedUser;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IUnverifiedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rachelguillory 2/16/2017.
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class UnverifiedUserResourceAccessor implements IResourceAccessor<UnverifiedUser> {
    @Autowired
    IUnverifiedUserRepository repo;

    @Override
    public UnverifiedUser find(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Iterable<UnverifiedUser> findAll() {
        return repo.findAll();
    }

    public UnverifiedUser findByEmail(String email) {
        return repo.findByEmailIgnoreCase(email);
    }

    @Override
    public Long add(UnverifiedUser entity) {
        return repo.save(entity).getId();
    }

    @Override
    public void update(UnverifiedUser entity) {

    }

    public void delete(Long id) {
        repo.delete(id);
    }
}
