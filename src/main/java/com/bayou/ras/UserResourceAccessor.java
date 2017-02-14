package com.bayou.ras;

import com.bayou.domains.AppUser;
import com.bayou.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class UserResourceAccessor {

    @Autowired
    IUserRepository userRepo;

    public AppUser addUser(AppUser user) {

        return userRepo.save(user);//access repo to add user
    }
}
