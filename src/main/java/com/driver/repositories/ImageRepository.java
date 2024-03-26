package com.driver.repositories;

import com.driver.models.Blog;
import com.driver.models.Image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>
{
	 List<Image> findByBlog(Blog blog);

}
