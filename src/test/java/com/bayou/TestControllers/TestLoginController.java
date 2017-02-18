package com.bayou.TestControllers;

import com.bayou.controllers.LoginController;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by joshuaeaton on 2/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestLoginController {

    @Autowired
    LoginController loginController;

    @Test
    public void testLoginUser() throws NotFoundException {

        ResponseEntity<LoginView> responseEntity = loginController.login(getMockLoginView()); //add a mock user

        LoginView returnedLoginView = responseEntity.getBody();   //get the response from adding the user

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));  //assert that the status code is 200 OK
        assertThat(returnedLoginView.getEmail(), is("jleaton@uno.edu"));
        assertThat(returnedLoginView.getAccountName(), is("jleaton"));  //assert that the username is jleaton
        assertThat(returnedLoginView.getPasswordSalt(), is("passwordSalt"));
        assertThat(returnedLoginView.getPasswordHash(), is("passwordHash"));
    }

    private LoginView getMockLoginView() {

        LoginView loginView = new LoginView();
        loginView.setEmail("jleaton@uno.edu");
        loginView.setAccountName("jleaton");
        loginView.setPasswordSalt("passwordSalt");
        loginView.setPasswordHash("passwordHash");

        return loginView;
    }
}
