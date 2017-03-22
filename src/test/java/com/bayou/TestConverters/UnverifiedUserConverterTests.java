package com.bayou.TestConverters;

import com.bayou.converters.UnverifiedUserConverter;
import com.bayou.domains.UnverifiedUser;
import com.bayou.views.UnverifiedUserView;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rachelguillory on 2/18/17.
 */
public class TestUnverifiedUserConverter {
    @Autowired
    UnverifiedUserConverter userConverter;

    @Test
    public void testConvertUnverifiedUserToViewSuccess() {
        UnverifiedUser user = getMockUser();

        UnverifiedUserView convertedView = userConverter.convertToView(user);

        assertThat(convertedView.getEmail(), is("jleaton3@uno.edu"));  //assert that the username is jleaton
    }

    @Test
    public void testConvertUnverifiedUserViewToDomainSuccess() {
        UnverifiedUserView userView = getMockUserView();

        UnverifiedUser convertedUser = userConverter.convertToDomain(userView);

        assertThat(convertedUser.getEmail(), is("jleaton3@uno.edu"));  //assert that the username is jleaton
    }

    private UnverifiedUserView getMockUserView() {
        UnverifiedUserView userView = new UnverifiedUserView();
        userView.setPasswordHash("jjjjjjj3");
        userView.setPasswordSalt("hhhhhhh3");
        userView.setEmail("jleaton3@uno.edu");
        userView.setVerificationCode(46555038);

        return userView;
    }

    private UnverifiedUser getMockUser() {
        UnverifiedUser user = new UnverifiedUser();
        user.setPasswordHash("jjjjjjj3");
        user.setPasswordSalt("hhhhhhh3");
        user.setEmail("jleaton3@uno.edu");
        user.setVerificationCode(46555038);

        return user;
    }
}
