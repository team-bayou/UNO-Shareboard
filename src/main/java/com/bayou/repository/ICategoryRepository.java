package com.bayou.repository;

import com.bayou.domains.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * File: ICategoryRepository
 * Package: com.bayou.repository
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Repository
public interface ICategoryRepository extends CrudRepository<Category, Long> {

}
