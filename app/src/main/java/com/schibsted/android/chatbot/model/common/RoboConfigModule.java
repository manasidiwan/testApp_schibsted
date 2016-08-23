package com.schibsted.android.chatbot.model.common;

import android.app.Application;

import com.google.inject.AbstractModule;
import com.schibsted.android.chatbot.Logic.ChatModel;
import com.schibsted.android.chatbot.Logic.ChatModelImpl;
import com.schibsted.android.chatbot.Logic.ChatProvider;
import com.schibsted.android.chatbot.Logic.ChatProviderImpl;

/**
 * @author manasidiwan
 */
public class RoboConfigModule extends AbstractModule {

    /**
     * Constructor
     *
     * @param app application context
     */
    public RoboConfigModule(final Application app) {
    }

    @Override
    protected void configure() {
        bind(ChatProvider.class).to(ChatProviderImpl.class);
        bind(ChatModel.class).to(ChatModelImpl.class);
    }
}
