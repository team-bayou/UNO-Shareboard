package com.bayou.TestControllers;

import com.bayou.controllers.UserController;
import com.bayou.views.impl.UserView;
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
 * Created by joshuaeaton on 2/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserController {
    @Autowired
    UserController userController;

    @Test
    public void testAddUserSuccess() {
        ResponseEntity<UserView> responseEntity = userController.add(getMockUserView());  //add a mock user

        UserView returnedView = responseEntity.getBody();   //get the response from adding the user

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));  //assert that the status code is 200 OK
        assertThat(returnedView.getAccountName(), is("jleaton3"));  //assert that the username is jleaton
    }

    private UserView getMockUserView() {
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

}
