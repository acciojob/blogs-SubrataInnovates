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
    private String description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Image [imageId=" + imageId + ", description=" + description + ", dimensions=" + dimensions + ", blog="
				+ blog + "]";
	}

	public Image(Integer imageId, String description, String dimensions, Blog blog) {
		super();
		this.imageId = imageId;
		this.description = description;
		this.dimensions = dimensions;
		this.blog = blog;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

    
  
}

