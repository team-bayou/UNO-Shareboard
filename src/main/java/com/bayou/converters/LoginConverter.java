package com.bayou.converters;

import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 2/17/17.
 */
@Component("LoginConverter") //registers this class as a component bean so that it can be pulled into the application context
public class LoginConverter {

    public LoginView convertToLoginView(UserView userView) {

        LoginView loginView = new LoginView();
        loginView.setEmail(userView.getEmail());
        loginView.setAccount_name(userView.getAccountName());
        loginView.setPasswordHash(userView.getPasswordHash());
        loginView.setPasswordSalt(userView.getPasswordSalt());

        return loginView;
    }
}
