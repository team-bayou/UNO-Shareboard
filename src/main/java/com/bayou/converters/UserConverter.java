package com.bayou.converters;

import com.bayou.Constants;
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
        view = setBooleanFlags(view, domain.getViewFlag());   //sets what values are to be displayed. eg. email, phone number, full name

        return view;    //return the View version of the given domain Object
    }

    public User convertToDomain(UserView view) throws ValidationException {
        User domain = new User();   //this will be the newly created Domain version of the view Object
        if (view.getId() != null) {
            domain.setId(view.getId());
        }
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

        if(view.isShowPhoneNumber() == null || view.isShowEmail() == null || view.isShowFullName() == null) {
           domain.setViewFlag(-1); //the -1 will indicate that flags were not sent back. Used later on in update conversion
        } else {
            domain.setViewFlag(getBinaryFlag(view));    //sets the binary flag for the domain object
        }
        
        if (validator.isValidFlag(domain.getViewFlag())) {
            return domain;  //return the Domain version of the given view Object
        } else {
            throw new ValidationException("Invalid data");
        }
    }

    //if user is given partial data on update
    //do a null check
    //if value from view is null
    //set the new domain objects value to the one that was retrieved from datasource
    public User updateConversion(User updatedUserState, User oldUserState) {

        if (updatedUserState.getAccountName() == null) {
            updatedUserState.setAccountName(oldUserState.getAccountName());
        }
        if (updatedUserState.getFirstName() == null) {
            updatedUserState.setFirstName(oldUserState.getFirstName());
        }
        if (updatedUserState.getLastName() == null) {
            updatedUserState.setLastName(oldUserState.getLastName());
        }
        if (updatedUserState.getPasswordHash() == null) {
            updatedUserState.setPasswordHash(oldUserState.getPasswordHash());
        }
        if (updatedUserState.getPasswordSalt() == null) {
            updatedUserState.setPasswordSalt(oldUserState.getPasswordSalt());
        }
        if (updatedUserState.getUserType() == null) {
            updatedUserState.setUserType(oldUserState.getUserType());
        }
        if (updatedUserState.getEmail() == null) {
            updatedUserState.setEmail(oldUserState.getEmail());
        }
        if (updatedUserState.getPhoneNumber() == null) {
            updatedUserState.setPhoneNumber(oldUserState.getPhoneNumber());
        }
        if (updatedUserState.getFacebookId() == null) {
            updatedUserState.setFacebookId(oldUserState.getFacebookId());
        }
        if (updatedUserState.getTwitterHandle() == null) {
            updatedUserState.setTwitterHandle(oldUserState.getTwitterHandle());
        }
        if (updatedUserState.getViewFlag() == null || updatedUserState.getViewFlag() == -1) {
            updatedUserState.setViewFlag(oldUserState.getViewFlag());
        }

        if(updatedUserState.getImageId() != null) {
            if (updatedUserState.getImageId() == -1) {   //null out the image id to let the delete happen
                updatedUserState.setImageId(null);
            } //else the updateUser id is not null and not -1 then do not reassign it a value
        } else {
            if(updatedUserState.getImageId() == null && oldUserState.getImageId() != null) { //preserve the old image id
                updatedUserState.setImageId(oldUserState.getImageId());
            } //else the the old user state is null also so do nothing
        }

        return updatedUserState;
    }

    public UserView setBooleanFlags(UserView view, int flag) {
        if ((flag & Constants.CODE_SHOW_FULL_NAME) != 0) {  //show full name
            view.setShowFullName(true);
        } else { view.setShowFullName(false); }
        if ((flag & Constants.CODE_SHOW_EMAIL) != 0) {  //show email
            view.setShowEmail(true);
        } else { view.setShowEmail(false); }
        if ((flag & Constants.CODE_SHOW_PHONE_NUMBER) != 0) { //show phone number
            view.setShowPhoneNumber(true);
        } else { view.setShowPhoneNumber(false);}

        return view;
    }

    private int getBinaryFlag(UserView view) {
        int flag = 0;

        if (view.isShowFullName() != null && view.isShowFullName()) {
            flag |= Constants.CODE_SHOW_FULL_NAME;
        }
        if (view.isShowEmail() != null && view.isShowEmail()) {
            flag |= Constants.CODE_SHOW_EMAIL;
        }
        if (view.isShowPhoneNumber() != null && view.isShowPhoneNumber()) {
            flag |= Constants.CODE_SHOW_PHONE_NUMBER;
        }

        return flag;
    }
}

