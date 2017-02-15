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
        view.setUsername(user.getUsername());

        return view;    //return the View version of the given domain Object
    }

    public User convertToDomain(UserView userView) {

        User domainUser = new User();   //this will be the newly created Domain version of the view Object
        domainUser.setUsername(userView.getUsername());

        return domainUser;  //return the Domain version of the given view Object
    }
}

