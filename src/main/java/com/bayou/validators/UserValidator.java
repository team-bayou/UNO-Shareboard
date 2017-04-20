package com.bayou.validators;

import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 4/20/17.
 */
@Component
public class UserValidator {

    /*Valid flag if 3 digits*/
    public boolean isValidFlag(int flag) {

        boolean validFlag = false;
        if(String.valueOf(flag).length() == 3) {
            validFlag = true;
        }

        return validFlag;
    }
}
