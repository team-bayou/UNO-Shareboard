package com.bayou.ras;

import com.bayou.domains.Advertisement;
import com.bayou.repository.IAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * File: AdvertisementResourceAccessor
 * Package: com.bayou.ras
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class AdvertisementResourceAccessor {
    @Autowired
    IAdvertisementRepository advertisementRepo;

    public Advertisement findById(Long id) {
        return advertisementRepo.findOne(id);
    }

    public void add(Advertisement advertisement) {
        advertisementRepo.save(advertisement);
    }

    public void delete(Long id) {
        advertisementRepo.delete(id);
    }
}
