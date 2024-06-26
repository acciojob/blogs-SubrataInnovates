package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setUser(user);
            blog.setPubDate(new Date());
            return blogRepository.save(blog);
        }
        return null;
    }

    public void deleteBlog(int blogId) {
        blogRepository.deleteById(blogId);
    }
}

