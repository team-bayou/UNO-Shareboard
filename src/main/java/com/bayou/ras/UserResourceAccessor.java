package com.bayou.ras;

import com.bayou.domains.User;
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

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findByAccountName(String accountName) {
         return userRepo.findByAccountName(accountName);
    }

    public User findById(Long id) {
        return userRepo.findOne(id);
    }

    public User add(User user) {
        return userRepo.save(user);
    }

    public void delete(Long id) {userRepo.delete(id);}
}
