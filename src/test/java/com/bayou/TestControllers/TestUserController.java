package com.bayou.TestControllers;

import com.bayou.controllers.UserControllerV1;
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
    UserControllerV1 userControllerV1;

    @Test
    public void testAddUserSuccess() {
        ResponseEntity<UserView> responseEntity = userControllerV1.addUser(getMockUserView());  //add a mock user

        UserView returnedView = responseEntity.getBody();   //get the response from adding the user

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));  //assert that the status code is 200 OK
        assertThat(returnedView.getUsername(), is("jleaton"));  //assert that the username is jleaton
    }

    private UserView getMockUserView() {
        UserView userView = new UserView();
        userView.setUsername("jleaton");
        return userView;
    }

}
