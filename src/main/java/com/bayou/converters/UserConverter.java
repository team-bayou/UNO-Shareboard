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
        view.setUser_id(user.getUser_id());
        view.setAccount_name(user.getAccount_name());
        view.setFirst_Name(user.getFirst_Name());
        view.setLast_Name(user.getLast_Name());
        view.setPassword_hash(user.getPassword_hash());
        view.setPassword_salt(user.getPassword_salt());
        view.setEmail(user.getEmail());
        view.setPhone_number(user.getPhone_number());
        view.setFacebook_id(user.getFacebook_id());
        view.setTwitter_handle(user.getTwitter_handle());
       // view.setImage_id(user.getImage_id());

        return view;    //return the View version of the given domain Object
    }

    public User convertToDomain(UserView userView) {

        User domainUser = new User();   //this will be the newly created Domain version of the view Object
        domainUser.setUser_id(userView.getUser_id());
        domainUser.setAccount_name(userView.getAccount_name());
        domainUser.setFirst_Name(userView.getFirst_Name());
        domainUser.setLast_Name(userView.getLast_Name());
        domainUser.setPassword_hash(userView.getPassword_hash());
        domainUser.setPassword_salt(userView.getPassword_salt());
        domainUser.setEmail(userView.getEmail());
        domainUser.setPhone_number(userView.getPhone_number());
        domainUser.setFacebook_id(userView.getFacebook_id());
        domainUser.setTwitter_handle(userView.getTwitter_handle());
       // domainUser.setImage_id(userView.getImage_id());

        return domainUser;  //return the Domain version of the given view Object
    }
}

