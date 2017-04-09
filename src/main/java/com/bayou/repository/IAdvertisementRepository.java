package com.bayou.repository;

import com.bayou.domains.Advertisement;
import com.bayou.types.AdType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * File: IAdvertisementRepository
 * Package: com.bayou.repository
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Repository
public interface IAdvertisementRepository extends PagingAndSortingRepository<Advertisement, Long> {
    Iterable<Advertisement> findByOwner(Long id);

    Page<Advertisement> findByOwner(Long id, Pageable pageable);

    Iterable<Advertisement> findByCategoryId(Long id);

    Page<Advertisement> findByCategoryId(Long id, Pageable pageable);

    Page<Advertisement> findByCategoryIdAndAdType(Long id, AdType adType, Pageable pageable);

    Page<Advertisement> findByCategoryIds(List<Long> ids, Pageable pageable);

    Page<Advertisement> findByCategoryIdsAndAdType(List<Long> ids, AdType adType, Pageable pageable);

    Page<Advertisement> findByCategoryIdsAndTitle(List<Long> ids, String title, Pageable pageable);

    Page<Advertisement> findByCategoryIdsAndAdTypeAndTitleContainingIgnoreCase(List<Long> ids, AdType adType, String title, Pageable pageable);

    Page<Advertisement> findByCategoryIdsAndAdTypeAndDescriptionContainingIgnoreCase(List<Long> ids, AdType adType, String description, Pageable pageable);

    Integer countByOwner(Long id);
}
