package com.example.demo.service;

import com.example.demo.model.Photo;
import com.example.demo.repository.PhotosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class PhotosService {
    private final PhotosRepository photosRepository;

    public Map<String, Photo> photoMap = new HashMap<>(){{
        put("1",new Photo("1","Hello World!"));
    }};

    public PhotosService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    public Iterable<Photo> get() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setID(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setData(data);
        photo.setContentType(contentType);
        photosRepository.save(photo);
        return photo;
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);
    }
}
