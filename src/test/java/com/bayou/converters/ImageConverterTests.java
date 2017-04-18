package com.bayou.converters;

import com.bayou.domains.Image;
import com.bayou.utils.DomainMocks;
import com.bayou.utils.ViewMocks;
import com.bayou.views.ImageView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * File: ImageConverterTests
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ImageConverterTests {
    @Autowired
    ImageConverter converter;

    @Test
    public void testConvertToView() {
        Image domain = DomainMocks.createImage();

        ImageView view = converter.convertToView(domain);

        assertEquals(domain.getId(), view.getId());
        assertEquals(domain.getDescription(), view.getDescription());
        assertEquals(domain.getImageData(), view.getImageData());
        assertEquals(domain.getOrder(), view.getOrder());
        assertEquals(domain.getOwner(), view.getOwner());
    }

    @Test
    public void testConvertToDomain() {
        ImageView view = ViewMocks.createImage();

        Image domain = converter.convertToDomain(view);

        assertEquals(view.getId(), domain.getId());
        assertEquals(view.getDescription(), domain.getDescription());
        assertEquals(view.getImageData(), domain.getImageData());
        assertEquals(view.getOrder(), domain.getOrder());
        assertEquals(view.getOwner(), domain.getOwner());
    }
}
