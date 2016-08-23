package com.schibsted.android.chatbot.model.common;

import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.model.Chats;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author manasidiwan
 */
public interface ApiInterface {

    @GET("/rocket-interview/chat.json")
    Observable<Chats> getChatMessages();
}
