package com.driver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity

public class Image
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer imageId;
	private String descriptions;
	private String dimenstions;
	
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

	public String getDimenstions() {
		return dimenstions;
	}

	public void setDimenstions(String dimenstions) {
		this.dimenstions = dimenstions;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", descriptions=" + descriptions + ", dimenstions=" + dimenstions
				+ ", blog=" + blog + "]";
	}

	public Image(Integer imageId, String descriptions, String dimenstions, Blog blog) {
		super();
		this.imageId = imageId;
		this.descriptions = descriptions;
		this.dimenstions = dimenstions;
		this.blog = blog;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}