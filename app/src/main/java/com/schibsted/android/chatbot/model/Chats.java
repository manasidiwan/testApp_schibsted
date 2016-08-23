package com.schibsted.android.chatbot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by manasidiwan on 22/08/2016.
 */
public class Chats {

    @SerializedName("chats")
    private List<ChatMessage> mChats;

    public List<ChatMessage> getChats() {
        return mChats;
    }

    public void setChats(List<ChatMessage> chats) {
        this.mChats = chats;
    }
}
