package com.bayou.converters;

import com.bayou.domains.User;
import com.bayou.utils.DomainMocks;
import com.bayou.utils.ViewMocks;
import com.bayou.views.UserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * File: UserConverterTests
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserConverterTests {
    @Autowired
    UserConverter converter;

    @Test
    public void testConvertToView() {
        User domain = DomainMocks.createUser();

        UserView view = converter.convertToView(domain);

        assertEquals(domain.getId(), view.getId());
        assertEquals(domain.getAccountName(), view.getAccountName());
        assertEquals(domain.getEmail(), view.getEmail());
        assertEquals(domain.getPasswordHash(), view.getPasswordHash());
        assertEquals(domain.getPasswordSalt(), view.getPasswordSalt());
        assertEquals(domain.getVerificationCode(), view.getVerificationCode());
        assertEquals(domain.getUserType(), view.getUserType());
        assertEquals(domain.getFirstName(), view.getFirstName());
        assertEquals(domain.getLastName(), view.getLastName());
        assertEquals(domain.getPhoneNumber(), view.getPhoneNumber());
        assertEquals(domain.getFacebookId(), view.getFacebookId());
        assertEquals(domain.getTwitterHandle(), view.getTwitterHandle());
        assertEquals(domain.getImageId(), view.getImageId());
    }

    @Test
    public void testConvertToDomain() {
        UserView view = ViewMocks.createUser();

        User domain = converter.convertToDomain(view);

        assertEquals(view.getId(), domain.getId());
        assertEquals(view.getAccountName(), domain.getAccountName());
        assertEquals(view.getEmail(), domain.getEmail());
        assertEquals(view.getPasswordHash(), domain.getPasswordHash());
        assertEquals(view.getPasswordSalt(), domain.getPasswordSalt());
        assertEquals(view.getVerificationCode(), domain.getVerificationCode());
        assertEquals(view.getUserType(), domain.getUserType());
        assertEquals(view.getFirstName(), domain.getFirstName());
        assertEquals(view.getLastName(), domain.getLastName());
        assertEquals(view.getPhoneNumber(), domain.getPhoneNumber());
        assertEquals(view.getFacebookId(), domain.getFacebookId());
        assertEquals(view.getTwitterHandle(), domain.getTwitterHandle());
        assertEquals(view.getImageId(), domain.getImageId());
    }
}
