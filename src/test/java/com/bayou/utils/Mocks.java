package com.bayou.utils;

import com.bayou.types.UserType;
import com.bayou.views.impl.LoginView;
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

    public static LoginView getLoginView() {
        LoginView view = new LoginView();
        view.setEmail("jleaton@uno.edu3");
        view.setAccountName("jleaton3");

        return view;
    }


    public static UserView getUserView() {
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
}
