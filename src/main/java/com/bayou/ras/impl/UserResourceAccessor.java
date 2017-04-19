package com.bayou.ras.impl;

import com.bayou.domains.User;
import com.bayou.loggers.Loggable;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IUserRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class UserResourceAccessor implements IResourceAccessor<User> {
    @Autowired
    IUserRepository repo;

    @Override
    public User find(Long id) {

        return repo.findOne(id);
    }

    @Override
    public Iterable<User> findAll() {
        return repo.findAll();
    }

    public User findByEmail(String email) {
        return repo.findByEmailIgnoreCase(email);
    }

    public User findByAccountName(String accountName) {
        return repo.findByAccountNameIgnoreCase(accountName);
    }
    @Loggable
    @Override
    public Long add(User entity) {
        return repo.save(entity).getId();
    }
    @Loggable
    @Override
    public Long update(User entity) {

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
