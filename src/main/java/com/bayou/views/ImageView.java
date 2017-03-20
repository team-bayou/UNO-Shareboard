package com.bayou.views;

/**
 * Created by Rachel on 3/19/2017.
 */
public class ImageView extends ImageInfoView {

    private byte[] imageData;

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
