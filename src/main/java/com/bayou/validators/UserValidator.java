package com.bayou.validators;

import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 4/20/17.
 */
@Component
public class UserValidator {


    int[] flagArray = {000, 001, 010, 100, 110, 111};

    /*Valid flag if 3 digits*/
    public boolean isValidFlag(int flag) {

        boolean validFlag = false;

        for (int val: flagArray) {
            if(flag == val) {
                validFlag = true;
            }
        }

        return validFlag;
    }
}
