package com.bayou.domains;


import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Rachel on 3/17/2017.
 */
@Entity(name = "Image")
@Table(name = "images")
@AttributeOverride(name = "id", column = @Column(name = "image_id"))
public class Image extends BaseEntity {

    @Column(name = "image_mime_type", columnDefinition = "VARCHAR")
    private String imageMimeType;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image_data", columnDefinition = "bytea")
    private byte[] imageData;

    @Column(name = "owner", columnDefinition = "integer")
    private Long owner;

    @Column(name = "image_order", columnDefinition = "integer")
    private Integer order;

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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
