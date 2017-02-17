package com.bayou.repository;

import com.bayou.domains.UnverifiedUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@Repository
public interface IUnverifiedUserRepository extends CrudRepository <UnverifiedUser, Long>{

 //  User findByAccountName(String accountName);
    UnverifiedUser findByEmail(String email);
}
