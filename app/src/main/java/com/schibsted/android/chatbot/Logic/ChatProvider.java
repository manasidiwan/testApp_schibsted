package com.schibsted.android.chatbot.Logic;

import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.model.Chats;

import java.util.List;

import rx.Observable;

/**
 * @author manasidiwan on 01/08/2016.
 */
public interface ChatProvider {

    Observable<Chats> getChatMessages();

}
