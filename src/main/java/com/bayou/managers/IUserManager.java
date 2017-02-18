package com.bayou.managers;

import com.bayou.views.impl.UserView;
import javassist.NotFoundException;

/**
 * Created by joshuaeaton on 1/31/17.
 */
public interface IUserManager {

    void add(UserView userView);

    UserView update(UserView userView);

    public UserView get(Long id) throws NotFoundException;

    void delete(Long id);

}
