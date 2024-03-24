package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private BlogRepository blogRepository;
    
    @Autowired
    private ImageRepository imageRepository;

    public Image addImage(Integer blogId, String description, String dimensions) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            Image image = new Image();
            image.setDescription(description);
            image.setDimensions(dimensions);
            image.setBlog(blog);
            return imageRepository.save(image);
        }
        return null;
    }

    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }

    public int calculateMaxImageCountOnScreen(Integer blogId, String screenDimensions) {
        try {
            String[] dimensions = screenDimensions.split("x");
            int screenWidth = Integer.parseInt(dimensions[0]);
            int screenHeight = Integer.parseInt(dimensions[1]);

            Blog blog = blogRepository.findById(blogId).orElse(null);
            if (blog != null) {
                List<Image> images = blog.getImage();
                if (!images.isEmpty()) {
                    Image sampleImage = images.get(0);
                    if (sampleImage != null && sampleImage.getDimensions() != null) {
                        String[] imageDimensions = sampleImage.getDimensions().split("x");
                        int imageWidth = Integer.parseInt(imageDimensions[0]);
                        int imageHeight = Integer.parseInt(imageDimensions[1]);

                        // Ensure imageWidth and imageHeight are not zero to avoid division by zero
                        if (imageWidth != 0 && imageHeight != 0) {
                            int horizontalCount = screenWidth / imageWidth;
                            int verticalCount = screenHeight / imageHeight;
                            return horizontalCount * verticalCount;
                        }
                    }
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Log the error or handle it appropriately.
            e.printStackTrace(); // Or log it using your preferred logging framework.
            // Return a default value or handle the error case based on your application's requirements.
        }

        return 0; // Default value if there's an error or no images can fit on the screen
    }
}
