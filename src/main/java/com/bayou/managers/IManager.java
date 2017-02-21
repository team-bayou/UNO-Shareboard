package com.bayou.managers;

import com.bayou.views.IView;
import javassist.NotFoundException;

import java.util.List;

/**
 * File: IManager
 * Package: com.bayou.managers
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public interface IManager<T extends IView> {
    T get(Long id) throws NotFoundException;

    List<T> getAll() throws NotFoundException;

    Long add(T view);

    T update(T view);

    void delete(Long id);
}
