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
        return view;
    }

    public Image convertToDomain(ImageView view) {
        Image image = new Image();
        image.setId(view.getId());
        image.setImageMimeType(view.getImageMimeType());
        image.setDescription(view.getDescription());
        image.setImageData(view.getImageData());
        return image;
    }

    public ImageInfoView convertToInfoView(ImageView view) {
        ImageInfoView infoView = new ImageInfoView();
        infoView.setImageMimeType(view.getImageMimeType());
        infoView.setDescription(view.getDescription());
        infoView.setId(view.getId());
        return infoView;
    }

}
