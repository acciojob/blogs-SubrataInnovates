package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions)
    {
    	Blog blog = blogRepository2.findById(blogId).orElse(null);
    	Image image=new Image();
    	image.setDescriptions(description);
    	image.setDimensions(dimensions);
    	image.setBlog(blog);
    	return imageRepository2.save(image);
        

    }

    public void deleteImage(Integer id)
    {
    	imageRepository2.deleteById(id);

    }

   
    public int countImagesInScreen(Integer id, String screenDimensions) {
        try {
            String[] dimensions = screenDimensions.split("x");
            int screenWidth = Integer.parseInt(dimensions[0]);
            int screenHeight = Integer.parseInt(dimensions[1]);

            Blog blog = blogRepository2.findById(id).orElse(null);
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
