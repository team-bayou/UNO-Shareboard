package com.bayou.converters;


import com.bayou.domains.User;
import com.bayou.views.LoginView;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 2/17/17.
 */
@Component("LoginConverter") //registers this class as a component bean so that it can be pulled into the application context
public class LoginConverter {


    public LoginView convertToLoginView(User user) {

        LoginView loginView = new LoginView();

        loginView.setId(user.getId());
        loginView.setEmail(user.getEmail());
        loginView.setAccountName(user.getAccountName());
        loginView.setPasswordSalt(user.getPasswordSalt());

        return loginView;
    }


}
