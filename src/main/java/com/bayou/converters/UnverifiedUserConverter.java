package com.bayou.converters;

import com.bayou.domains.UnverifiedUser;
import com.bayou.views.impl.UnverifiedUserView;
import org.springframework.stereotype.Component;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@Component("UnverifiedUserConverter") //registers this class as a component bean so that it can be pulled into the application context
public class UnverifiedUserConverter {


    public UnverifiedUserView convertToView(UnverifiedUser user) {
        UnverifiedUserView view = new UnverifiedUserView(); //this will be the newly created View version of the domain Object
        view.setId(user.getId());
        view.setPasswordHash(user.getPasswordHash());
        view.setPasswordSalt(user.getPasswordSalt());
        view.setEmail(user.getEmail());
        view.setVerificationCode(user.getVerificationCode());


        return view;    //return the View version of the given domain Object
    }

    public UnverifiedUser convertToDomain(UnverifiedUserView userView) {
        UnverifiedUser domainUser = new UnverifiedUser();   //this will be the newly created Domain version of the view Object
        domainUser.setPasswordHash(userView.getPasswordHash());
        domainUser.setPasswordSalt(userView.getPasswordSalt());
        domainUser.setEmail(userView.getEmail());
        domainUser.setVerificationCode(userView.getVerificationCode());

        return domainUser;  //return the Domain version of the given view Object
    }
}

