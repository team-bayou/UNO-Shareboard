package com.bayou.repository;

import com.bayou.domains.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    User findByAccountName(String accountName);

    User findByEmail(String email);

    User findByAccountNameIgnoreCase(String accountName);

    User findByEmailIgnoreCase(String email);

}
