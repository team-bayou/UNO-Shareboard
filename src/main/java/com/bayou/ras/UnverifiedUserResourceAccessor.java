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

    public UnverifiedUser findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UnverifiedUser findById(Long id) {
        return userRepo.findOne(id);
    }

    public UnverifiedUser add(UnverifiedUser user) {
        return userRepo.save(user);//access repo to add user
    }

    public void delete(Long id) {userRepo.delete(id);}
}
