package com.bayou.repository;

import com.bayou.domains.User;
import org.springframework.stereotype.Repository;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Repository
public class UserRepository {

    //TODO: Not implemented with postgres database yet
    public User addUser(User user) {

        User returnUser = new User();

        returnUser.setUsername(user.getUsername());

        return user;

    }
}
