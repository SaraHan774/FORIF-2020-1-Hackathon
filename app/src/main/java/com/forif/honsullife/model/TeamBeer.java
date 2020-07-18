package com.forif.honsullife.model;

public class TeamBeer {
    private int numPosts;
    private int participants;
    private int imgSrc;

    public TeamBeer(int numPosts, int participants) {
        this.numPosts = numPosts;
        this.participants = participants;
    }

    public int getNumPosts() {
        return numPosts;
    }

    public void setNumPosts(int numPosts) {
        this.numPosts = numPosts;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }
}
