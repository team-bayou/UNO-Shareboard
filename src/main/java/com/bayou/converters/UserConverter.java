package com.bayou.converters;

import com.bayou.domains.User;
import com.bayou.views.UserView;
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
        if(view.getId() != null) { domain.setId(view.getId()); }
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

    //if user is given partial data on update
    //do a null check
    //if value from view is null
    //set the new domain objects value to the one that was retrieved from datasource
    public User updateConversion(User updatedUserState, User oldUserState) {

        if(updatedUserState.getAccountName() == null) { updatedUserState.setAccountName(oldUserState.getAccountName());}
        if(updatedUserState.getFirstName() == null) { updatedUserState.setFirstName(oldUserState.getFirstName()); }
        if(updatedUserState.getLastName() == null ) { updatedUserState.setLastName(oldUserState.getLastName()); }
        if(updatedUserState.getPasswordHash() == null) { updatedUserState.setPasswordHash(oldUserState.getPasswordHash()); }
        if(updatedUserState.getPasswordSalt() == null) { updatedUserState.setPasswordSalt(oldUserState.getPasswordSalt()); }
        if(updatedUserState.getUserType() == null) { updatedUserState.setUserType(oldUserState.getUserType());}
        if(updatedUserState.getEmail() == null) { updatedUserState.setEmail(oldUserState.getEmail()); }
        if(updatedUserState.getPhoneNumber() == null) { updatedUserState.setPhoneNumber(oldUserState.getPhoneNumber()); }
        if(updatedUserState.getFacebookId() == null) { updatedUserState.setFacebookId(oldUserState.getFacebookId()); }
        if(updatedUserState.getTwitterHandle() == null) { updatedUserState.setTwitterHandle(oldUserState.getTwitterHandle()); }
        if(updatedUserState.getImageId() == null) { updatedUserState.setImageId(oldUserState.getImageId()); }

        return updatedUserState;
    }
}

