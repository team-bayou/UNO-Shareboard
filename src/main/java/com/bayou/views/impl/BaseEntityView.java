package com.bayou.views.impl;

import com.bayou.views.IView;

/**
 * File: BaseEntityView
 * Package: com.bayou.views.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public abstract class BaseEntityView implements IView {
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntityView that = (BaseEntityView) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BaseEntityView{" +
                "id=" + id +
                '}';
    }
}
