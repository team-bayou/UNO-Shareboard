package com.bayou.repository;

import com.bayou.domains.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * File: IAdvertisementRepository
 * Package: com.bayou.repository
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Repository
public interface IAdvertisementRepository extends CrudRepository<Advertisement, Long> {
    Iterable<Advertisement> findByOwner(Long id);
}
