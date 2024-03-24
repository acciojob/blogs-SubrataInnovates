package com.driver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
	
}