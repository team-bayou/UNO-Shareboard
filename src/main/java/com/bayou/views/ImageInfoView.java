package com.bayou.views;

/**
 * Created by Rachel on 3/19/2017.
 */
public class ImageInfoView extends BaseEntityView {

    private String description;
    private String imageMimeType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }
}
