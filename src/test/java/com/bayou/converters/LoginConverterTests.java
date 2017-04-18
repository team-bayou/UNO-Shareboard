package com.bayou.converters;

import com.bayou.utils.ViewMocks;
import com.bayou.views.LoginView;
import com.bayou.views.UserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * File: LoginConverterTests
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginConverterTests {
    @Autowired
    LoginConverter converter;

    @Test
    public void testConvertToView() {
        UserView userView = ViewMocks.createUser();

        LoginView view = converter.convertToView(userView);

        assertEquals(userView.getId(), view.getId());
        assertEquals(userView.getEmail(), view.getEmail());
        assertEquals(userView.getAccountName(), view.getAccountName());
        assertEquals(userView.getPasswordSalt(), view.getPasswordSalt());
    }
}
