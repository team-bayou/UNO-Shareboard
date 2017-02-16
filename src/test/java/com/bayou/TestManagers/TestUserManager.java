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
        assertThat(returnedView.getAccountName(), is("jleaton"));
    }

    private static UserView createMockPart() {
        UserView userView = new UserView();
        userView.setAccountName("jleaton");
        return userView;
    }
}
