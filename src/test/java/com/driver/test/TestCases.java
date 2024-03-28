import com.driver.models.*;
import com.driver.repositories.*;
import com.driver.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCases {
    @InjectMocks
    BlogService blogService;

    @InjectMocks
    ImageService imageService;

    @InjectMocks
    UserService userService;

    @Mock
    BlogRepository blogRepository1;
    @Mock
    UserRepository userRepository1;
    @Mock
    ImageRepository imageRepository2;
    @Mock
    BlogRepository blogRepository2;
    @Mock
    UserRepository userRepository3;

    User user;
    Blog blog;
    Image image;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpassword");

        blog = new Blog();
        blog.setId(1);
        blog.setUser(user);
        blog.setTitle("Test Blog Title");
        blog.setContent("Test Blog Content");
        blog.setPubDate(new Date());

        image = new Image();
        image.setId(1);
        image.setBlog(blog);
        image.setDescription("Test Image Description");
        image.setDimensions("100X200");

        ArrayList<Image> imageList = new ArrayList<>();
        imageList.add(image);
        blog.setImageList(imageList);

        ArrayList<Blog> blogs = new ArrayList<>();
        blogs.add(blog);
        user.setBlogList(blogs);
    }

    @Test
    public void testCreateAndReturnBlog() {
        when(userRepository1.findById(any())).thenReturn(Optional.of(user));
        when(userRepository1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Blog blog1 = blogService.createAndReturnBlog(user.getId(), "Test Blog Title", "Test Blog Content");

        assertEquals(blog1.getUser().getUsername(), "testuser");
        assertEquals(blog1.getUser().getPassword(), "testpassword");
        assertEquals(blog1.getTitle(), blog.getTitle());
        assertEquals(blog1.getContent(), blog.getContent());
        assertEquals(blog1.getPubDate().toString(), blog.getPubDate().toString());

        verify(userRepository1, times(1)).save(any());
        verify(blogRepository1, times(0)).save(blog);
    }

    // Other test methods remain the same

    @Test
    public void testUpdateUser() {
        when(userRepository3.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(userRepository3.findById(any())).thenReturn(Optional.of(user));

        User user1 = userService.updateUser(1, "test");

        verify(userRepository3, times(1)).save(any());
        verify(userRepository3, times(1)).findById(any());

        assertEquals(user1.getUsername(), user.getUsername());
        assertEquals(user1.getPassword(), "test");
    }
}
