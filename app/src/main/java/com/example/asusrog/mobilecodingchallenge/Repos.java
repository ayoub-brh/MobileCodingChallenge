package com.example.asusrog.mobilecodingchallenge;

public class Repos {
    // variables to use
    private String repos_name,repos_desc,NbStars,imageUrl,username;

    // Empty constructor
    public Repos(){

    }

    // Constructor
    public Repos(String repos_name, String repos_desc, String imageUrl, String nbStars, String username) {
        this.repos_name = repos_name;
        this.repos_desc = repos_desc;
        this.imageUrl = imageUrl;
        this.NbStars = nbStars;
        this.username = username;
    }

    // Getters and Setters

    public String getRepos_name() {
        return repos_name;
    }

    public void setRepos_name(String repos_name) {
        this.repos_name = repos_name;
    }

    public String getRepos_desc() {
        return repos_desc;
    }

    public void setRepos_desc(String repos_desc) {
        this.repos_desc = repos_desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNbStars() {
        return NbStars;
    }

    public void setNbStars(String nbStars) {
        NbStars = nbStars;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
