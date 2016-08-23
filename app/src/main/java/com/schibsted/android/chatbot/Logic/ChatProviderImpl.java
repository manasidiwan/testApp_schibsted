package com.schibsted.android.chatbot.Logic;

import android.content.Context;

import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.model.Chats;

import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;
import rx.Observable;

/**
 * @author manasidiwan
 */
public class ChatProviderImpl implements ChatProvider {


    ChatModel mModel;

    private Context mContext;


    public ChatProviderImpl(Context context) {
        mContext = context;
        mModel = new ChatModelImpl(context);
        //RoboGuice.getInjector(mContext).injectMembers(this);
    }

    @Override
    public Observable<Chats> getChatMessages() {
        return mModel.getChatMessages();
    }
}
