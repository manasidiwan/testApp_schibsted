package com.schibsted.android.chatbot.Logic;

import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.model.Chats;

import java.util.List;

import rx.Observable;

/**
 * @author manasidiwan.
 */
public interface ChatModel {

    Observable<Chats> getChatMessages();


}
