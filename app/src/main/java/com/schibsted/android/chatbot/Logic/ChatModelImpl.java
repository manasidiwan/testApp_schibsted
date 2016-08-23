package com.schibsted.android.chatbot.Logic;

import android.content.Context;
import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.model.Chats;
import com.schibsted.android.chatbot.model.common.ApiClient;
import com.schibsted.android.chatbot.model.common.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by manasidiwan on 01/08/2016.
 */
public class ChatModelImpl implements ChatModel {

    private Context mContext;


    public ChatModelImpl(Context context) {
        mContext = context;
    }

    @Override
    public Observable<Chats> getChatMessages() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        return apiService.getChatMessages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .asObservable();
    }
}
