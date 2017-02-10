package com.bayou.repository;

import com.bayou.domains.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Repository
public interface IUserRepository extends CrudRepository <AppUser, Long>{

}
