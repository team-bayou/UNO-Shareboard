package com.bayou.ras;

import com.bayou.domains.BaseEntity;

import java.util.List;

/**
 * File: IResourceAccessor
 * Package: com.bayou.ras
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public interface IResourceAccessor<T extends BaseEntity> {
    T find(Long id);

    List<T> findAll();

    Long add(T entity);

    void update(T entity);

    void delete(Long id);
}
