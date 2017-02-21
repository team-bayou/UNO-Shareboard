package com.bayou.ControllerTests;

import com.bayou.controllers.UnverifiedUserController;
import com.bayou.views.impl.UnverifiedUserView;
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
 * Created by rachelguillory on 2/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUnverifiedUserController {
    @Autowired
    private UnverifiedUserController userController;

    @Test
    public void testAddUnverifiedUserSuccess() {
        ResponseEntity<Long> responseEntity = userController.add(getMockUserView());  //add a mock user

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));  //assert that the status code is 200 OK
    }

    private UnverifiedUserView getMockUserView() {
        UnverifiedUserView userView = new UnverifiedUserView();
        userView.setPasswordHash("jjjjjjj3");
        userView.setPasswordSalt("hhhhhhh3");
        userView.setEmail("jleaton3@uno.edu");
        userView.setVerificationCode(46555038);

        return userView;
    }

}
