package com.driver.test;
 
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
 
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
 
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
    public void testCreateAndReturnBlog(){
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setBlogList(new ArrayList<>());
        when(userRepository1.findById(any())).thenReturn(Optional.of(user));
        when(userRepository1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Blog blog1 = blogService.createAndReturnBlog(user.getId(), "Test Blog Title", "Test Blog Content");
        assertEquals(blog1.getUser().getUsername(), "testuser");
        assertEquals(blog1.getUser().getPassword(), "testpassword");
        assertEquals(blog1.getTitle(), blog.getTitle());
        assertEquals(blog1.getContent(), blog.getContent());
        assert(blog1.getPubDate().toString().equals(blog.getPubDate().toString()));
        verify(userRepository1, times(1)).save(any());
        verify(blogRepository1, never()).save(blog);
    }
 
    @Test
    public void testCreateAndReturnBlog1(){
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setBlogList(new ArrayList<>());
        when(userRepository1.findById(any())).thenReturn(Optional.of(user));
        when(userRepository1.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Blog blog1 = blogService.createAndReturnBlog(user.getId(), "Test Blog Title", "Test Blog Content");
        assertEquals(blog1.getUser().getUsername(), "testuser");
        assertEquals(blog1.getUser().getPassword(), "testpassword");
        assertEquals(blog1.getTitle(), blog.getTitle());
        assertEquals(blog1.getContent(), blog.getContent());
        assert(blog1.getPubDate().toString().equals(blog.getPubDate().toString()));
        verify(userRepository1, times(1)).save(any());
        verify(blogRepository1, never()).save(blog);
    }
 
    @Test
    public void testAddImage() {
        Blog blog = new Blog();
        blog.setId(1);
        blog.setUser(user);
        blog.setTitle("Test Blog Title");
        blog.setContent("Test Blog Content");
        blog.setPubDate(new Date());
        blog.setImageList(new ArrayList<>());
 
        Image image = new Image();
        image.setId(1);
        image.setBlog(blog);
        image.setDescription("Test Image Description");
        image.setDimensions("100X200");
 
        when(blogRepository2.findById(anyInt())).thenReturn(Optional.ofNullable(blog));
        when(blogRepository2.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Image image1 = imageService.addImage(blog.getId(), "Test Image Description", "100X200");
        assertEquals(1, blog.getImageList().size());
        assertEquals(image1.getDimensions(), image.getDimensions());
        assertEquals(image1.getBlog().getTitle(), blog.getTitle());
        assertEquals(image1.getDescription(), image.getDescription());
        verify(imageRepository2, times(0)).save(any());
        verify(blogRepository2, times(1)).save(any());
    }
 
    @Test
    public void testAddImage1() {
        Blog blog = new Blog();
        blog.setId(1);
        blog.setUser(user);
        blog.setTitle("Test Blog Title");
        blog.setContent("Test Blog Content");
        blog.setPubDate(new Date());
 
 
        Image image = new Image();
        image.setId(1);
        image.setBlog(blog);
        image.setDescription("Test Image Description");
        image.setDimensions("100X200");
 
        List<Image> imageList = new ArrayList<>();
        imageList.add(image);
        blog.setImageList(imageList);
 
        when(blogRepository2.findById(anyInt())).thenReturn(Optional.ofNullable(blog));
        when(blogRepository2.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Image image1 = imageService.addImage(blog.getId(), "Test Image Description", "100X200");
        assertEquals(2, blog.getImageList().size());
        assertEquals(image1.getDimensions(), image.getDimensions());
        assertEquals(image1.getBlog().getTitle(), blog.getTitle());
        assertEquals(image1.getDescription(), image.getDescription());
        verify(imageRepository2, times(0)).save(any());
        verify(blogRepository2, times(1)).save(any());
    }
 
    @Test
    public void testDeleteBlog() {
        blogService.deleteBlog(1);
        verify(blogRepository1, times(1)).deleteById(any());
    }
 
    @Test
    public void testDeleteImage() {
        imageService.deleteImage(1);
        verify(imageRepository2, times(1)).deleteById(any());
    }
 
    @Test
    public void testCountImage1() {
        Image image = new Image();
        image.setId(1);
        image.setDimensions("100X200");
        String screenDimensions = "500X700";
        int expected = 15;
        when(imageRepository2.findById(any())).thenReturn(Optional.of(image));
        int actual = imageService.countImagesInScreen(image.getId(), screenDimensions);
        assertEquals(expected, actual);
    }
 
    @Test
    public void testCountImage2() {
        Image image = new Image();
        image.setId(1);
        image.setDimensions("500X200");
        String screenDimensions = "500X800";
        int expected = 4;
        when(imageRepository2.findById(any())).thenReturn(Optional.of(image));
        int actual = imageService.countImagesInScreen(image.getId(), screenDimensions);
        assertEquals(expected, actual);
    }
 
    @Test
    public void testCountImage3() {
        Image image = new Image();
        image.setId(1);
        image.setDimensions("500X801");
        String screenDimensions = "500X800";
        int expected = 0;
        when(imageRepository2.findById(any())).thenReturn(Optional.of(image));
        int actual = imageService.countImagesInScreen(image.getId(), screenDimensions);
        assertEquals(expected, actual);
    }
 
    @Test
    public void testCountImage4() {
        Image image = new Image();
        image.setId(1);
        image.setDimensions("250X200");
        String screenDimensions = "500X800";
        int expected = 8;
        when(imageRepository2.findById(any())).thenReturn(Optional.of(image));
        int actual = imageService.countImagesInScreen(image.getId(), screenDimensions);
        assertEquals(expected, actual);
    }
 
    @Test
    public void testCountImage5() {
        Image image = new Image();
        image.setId(1);
        image.setDimensions("250X200");
        String screenDimensions = "500X800";
        int expected = 8;
        when(imageRepository2.findById(any())).thenReturn(Optional.of(image));
        int actual = imageService.countImagesInScreen(image.getId(), screenDimensions);
        assertEquals(expected, actual);
    }
 
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpassword");
        when(userRepository3.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        User user1 = userService.createUser("testuser", "testpassword");
        verify(userRepository3, times(1)).save(any());
        assertEquals(user1.getPassword(), user.getPassword());
        assertEquals(user1.getUsername(), user.getUsername());
    }
 
    @Test
    public void testCreateUser1() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpassword");
        when(userRepository3.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        User user1 = userService.createUser("testuser", "testpassword");
        verify(userRepository3, times(1)).save(any());
        assertEquals(user1.getPassword(), user.getPassword());
        assertEquals(user1.getUsername(), user.getUsername());
    }
 
    @Test
    public void testDeleteUser() {
        userService.deleteUser(1);
        verify(userRepository3, times(1)).deleteById(any());
    }
 
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("testpassword");
        when(userRepository3.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(userRepository3.findById(any())).thenReturn(Optional.of(user));
        User user1 = userService.updateUser(1, "test");
        verify(userRepository3, times(1)).save(any());
        verify(userRepository3, times(1)).findById(any());
        assertEquals(user1.getUsername(), user.getUsername());
        assertEquals(user1.getPassword(), "test");
    }
 
}
