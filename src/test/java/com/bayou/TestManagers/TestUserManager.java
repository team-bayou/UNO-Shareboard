package com.bayou.TestManagers;

import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.UserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by joshuaeaton on 2/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserManager {
    @InjectMocks
    private UserManager userManager;

    @Test
    public void testAddUser() {
        UserView returnedView = userManager.add(createMockPart());
        assertThat(returnedView.getAccountName(), is("jleaton3"));
    }

    private static UserView createMockPart() {
        UserView userView = new UserView();
        userView.setAccountName("jleaton3");
        userView.setPasswordHash("jjjjjjj3");
        userView.setPasswordSalt("hhhhhhh3");
        userView.setFirstName("Joshua3");
        userView.setLastName("Eaton3");
        userView.setEmail("jleaton@uno.edu3");
        userView.setPhoneNumber("5046555038");
        userView.setFacebookId("Josh Eaton");
        userView.setTwitterHandle("");

        return userView;
    }
}
