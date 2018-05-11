package com.androidgits.mediaplayer.model;

/**
 * Created by Lenovo on 5/10/2018.
 */

public class Post {
    private String username;
    private String image_url;
    private String likes;
    private String status;
    private String TimeStamp;

    public Post() {
    }

    public Post(String username, String image_url, String likes, String status, String timeStamp) {
        this.username = username;
        this.image_url = image_url;
        this.likes = likes;
        this.status = status;
        TimeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
