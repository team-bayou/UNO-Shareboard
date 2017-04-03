package com.bayou.converters;


import com.bayou.views.LoginView;
import com.bayou.views.UserView;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 2/17/17.
 */
@Component("LoginConverter")
public class LoginConverter {
    public LoginView convertToLoginView(UserView user) {
        LoginView loginView = new LoginView();
        loginView.setId(user.getId());
        loginView.setEmail(user.getEmail());
        loginView.setAccountName(user.getAccountName());
        loginView.setPasswordSalt(user.getPasswordSalt());

        return loginView;
    }


}
