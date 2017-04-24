package com.bayou.repository;

import com.bayou.domains.Advertisement;
import com.bayou.types.AdType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    Page<Advertisement> findByCategoryIdIn(Long[] ids, Pageable pageable);

    Iterable<Advertisement> findByAdType(AdType adType);

    Page<Advertisement> findByAdType(AdType adType, Pageable pageable);

    Page<Advertisement> findByCategoryIdInAndAdType(Long[] ids, AdType adType, Pageable pageable);

    Page<Advertisement> findByCategoryIdInAndTitleContainingIgnoreCase(Long[] ids, String title, Pageable pageable);

    Page<Advertisement> findByCategoryIdInAndDescriptionContainingIgnoreCase(Long[] ids, String desc, Pageable pageable);

//    @Query("SELECT ads FROM Advertisement ads WHERE category_id IN (:ids) AND (upper(title) LIKE upper('%' || :title || '%') OR upper(description) LIKE upper('%' || :description || '%'))")
    Page<Advertisement> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategoryIdIn(String title, String desc, Long[] ids, Pageable pageable);

    Page<Advertisement> findByCategoryIdInAndTitleContainingIgnoreCaseAndAdType(Long[] ids, String title, AdType type, Pageable pageable);

    Page<Advertisement> findByCategoryIdInAndDescriptionContainingIgnoreCaseAndAdType(Long[] ids, String title, AdType type, Pageable pageable);

 //   @Query(value = "SELECT ads FROM Advertisement ads WHERE category_id IN (:ids) AND CAST(ad_type AS text) = CAST(:ad_type AS text) AND (upper(title) LIKE upper('%' || :title || '%') OR upper(description) LIKE upper('%' || :description || '%'))")
    Page<Advertisement> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategoryIdInAndAdType(String title, String desc, Long[] ids, AdType type, Pageable pageable);

    Page<Advertisement> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Advertisement> findByDescriptionContainingIgnoreCase(String desc, Pageable pageable);

    Page<Advertisement> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String desc, Pageable pageable);

    Page<Advertisement> findByTitleContainingIgnoreCaseAndAdType(String title, AdType type, Pageable pageable);

    Page<Advertisement> findByDescriptionContainingIgnoreCaseAndAdType(String title, AdType type, Pageable pageable);

//    @Query(value = "SELECT ads FROM Advertisement ads WHERE CAST(ad_type AS text) = CAST(:ad_type AS text) AND (upper(title) LIKE upper('%' || :title || '%') OR upper(description) LIKE upper('%' || :description || '%'))")
    Page<Advertisement> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndAdType(String title, String desc, AdType type, Pageable pageable);

    Integer countByOwner(Long id);
}
