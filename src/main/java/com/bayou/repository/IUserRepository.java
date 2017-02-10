package com.bayou.repository;

import com.bayou.domains.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Repository
public class UserRepository extends CrudRepository <AppUser, Long>{

    //TODO: Not implemented with postgres database yet
    public AppUser addUser(AppUser user) {

        AppUser returnUser = new AppUser();

        returnUser.setUsername(user.getUsername());

        return user;

    }



    public AppUser

    /**
    @Override
    public <S extends T> S save(S s) {
        return null;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }
*/
    @Override
    public AppUser findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<AppUser> findAll() {
        return null;
    }

    @Override
    public Iterable<AppUser> findAll(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }


    @Override
    public void delete(AppUser appUser) {

    }

    @Override
    public void delete(Iterable<? extends AppUser> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
