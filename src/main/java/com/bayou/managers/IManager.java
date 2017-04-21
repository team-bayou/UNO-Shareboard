package com.bayou.managers;

import com.bayou.exceptions.ValidationException;
import com.bayou.views.BaseEntityView;
import javassist.NotFoundException;

import java.util.List;

/**
 * File: IManager
 * Package: com.bayou.managers
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public interface IManager<T extends BaseEntityView> {
    T get(Long id) throws NotFoundException;

    List<T> getAll() throws NotFoundException;

    Long add(T view) throws ValidationException;

    Long update(T view) throws ValidationException;

    void delete(Long id);
}
