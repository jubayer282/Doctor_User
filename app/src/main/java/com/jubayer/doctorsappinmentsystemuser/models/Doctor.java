package com.jubayer.doctorsappinmentsystemuser.models;

import java.io.Serializable;

public class Doctor implements Serializable {

    public String id;

    public String name;
    public String image;
    public String description;

    public String category;
    public String date;
    public String time;
    public String authorId;

    public Doctor() {
    }

    public Doctor(String name, String description, String time, String category, String date, String image, String authorId) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        this.date = date;
        this.time = time;
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
