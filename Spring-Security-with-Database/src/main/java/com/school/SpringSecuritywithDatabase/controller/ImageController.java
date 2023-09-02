package com.school.SpringSecuritywithDatabase.controller;

import com.school.SpringSecuritywithDatabase.model.Image;
import com.school.SpringSecuritywithDatabase.service.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file){
        String response = imageServiceImpl.uploadImage(file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadImage(@PathVariable String filename){
       byte[] imageData = imageServiceImpl.downloadImage(filename);
        // Create a ByteArrayResource to represent the image data
        ByteArrayResource resource = new ByteArrayResource(imageData);
        // Set the Content-Disposition header to suggest the browser's behavior
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageData.length)
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

    // display image
//    @GetMapping("/display")
//    public ResponseEntity<byte[]> displayImage(@RequestParam("id") int id) throws IOException, SQLException
//    {
//        Image image = imageServiceImpl.viewById(id);
//        byte [] imageBytes = null;
//        imageBytes = image.getImage().getBytes(1,(int) image.getImage().length());
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//    }
//    @GetMapping("/")
//    public ModelAndView home(){
//        ModelAndView mv = new ModelAndView("index");
//        List<Image> imageList = imageServiceImpl.viewAll();
//        mv.addObject("imageList", imageList);
//        return mv;
//    }
//
//    @GetMapping("/add")
//    public ModelAndView addImage(){
//        return new ModelAndView("addimage");
//    }
//
//    @PostMapping("/add")
//    public String addImagePost(HttpServletRequest request, @RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException, IOException, SQLException {
//        byte[] bytes = file.getBytes();
//        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//
//        Image image = new Image();
//        image.setImage(blob);
//        imageServiceImpl.create(image);
//        return "redirect:/";
//    }


