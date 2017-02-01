package com.bayou.ras;

import com.bayou.domains.User;
import com.bayou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class UserResourceAccessor {

    @Autowired
    UserRepository userRepo = new UserRepository();

    public User addUser(User user) {

        return userRepo.addUser(user);  //access repo to add user
    }
}
