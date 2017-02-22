package com.bayou.converters;

import com.bayou.domains.User;
import com.bayou.views.impl.UserView;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Component("UserConverter")
//registers this class as a component bean so that it can be pulled into the application context
public class UserConverter {
    public UserView convertToView(User domain) {
        UserView view = new UserView(); //this will be the newly created View version of the domain Object
        view.setId(domain.getId());
        view.setAccountName(domain.getAccountName());
        view.setFirstName(domain.getFirstName());
        view.setLastName(domain.getLastName());
        view.setPasswordHash(domain.getPasswordHash());
        view.setPasswordSalt(domain.getPasswordSalt());
        view.setUserType(domain.getUserType());
        view.setEmail(domain.getEmail());
        view.setPhoneNumber(domain.getPhoneNumber());
        view.setFacebookId(domain.getFacebookId());
        view.setTwitterHandle(domain.getTwitterHandle());
        view.setImageId(domain.getImageId());

        return view;    //return the View version of the given domain Object
    }

    public User convertToDomain(UserView view) {
        User domain = new User();   //this will be the newly created Domain version of the view Object
        domain.setAccountName(view.getAccountName());
        domain.setFirstName(view.getFirstName());
        domain.setLastName(view.getLastName());
        domain.setPasswordHash(view.getPasswordHash());
        domain.setPasswordSalt(view.getPasswordSalt());
        domain.setUserType(view.getUserType());
        domain.setEmail(view.getEmail());
        domain.setPhoneNumber(view.getPhoneNumber());
        domain.setFacebookId(view.getFacebookId());
        domain.setTwitterHandle(view.getTwitterHandle());
        domain.setImageId(view.getImageId());

        return domain;  //return the Domain version of the given view Object
    }
}

