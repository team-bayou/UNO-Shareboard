package com.bayou.utils;

import com.bayou.domains.*;
import com.bayou.types.AdType;
import com.bayou.types.UserType;

import java.util.Calendar;
import java.util.Random;

/**
 * File: DomainMocks
 * Package: com.bayou.utils
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public class DomainMocks {
    private static Random rand = new Random();

    public static User createUser() {
        User view = new User();
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
        view.setViewFlag(001);

        return view;
    }

    public static UnverifiedUser createUnverifiedUser() {
        UnverifiedUser view = new UnverifiedUser();
        view.setPasswordHash("jjjjjjj3                                                                                                                        ");
        view.setPasswordSalt("hhhhhhh3                                                        ");
        view.setEmail("jleaton" + rand.nextInt() + "@uno.edu");

        return view;
    }

    public static Category createCategory() {
        Category view = new Category();
        view.setTitle("Books");
        view.setColor("#123456");
        view.setDescription("Different kinds of books");

        return view;
    }

    public static Advertisement createAdvertisement() {
        Advertisement view = new Advertisement();
        view.setTitle("Literature for UNO");
        view.setDescription("Class notes for math");
        view.setTimePublished(Calendar.getInstance().getTime());
        view.setExpirationDate(Calendar.getInstance().getTime());
        view.setAdType(AdType.offer);
        view.setPrice(150.95);

        return view;
    }

    public static Review createReview() {
        Review view = new Review();
        view.setRating(3);
        view.setComments("Some text with comments which describes some quality aspects about the reviewee");

        return view;
    }

    public static Image createImage() {
        Image view = new Image();
        view.setImageMimeType("jpeg");
        view.setDescription("Some text describing the image");
        view.setImageData(new byte[]{1, 2, 3, 4, 5});
        view.setOwner(1L);
        view.setOrder(1);

        return view;
    }
}
