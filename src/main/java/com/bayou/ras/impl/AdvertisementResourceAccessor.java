package com.bayou.ras.impl;

import com.bayou.domains.Advertisement;
import com.bayou.loggers.Loggable;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IAdvertisementRepository;
import com.bayou.types.AdType;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bayou.Constants.MAX_RESULTS;

/**
 * File: AdvertisementResourceAccessor
 * Package: com.bayou.ras.impl
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class AdvertisementResourceAccessor implements IResourceAccessor<Advertisement> {
    @Autowired
    IAdvertisementRepository repo;

    @Loggable
    @Override
    public Advertisement find(Long id) {
        return repo.findOne(id);
    }

    @Loggable
    @Override
    public Iterable<Advertisement> findAll() {
        return repo.findAll();
    }
    @Loggable
    public Iterable<Advertisement> findAll(Integer page) {
        return repo.findAll(pageAndSortByIdDesc(page));
    }
    @Loggable
    public Iterable<Advertisement> findByOwner(Long id) {
        return repo.findByOwner(id);
    }
    @Loggable
    public Iterable<Advertisement> findByOwner(Long id, Integer page) {
        return repo.findByOwner(id, pageAndSortByIdDesc(page));
    }
    @Loggable
    public Iterable<Advertisement> findByCategory(Long id) {
        return repo.findByCategoryId(id);
    }

    @Loggable
    public Iterable<Advertisement> findByCategory(Long id, Integer page) {
        return repo.findByCategoryId(id, pageAndSortByIdDesc(page));
    }
    @Loggable
    public Iterable<Advertisement> findByCategoryIn(Long[] ids, Integer page) {
        return repo.findByCategoryIdIn(ids, pageAndSortByIdDesc(page));
    }
    @Loggable
    public Iterable<Advertisement> search(Long[] categoryIds, String title, String desc, AdType type, Integer page) {
        if(categoryIds != null && categoryIds.length > 0) {
            if (title == null && desc == null && type == null) {
                return repo.findByCategoryIdIn(categoryIds, pageAndSortByIdDesc(page));
            } else if (title != null && desc == null && type == null) {
                return repo.findByCategoryIdInAndTitleContainingIgnoreCase(categoryIds, title, pageAndSortByIdDesc(page));
            } else if (title != null && desc != null && type == null) {
                return repo.findByCategoryIdInAndTitleContainingIgnoreCaseAndDescriptionContainingIgnoreCase(categoryIds, title, desc, pageAndSortByIdDesc(page));
            } else if (title != null && desc != null && type != null) {
                return repo.findByCategoryIdInAndTitleContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndAdType(categoryIds, title, desc, type, pageAndSortByIdDesc(page));
            } else if (title == null && desc != null && type == null) {
                return repo.findByCategoryIdInAndDescriptionContainingIgnoreCase(categoryIds, desc, pageAndSortByIdDesc(page));
            } else if (title == null && desc != null && type != null) {
                return repo.findByCategoryIdInAndDescriptionContainingIgnoreCaseAndAdType(categoryIds, desc, type, pageAndSortByIdDesc(page));
            } else if (title != null && desc == null && type != null) {
                return repo.findByCategoryIdInAndTitleContainingIgnoreCaseAndAdType(categoryIds, title, type, pageAndSortByIdDesc(page));
            } else if (title == null && desc == null && type != null) {
                return repo.findByCategoryIdInAndAdType(categoryIds, type, pageAndSortByIdDesc(page));
            }
        } else {
            if (title == null && desc == null && type == null) {
                return repo.findAll(pageAndSortByIdDesc(page));
            } else if (title != null && desc == null && type == null) {
                return repo.findByTitleContainingIgnoreCase(title, pageAndSortByIdDesc(page));
            } else if (title != null && desc != null && type == null) {
                return repo.findByTitleContainingIgnoreCaseAndDescriptionContainingIgnoreCase(title, desc, pageAndSortByIdDesc(page));
            } else if (title != null && desc != null && type != null) {
                return repo.findByTitleContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndAdType(title, desc, type, pageAndSortByIdDesc(page));
            } else if (title == null && desc != null && type == null) {
                return repo.findByDescriptionContainingIgnoreCase(desc, pageAndSortByIdDesc(page));
            } else if (title == null && desc != null && type != null) {
                return repo.findByDescriptionContainingIgnoreCaseAndAdType(desc, type, pageAndSortByIdDesc(page));
            } else if (title != null && desc == null && type != null) {
                return repo.findByTitleContainingIgnoreCaseAndAdType(title, type, pageAndSortByIdDesc(page));
            } else if (title == null && desc == null && type != null) {
                return repo.findByAdType(type, pageAndSortByIdDesc(page));
            }
        }
        return null;
    }

    public Integer countByOwner(Long id) {
        return repo.countByOwner(id);
    }

    @Loggable
    @Override
    public Long add(Advertisement entity) {
        return repo.save(entity).getId();
    }

    @Loggable
    @Override
    public Long update(Advertisement entity) {

        Long returnedID = -1L;
        if (entity.getId() == null) {    //handles the case of a null id being given for a update
            return -1L;
        }
        try {
            entity = repo.save(entity);
            returnedID = entity.getId();
        }   //below catches the exceptions, need not do anything as returnedID will now stay -1L which is the flag for stale data
        catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            // Ignore
        }

        return returnedID;
    }
    @Loggable
    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
    @Loggable
    private PageRequest pageAndSortByIdDesc(Integer page) {
        return new PageRequest(page - 1, MAX_RESULTS,
                new Sort(Sort.Direction.DESC, "id"));
    }
}
