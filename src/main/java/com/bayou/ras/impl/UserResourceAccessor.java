package com.bayou.ras.impl;

import com.bayou.domains.User;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IUserRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public Long add(User entity) {
        return repo.save(entity).getId();
    }

    @Override
    public Long update(User entity) {

        Long returnedID = -1L;

        try{
            entity = repo.save(entity);
            returnedID = entity.getId();
        }   //below catches the exceptions, need not do anything as returnedID will now stay -1L which is the flag for stale data
        catch(ObjectOptimisticLockingFailureException e ){}
        catch( StaleObjectStateException e){}

        return returnedID;
    }
    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
