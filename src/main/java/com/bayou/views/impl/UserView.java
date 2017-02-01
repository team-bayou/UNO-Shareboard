package com.bayou.views.impl;

import com.bayou.views.IView;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by joshuaeaton on 1/31/17.
 */
public class UserView implements IView {



    private String username;


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    //TODO: the following methods may need to have case by case basis of implementations
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
