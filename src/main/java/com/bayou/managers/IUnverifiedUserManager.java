package com.bayou.managers;

import com.bayou.views.impl.UnverifiedUserView;

/**
 * Created by rachelguillory on 2/16/2017.
 */
public interface IUnverifiedUserManager {

    UnverifiedUserView add(UnverifiedUserView userView);

    UnverifiedUserView update(UnverifiedUserView userView);

    UnverifiedUserView get(UnverifiedUserView userView);

    UnverifiedUserView delete();

}
