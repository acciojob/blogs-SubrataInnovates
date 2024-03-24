package com.driver.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @JoinColumn
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Image> image;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", user=" + user + ", image="
                + image + "]";
    }

    public Blog(Integer id, String title, String content, User user, List<Image> image) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.image = image;
    }

    public Blog() {
        super();
    }
}
