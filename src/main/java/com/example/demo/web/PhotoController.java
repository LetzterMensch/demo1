package com.example.demo.web;

import com.example.demo.model.Photo;
import com.example.demo.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
public class PhotoController {

    private final PhotosService photosService;

    public PhotoController(@Autowired PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/photos")
    public Iterable<Photo> getPhotoMap() {
        return photosService.get();
    }

    @GetMapping("/photos/{id}")
    public Photo getRecipe(@PathVariable Integer id) {
        Photo photo = photosService.get(id);
        if(photo == null ) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }
    @DeleteMapping("/photos/{id}")
    public String deleteRecipe(@PathVariable Integer id){
        if(photosService.get(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        photosService.remove(id);
        return "Removed successfully !";
    }
    /* Using RequestBody*/
    @PostMapping("/create_photos")
    public Photo createPhoto(@RequestParam String file_name, @RequestParam String content_type){
        byte[] a = {(byte) 12321,123, (byte) 2332} ;
        return photosService.save(file_name,content_type,a);
    }
    @PostMapping("/photos")
    public Photo createRecipe(@RequestPart("data") MultipartFile file) throws IOException {
        return photosService.save(file.getOriginalFilename(),file.getContentType(),file.getBytes());
    }
}
