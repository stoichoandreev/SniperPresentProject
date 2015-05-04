package com.sniper.presentproject.models;

import java.io.Serializable;

public class UserPostModel implements Serializable{
    private String userName;
    private int userLevel;
    private String userPicture;
    private long postTimestampDate;
    private String postText;
    private String postPicture;
    private String postVideo;
    private int postLikesCounter;
    private int postCommentsCounter;
    private int postSharesCounter;
    private int likesNumber;
    private int commentsNumber;
    private int sharesNumber;

    public int getPostCommentsCounter() {
        return postCommentsCounter;
    }

    public void setPostCommentsCounter(int postCommentsCounter) {
        this.postCommentsCounter = postCommentsCounter;
    }

    public int getPostLikesCounter() {
        return postLikesCounter;
    }

    public void setPostLikesCounter(int postLikesCounter) {
        this.postLikesCounter = postLikesCounter;
    }

    public String getPostPicture() {
        return postPicture;
    }

    public void setPostPicture(String postPicture) {
        this.postPicture = postPicture;
    }

    public int getPostSharesCounter() {
        return postSharesCounter;
    }

    public void setPostSharesCounter(int postSharesCounter) {
        this.postSharesCounter = postSharesCounter;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public long getPostTimestampDate() {
        return postTimestampDate;
    }

    public void setPostTimestampDate(long postTimestampDate) {
        this.postTimestampDate = postTimestampDate;
    }

    public String getPostVideo() {
        return postVideo;
    }

    public void setPostVideo(String postVideo) {
        this.postVideo = postVideo;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    public int getSharesNumber() {
        return sharesNumber;
    }

    public void setSharesNumber(int sharesNumber) {
        this.sharesNumber = sharesNumber;
    }
}
