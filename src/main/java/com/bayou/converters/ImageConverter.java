package com.bayou.converters;

import com.bayou.domains.Image;
import com.bayou.views.ImageInfoView;
import com.bayou.views.ImageView;
import org.springframework.stereotype.Component;

/**
 * Created by Rachel on 3/19/2017.
 */

@Component("ImageConverter")
public class ImageConverter {

    public ImageView convertToView(Image image) {
        ImageView view = new ImageView();
        view.setId(image.getId());
        view.setImageMimeType(image.getImageMimeType());
        view.setDescription(image.getDescription());
        view.setImageData(image.getImageData());
        view.setOwner(image.getOwner());
        view.setOrder(image.getOrder());
        return view;
    }

    public Image convertToDomain(ImageView view) {
        Image image = new Image();
        image.setId(view.getId());
        image.setImageMimeType(view.getImageMimeType());
        image.setDescription(view.getDescription());
        image.setImageData(view.getImageData());
        image.setOwner(view.getOwner());
        image.setOrder(view.getOrder());
        return image;
    }

    public ImageInfoView convertToInfoView(ImageView view) {
        ImageInfoView infoView = new ImageInfoView();
        infoView.setImageMimeType(view.getImageMimeType());
        infoView.setDescription(view.getDescription());
        infoView.setId(view.getId());
        infoView.setOwner(view.getOwner());
        infoView.setOrder(view.getOrder());
        return infoView;
    }

    public Image updateConversion(Image updatedImageState, Image oldImageState) {

        if(updatedImageState.getDescription() == null) updatedImageState.setDescription(oldImageState.getDescription());
        if(updatedImageState.getOwner() == null) updatedImageState.setOwner(oldImageState.getOwner());
        if(updatedImageState.getOrder() == null)  updatedImageState.setOrder(oldImageState.getOrder());

        // Image MIME type shouldn't be changed as it is based on the image_data field that is immutable.
        updatedImageState.setImageMimeType(oldImageState.getImageMimeType());
        updatedImageState.setImageData(oldImageState.getImageData());

        return updatedImageState;
    }
}
