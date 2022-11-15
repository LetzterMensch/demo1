package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.ContentType;
//import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "photos")
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    private String id;

    @Column(name = "content_type")
    private String contentType;

    @NotEmpty
    @Column(name = "file_name")
    private String fileName;
    //@JsonIgnore
    @Column(name = "data")
    private byte[] data;

    public Photo(String ID, String fileName) {
        this.id = ID;
        this.fileName = fileName;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
