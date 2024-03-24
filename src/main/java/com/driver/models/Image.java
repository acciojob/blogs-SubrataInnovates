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
    private Integer id;
    private String description;
    private String dimensions;

    @JoinColumn
    @ManyToOne
    private Blog blog;

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
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
		return "Image [id=" + id + ", description=" + description + ", dimensions=" + dimensions + ", blog="
				+ blog + "]";
	}

	public Image(Integer id, String description, String dimensions, Blog blog) {
		super();
		this.id = id;
		this.description = description;
		this.dimensions = dimensions;
		this.blog = blog;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

    
  
}

