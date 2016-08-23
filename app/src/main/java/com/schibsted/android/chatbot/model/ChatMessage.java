package com.schibsted.android.chatbot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by manasidiwan on 27/04/2016.
 */
public class ChatMessage {

    public enum chatType {
        SELF,
        OTHER_USERS
    }

    @SerializedName("username")
    private String mUsername;
    @SerializedName("content")
    private String mContent;
    @SerializedName("userImage_url")
    private String mUserImage;
    @SerializedName("time")
    private String mTime;
    private chatType mType = chatType.OTHER_USERS;

    public ChatMessage(String username, String content, String image, String time, chatType type) {
        mUsername = username;
        mContent = content;
        mUserImage = image;
        mTime = time;
        mType = type;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public String getUserImage() {
        return mUserImage;
    }

    public void setUserImage(String userImage) {
        this.mUserImage = userImage;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public chatType getType() {
        return mType;
    }

    public void setType(chatType type) {
        this.mType = type;
    }

}
