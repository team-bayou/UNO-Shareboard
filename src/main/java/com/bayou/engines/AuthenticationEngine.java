package com.bayou.engines;

import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.UnverifiedUserView;
import com.bayou.views.impl.UserView;
import com.bayou.views.impl.VerifyUserView;
import javassist.NotFoundException;
import com.bayou.exceptions.VerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Rachel on 2/21/2017.
 */
@Component("AuthenticationEngine")
public class AuthenticationEngine {

    @Autowired
    UnverifiedUserManager unverifiedUserManager = new UnverifiedUserManager();
    @Autowired
    UserManager userManager = new UserManager();

    public UserView verify(VerifyUserView verifyUserView) throws NotFoundException, VerificationException {
        UserView userView;
        UnverifiedUserView unverifiedUserView = unverifiedUserManager.getByEmail(verifyUserView.getEmail());
        verifyUserView.setPasswordHash(unverifiedUserView.getPasswordHash());
        verifyUserView.setPasswordSalt(unverifiedUserView.getPasswordSalt());
        if(verifyUserView.login() && unverifiedUserView.getVerificationCode().equals(verifyUserView.getEnteredVerificationCode())) {
            Long id = userManager.add(verifyUserView);
            userView = userManager.get(id);
            unverifiedUserManager.delete(unverifiedUserView.getId());
        } else {
            throw new VerificationException();
        }
        return userView;
    }

    public UserView login(VerifyUserView verifyUserView) throws NotFoundException, VerificationException {
        UserView userView;

        if(verifyUserView.getEmail() != null) {
            userView = userManager.getByEmail(verifyUserView.getEmail());
        } else if(verifyUserView.getAccountName() != null) {
            userView = userManager.getByAccountName(verifyUserView.getAccountName());
        } else {
            return null;
        }
        verifyUserView.setPasswordHash(userView.getPasswordHash());
        verifyUserView.setPasswordSalt(userView.getPasswordSalt());
        if(verifyUserView.login()) {
            return userView;
        } else {
            throw new VerificationException();
        }
    }
}
