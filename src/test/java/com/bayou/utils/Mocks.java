package com.bayou.utils;

import com.bayou.types.UserType;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UnverifiedUserView;
import com.bayou.views.impl.UserView;

import java.util.Random;

/**
 * File: Mocks
 * Package: com.bayou.utils
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public class Mocks {
    private static Random rand = new Random();

    public static LoginView createLoginView() {
        LoginView view = new LoginView();
        view.setEmail("jleaton" + rand.nextInt() + "@uno.edu3");
        view.setAccountName("jleaton" + rand.nextInt());

        return view;
    }

    public static UserView createUserView() {
        UserView view = new UserView();
        view.setAccountName("jleaton" + rand.nextInt());
        view.setPasswordHash("jjjjjjj3");
        view.setPasswordSalt("hhhhhhh3");
        view.setUserType(UserType.standard);
        view.setFirstName("Joshua3");
        view.setLastName("Eaton3");
        view.setEmail("jleaton" + rand.nextInt() + "@uno.sa.edu.");
        view.setPhoneNumber("5046555038");
        view.setFacebookId("Josh Eaton");
        view.setTwitterHandle("");

        return view;
    }

    public static UnverifiedUserView createUnverifiedUserView() {
        UnverifiedUserView view = new UnverifiedUserView();
        view.setPasswordHash("jjjjjjj3");
        view.setPasswordSalt("hhhhhhh3");
        view.setEmail("jleaton" + rand.nextInt() + "@uno.sa.edu.");
        view.setVerificationCode(46555038);

        return view;
    }
}
