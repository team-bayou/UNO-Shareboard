package com.bayou.TestManagers;

import com.bayou.MainConfig;
import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
import javassist.NotFoundException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;

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
    @InjectMocks
    private MainConfig mainConfig;

    @Ignore
    @Test
    public void testAddUser() throws URISyntaxException {
       // mainConfig.dataSource();
       // UserView returnedView = userManager.add(createMockUser());
       // assertThat(returnedView.getAccountName(), is("jleaton3"));
    }
    @Ignore
    @Test
    public void testLogin() throws NotFoundException, URISyntaxException {

        LoginView returnedLoginView = userManager.login(createMockLoginView());
        assertThat(returnedLoginView.getAccountName(), is("jleaton"));
        assertThat(returnedLoginView.getEmail(), is("jleaton@uno.edu"));
        assertThat(returnedLoginView.getPasswordHash(), is("passwordHash"));
        assertThat(returnedLoginView.getPasswordSalt(), is("passwordSalt"));
    }

    private static UserView createMockUser() {
        UserView userView = new UserView();
        userView.setAccountName("jleaton3");
        userView.setPasswordSalt("hhhhhhh3");
        userView.setFirstName("Joshua3");
        userView.setLastName("Eaton3");
        userView.setEmail("jleaton@uno.edu3");
        userView.setPhoneNumber("5046555038");
        userView.setFacebookId("Josh Eaton");
        userView.setTwitterHandle("");

        return userView;
    }

    private static LoginView createMockLoginView() {

        LoginView loginView = new LoginView();
        loginView.setAccountName("jleaton");
        loginView.setEmail("jleaton@uno.edu");
        loginView.setPasswordSalt("passwordSalt");
        loginView.setPasswordHash("passwordHash");

        return loginView;
    }
}
