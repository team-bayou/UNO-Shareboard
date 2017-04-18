package com.bayou.converters;

import com.bayou.domains.Advertisement;
import com.bayou.utils.DomainMocks;
import com.bayou.utils.ViewMocks;
import com.bayou.views.AdvertisementView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * File: AdvertisementConverterTests
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AdvertisementConverterTests {
    @Autowired
    AdvertisementConverter converter;

    @Test
    public void testConvertToView() {
        Advertisement domain = DomainMocks.createAdvertisement();

        AdvertisementView view = converter.convertToView(domain);

        assertEquals(domain.getId(), view.getId());
        assertEquals(domain.getTitle(), view.getTitle());
        assertEquals(domain.getDescription(), view.getDescription());
        assertEquals(domain.getCategoryId(), view.getCategoryId());
        assertEquals(domain.getOwner(), view.getOwnerId());
        assertEquals(domain.getTimePublished(), view.getTimePublished());
        assertEquals(domain.getExpirationDate(), view.getExpirationDate());
        assertEquals(domain.getAdType(), view.getAdType());
        assertEquals(domain.getPrice(), view.getPrice());
        assertEquals(domain.getTradeItem(), view.getTradeItem());
        assertEquals(domain.getImageIDs(), view.getImageIDs());
    }

    @Test
    public void testConvertToDomain() {
        AdvertisementView view = ViewMocks.createAdvertisement();

        Advertisement domain = converter.convertToDomain(view);

        assertEquals(view.getId(), domain.getId());
        assertEquals(view.getTitle(), domain.getTitle());
        assertEquals(view.getDescription(), domain.getDescription());
        assertEquals(view.getCategoryId(), domain.getCategoryId());
        assertEquals(view.getOwnerId(), domain.getOwner());
        assertEquals(view.getTimePublished(), domain.getTimePublished());
        assertEquals(view.getExpirationDate(), domain.getExpirationDate());
        assertEquals(view.getAdType(), domain.getAdType());
        assertEquals(view.getPrice(), domain.getPrice());
        assertEquals(view.getTradeItem(), domain.getTradeItem());
        assertEquals(view.getImageIDs(), domain.getImageIDs());
    }
}
