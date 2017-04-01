package com.bayou.managers;

import com.bayou.MainConfig;
import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.views.UnverifiedUserView;
import javax.ws.rs.NotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rachel on 2/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnverifiedUserManagerTests {
    @InjectMocks
    private UnverifiedUserManager userManager;
    @InjectMocks
    private MainConfig mainConfig;

    @Ignore
    @Test
    public void testAddUser() throws URISyntaxException {
        mainConfig.dataSource();
        Long id = userManager.add(createMockUser());
        UnverifiedUserView returnedView;
        try {
            returnedView = userManager.get(id);
            assertThat(returnedView.getEmail(), is("jleaton3@uno.edu"));
        } catch(NotFoundException e) {

        }
    }

    private static UnverifiedUserView createMockUser() {
        UnverifiedUserView userView = new UnverifiedUserView();
        userView.setPasswordHash("jjjjjjj3");
        userView.setPasswordSalt("hhhhhhh3");
        userView.setEmail("jleaton3@uno.edu");
        userView.setVerificationCode(46555038);

        return userView;
    }
}
