package com.school.SpringSecuritywithDatabase.service;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
     String uploadImage(MultipartFile file);

     byte[] downloadImage(String filename);

}
