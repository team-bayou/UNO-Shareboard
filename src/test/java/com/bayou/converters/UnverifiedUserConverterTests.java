package com.bayou.converters;

import com.bayou.domains.UnverifiedUser;
import com.bayou.utils.DomainMocks;
import com.bayou.utils.ViewMocks;
import com.bayou.views.UnverifiedUserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by rachelguillory on 2/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UnverifiedUserConverterTests {
    @Autowired
    UnverifiedUserConverter converter;

    @Test
    public void testConvertToView() {
        UnverifiedUser domain = DomainMocks.createUnverifiedUser();

        UnverifiedUserView view = converter.convertToView(domain);

        assertEquals(domain.getId(), view.getId());
        assertEquals(domain.getEmail(), view.getEmail());
        assertEquals(domain.getPasswordHash(), view.getPasswordHash());
        assertEquals(domain.getPasswordSalt(), view.getPasswordSalt());
        assertEquals(domain.getVerificationCode(), view.getVerificationCode());
    }

    @Test
    public void testConvertToDomain() {
        UnverifiedUserView view = ViewMocks.createUnverifiedUser();

        UnverifiedUser domain = converter.convertToDomain(view);

        assertEquals(view.getId(), domain.getId());
        assertEquals(view.getEmail(), domain.getEmail());
        assertEquals(view.getPasswordHash(), domain.getPasswordHash());
        assertEquals(view.getPasswordSalt(), domain.getPasswordSalt());
        assertEquals(view.getVerificationCode(), domain.getVerificationCode());
    }
}
