package com.bayou.converters;

import com.bayou.domains.User;
import com.bayou.exceptions.ValidationException;
import com.bayou.validators.UserValidator;
import com.bayou.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Component("UserConverter")
//registers this class as a component bean so that it can be pulled into the application context
public class UserConverter {

    @Autowired
    UserValidator validator;

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
        view.setVerificationCode(domain.getVerificationCode());
        view = setBooleanFlags(domain, view);   //sets what values are to be displayed. eg. email, phone number, full name

        return view;    //return the View version of the given domain Object
    }

    public User convertToDomain(UserView view) throws ValidationException {
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
        domain.setVerificationCode(view.getVerificationCode());
        domain.setViewFlag(setBinaryFlag(domain, view));    //sets the binary flag for the domain object

        if(validator.isValidFlag(domain.getViewFlag())) {
            return domain;  //return the Domain version of the given view Object
        } else {
            throw new ValidationException("Invalid user data format");
        }
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

    public UserView setBooleanFlags(User domain , UserView view) {
        int fullNameMask = 001;
        int emailMask = 010;
        int phoneNumberMask = 100;

        if((domain.getViewFlag() & fullNameMask) != 0) {  //show full name
            view.setShowFullName(true);
        }
        if((domain.getViewFlag() & emailMask) != 0) {  //show email
            view.setShowEmail(true);
        }
        if((domain.getViewFlag() & phoneNumberMask) != 0) { //show phone number
            view.setShowPhoneNumber(true);
        }

        return view;
    }

    public int setBinaryFlag(User domain, UserView view) {

        int flag = 000;

        if(view.isShowFullName()) {
            flag += 001;
        }
        if(view.isShowEmail()) {
            flag += 010;
        }
        if(view.isShowPhoneNumber()) {
            flag += 100;
        }

        return flag;
    }
}

