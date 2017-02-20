package com.bayou.converters;

import com.bayou.domains.User;
import com.bayou.views.impl.UserView;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Component("UserConverter") //registers this class as a component bean so that it can be pulled into the application context
public class UserConverter {


    public UserView convertToView(User user) {
        UserView view = new UserView(); //this will be the newly created View version of the domain Object
        view.setId(user.getId());
        view.setAccountName(user.getAccountName());
        view.setFirstName(user.getFirstName());
        view.setLastName(user.getLastName());
        view.setPasswordHash(user.getPasswordHash());
        view.setPasswordSalt(user.getPasswordSalt());
        view.setUserType(user.getUserType());
        view.setEmail(user.getEmail());
        view.setPhoneNumber(user.getPhoneNumber());
        view.setFacebookId(user.getFacebookId());
        view.setTwitterHandle(user.getTwitterHandle());
        view.setImageId(user.getImageId());

        return view;    //return the View version of the given domain Object
    }

    public User convertToDomain(UserView userView) {
        User domainUser = new User();   //this will be the newly created Domain version of the view Object
        domainUser.setAccountName(userView.getAccountName());
        domainUser.setFirstName(userView.getFirstName());
        domainUser.setLastName(userView.getLastName());
        domainUser.setPasswordHash(userView.getPasswordHash());
        domainUser.setPasswordSalt(userView.getPasswordSalt());
        domainUser.setUserType(userView.getUserType());
        domainUser.setEmail(userView.getEmail());
        domainUser.setPhoneNumber(userView.getPhoneNumber());
        domainUser.setFacebookId(userView.getFacebookId());
        domainUser.setTwitterHandle(userView.getTwitterHandle());
        domainUser.setImageId(userView.getImageId());

        return domainUser;  //return the Domain version of the given view Object
    }
}

