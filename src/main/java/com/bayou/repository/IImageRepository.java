package com.bayou.repository;

import com.bayou.domains.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Rachel on 3/19/2017.
 */
public interface IImageRepository extends CrudRepository<Image, Long> {

}
