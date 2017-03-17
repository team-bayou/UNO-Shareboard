package com.bayou.domains;


import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by Rachel on 3/17/2017.
 */
@Entity(name = "Image")
@Table(name = "images")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class Image extends BaseEntity {

    @Column(name = "image_mime_type", columnDefinition = "VARCHAR")
    private String imageMimeType;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image_data", columnDefinition = "bytea")
    @Lob
    private Blob imageData;

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImageData() {
        return imageData;
    }

    public void setImageData(Blob imageData) {
        this.imageData = imageData;
    }

}
