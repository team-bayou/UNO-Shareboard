package com.bayou.ras;

import com.bayou.domains.UnverifiedUser;
import com.bayou.repository.IUnverifiedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rachelguillory 2/16/2017.
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class UnverifiedUserResourceAccessor {
    @Autowired
    IUnverifiedUserRepository userRepo;

    public UnverifiedUser getByEmail(String email) {
        return userRepo.findByEmail(email);
    }
/*
    public User getByAccountName(String accountName) {

         return userRepo.findByAccountName(accountName);//access repo to get user
    }
*/
    public UnverifiedUser getById(Long id) {
        return userRepo.findOne(id);
    }

    public UnverifiedUser add(UnverifiedUser user) {
        return userRepo.save(user);//access repo to add user
    }
}
