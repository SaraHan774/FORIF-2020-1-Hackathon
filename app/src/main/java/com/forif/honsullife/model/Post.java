package com.forif.honsullife.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {

    private String postName;
    private String postContent;
    private String createdAt;
    private String photoUrl;
    private String userName;
    private String teamName;

    protected Post(Parcel in) {
        postName = in.readString();
        postContent = in.readString();
        createdAt = in.readString();
        photoUrl = in.readString();
        userName = in.readString();
        teamName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postName);
        dest.writeString(postContent);
        dest.writeString(createdAt);
        dest.writeString(photoUrl);
        dest.writeString(userName);
        dest.writeString(teamName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getPostName() {
        return postName;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getTeamName() {
        return teamName;
    }
}
