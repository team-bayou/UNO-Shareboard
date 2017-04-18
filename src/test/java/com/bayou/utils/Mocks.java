package com.bayou.utils;

import com.bayou.types.AdType;
import com.bayou.types.UserType;
import com.bayou.views.*;

import java.util.Calendar;
import java.util.Random;

/**
 * File: Mocks
 * Package: com.bayou.utils
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public class Mocks {
    private static Random rand = new Random();

    public static UserView createUserView() {
        UserView view = new UserView();
        view.setAccountName("jleaton" + rand.nextInt());
        view.setPasswordHash("jjjjjjj3                                                                                                                        ");
        view.setPasswordSalt("hhhhhhh3                                                        ");
        view.setUserType(UserType.standard);
        view.setFirstName("Joshua3");
        view.setLastName("Eaton3");
        view.setEmail("jleaton" + rand.nextInt() + "@uno.edu");
        view.setPhoneNumber("5046555038");
        view.setFacebookId("Josh Eaton");
        view.setTwitterHandle("");

        return view;
    }

    public static UnverifiedUserView createUnverifiedUserView() {
        UnverifiedUserView view = new UnverifiedUserView();
        view.setPasswordHash("jjjjjjj3                                                                                                                        ");
        view.setPasswordSalt("hhhhhhh3                                                        ");
        view.setEmail("jleaton" + rand.nextInt() + "@uno.edu");

        return view;
    }

    public static VerifyUserView createVerifyUserViewForLoginByAccountName(UserView userView) {
        VerifyUserView view = new VerifyUserView();
        view.setAccountName(userView.getAccountName());
        view.setEnteredPasswordHash(userView.getPasswordHash());

        return view;
    }

    public static VerifyUserView createVerifyUserViewForLoginByEmail(UserView userView) {
        VerifyUserView view = new VerifyUserView();
        view.setEmail(userView.getEmail());
        view.setEnteredPasswordHash(userView.getPasswordHash());

        return view;
    }

    public static VerifyUserView createVerifyUserViewForVerification(UnverifiedUserView unverifiedUserView) {
        VerifyUserView view = new VerifyUserView();
        view.setAccountName("jleaton" + rand.nextInt());
        view.setEmail(unverifiedUserView.getEmail());
        view.setUserType(UserType.standard);
        view.setEnteredPasswordHash(unverifiedUserView.getPasswordHash());
        view.setEnteredVerificationCode(unverifiedUserView.getVerificationCode());

        return view;
    }

    public static VerifyUserView createVerifyUserViewForForgotPassword(UserView userView) {
        VerifyUserView view = new VerifyUserView();
        view.setEmail(userView.getEmail());
        view.setEnteredPasswordHash(userView.getPasswordHash());
        view.setEnteredVerificationCode(userView.getVerificationCode());

        return view;
    }

    public static VerifyUserView createVerifyUserViewForResetPassword(UserView userView) {
        VerifyUserView view = new VerifyUserView();
        view.setEmail(userView.getEmail());
        view.setPasswordHash("kkkkkkk4                                                                                                                        ");
        view.setPasswordSalt("ggggggg4                                                        ");
        view.setEnteredVerificationCode(userView.getVerificationCode());

        return view;
    }

    public static CategoryView createCategoryView() {
        CategoryView view = new CategoryView();
        view.setTitle("Books");
        view.setColor("#123456");
        view.setDescription("Different kinds of books");

        return view;
    }

    public static AdvertisementView createAdvertisementView() {
        AdvertisementView view = new AdvertisementView();
        view.setTitle("Literature for UNO");
        view.setDescription("Class notes for math");
        view.setTimePublished(Calendar.getInstance().getTime());
        view.setExpirationDate(Calendar.getInstance().getTime());
        view.setAdType(AdType.offer);
        view.setPrice(150.95);

        return view;
    }

    public static ReviewView createReviewView() {
        ReviewView view = new ReviewView();
        view.setRating(3);
        view.setComments("Some text with comments which describes some quality aspects about the reviewee");

        return view;
    }
}
