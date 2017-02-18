package com.bayou.managers;

import com.bayou.views.impl.UnverifiedUserView;
import org.springframework.http.HttpStatus;

/**
 * Created by rachelguillory on 2/16/2017.
 */
public interface IUnverifiedUserManager {

    HttpStatus add(UnverifiedUserView userView);

    UnverifiedUserView update(UnverifiedUserView userView);

    UnverifiedUserView delete();

}
