package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.dao.ImageDao;
import com.school.SpringSecuritywithDatabase.exc.ImageDoesntExist;
import com.school.SpringSecuritywithDatabase.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.ImageUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageDao imageDao;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            byte[] imageData = ImageUtils.compressImage(file.getBytes());
            String name = file.getOriginalFilename();
            String type = file.getContentType();
            Image image = new Image(name, type, imageData);

            imageDao.save(image);
            return "Image uploaded successfully";
        } catch (IOException e) {
            return "Error uploading image";
        }
    }

    @Override
    public byte[] downloadImage(String filename) {
        Optional<Image> dbImageData = imageDao.findByName(filename);
        if (dbImageData.isPresent()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        } else {
            throw new ImageDoesntExist("Image with name:" + filename + " doesn't exist");
        }
    }
}
