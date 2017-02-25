package com.bayou.ras;

import com.bayou.domains.BaseEntity;

/**
 * File: IResourceAccessor
 * Package: com.bayou.ras
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
public interface IResourceAccessor<T extends BaseEntity> {
    T find(Long id);

    Iterable<T> findAll();

    Long add(T entity);

    Long update(T entity);

    void delete(Long id);
}
