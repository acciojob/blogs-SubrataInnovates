package com.driver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    private String descriptions;
    private String dimensions;

    @JoinColumn
    @ManyToOne
    private Blog blog;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "Image [imageId=" + imageId + ", descriptions=" + descriptions + ", dimensions=" + dimensions
                + ", blog=" + blog + "]";
    }

    public Image(Integer imageId, String descriptions, String dimensions, Blog blog) {
        super();
        this.imageId = imageId;
        this.descriptions = descriptions;
        this.dimensions = dimensions;
        this.blog = blog;
    }

    public Image() {
        super();
    }
}
