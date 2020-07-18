package com.forif.honsullife.model;

public class User {
    private String userName;
    private String email;
    private String teamName;
    private String profileUrl;

    public static User instance;

    public static User getInstance() {
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", teamName='" + teamName + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }

    public User(){

    }

    public User(String userName, String email, String profileUrl) {
        this.userName = userName;
        this.email = email;
        this.profileUrl = profileUrl;
    }

    public User(String userName, String email, String teamName, String profileUrl) {
        this.userName = userName;
        this.email = email;
        this.teamName = teamName;
        this.profileUrl = profileUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
