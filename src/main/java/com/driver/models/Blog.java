
package com.driver.models;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    @CreationTimestamp
    private Date pubDate;

    @ManyToOne
   
    private User user;

    
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)

    private List<Image> imageList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

//	@Override
//	public String toString() {
//		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", pubDate=" + pubDate + ", user="
//				+ user + ", imageList=" + imageList + "]";
//	}

	public Blog(Integer id, String title, String content, Date pubDate, User user, List<Image> imageList) {
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.pubDate = pubDate;
		this.user = user;
		this.imageList = imageList;
	}

	public Blog() {
			}


    
}
