package com.forif.honsullife.model;

public class ViewPagerRanking {

    private int img;
    private int score;
    private String name;

    public ViewPagerRanking(int img, int score, String name) {
        this.img = img;
        this.score = score;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
