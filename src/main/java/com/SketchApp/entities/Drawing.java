package com.SketchApp.entities;

import javax.persistence.*;

/**
 * Created by ericweidman on 6/4/17.
 */
@Entity
@Table(name = "drawings")
public class Drawing {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String base64result;

    @Column(nullable = false)
    private String fileLocation;

    @ManyToOne
    private User user;

    public Drawing() {
    }

    public Drawing(String title, String base64result, String fileLocation, User user) {
        this.title = title;
        this.base64result = base64result;
        this.fileLocation = fileLocation;
        this.user = user;
    }

    public String getBase64result() {
        return base64result;
    }

    public void setBase64result(String base64result) {
        this.base64result = base64result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
