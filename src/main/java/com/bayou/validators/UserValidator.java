package com.bayou.validators;

import com.bayou.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 4/20/17.
 */
@Component
public class UserValidator {
    /*Valid flag if 3 digits*/
    public boolean isValidFlag(int flag) {
        return flag <= Constants.CODE_SHOW_FULL_NAME + Constants.CODE_SHOW_EMAIL + Constants.CODE_SHOW_PHONE_NUMBER;
    }
}
