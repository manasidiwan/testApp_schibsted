package com.schibsted.android.chatbot.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.schibsted.android.chatbot.Logic.ChatProvider;
import com.schibsted.android.chatbot.Logic.ChatProviderImpl;
import com.schibsted.android.chatbot.R;
import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.model.Chats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import rx.Observable;
import rx.Observer;

public class ChatActivity extends AppCompatActivity
{

    private ChatProvider mProvider;

    private ArrayAdapter mAdapter;

    // Construct the data source
    private static ArrayList<ChatMessage> arrayOfMessages = new ArrayList<ChatMessage>();
    private Button mButton;
    private EditText mEditText;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_chat);
        mProvider = new ChatProviderImpl(this);
        mUserName = getIntent().getStringExtra(LoginActivity.EXTRA_USER_NAME);
        // Set the title on screen
        setTitle("Chat " + mUserName);
        // Create the adapter to convert the array to views
        mAdapter = new ChatAdapter(this, arrayOfMessages);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mButton = (Button) findViewById(R.id.button_send);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditText.getText().toString().isEmpty()) {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String time = sdf.format(cal.getTime());
                    ChatMessage message = new ChatMessage(mUserName, mEditText.getText().toString(), null, time, ChatMessage.chatType.SELF);
                    mAdapter.add(message);
                    mAdapter.notifyDataSetChanged();
                    mEditText.getText().clear();
                }
            }
        });
        // Attach the adapter to a ListView
        ListView listview = (ListView) findViewById(R.id.lvMessages);
        if (listview != null) {
            listview.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchChatMessages();
    }

    public void fetchChatMessages() {
        Observable<Chats> observable = mProvider.getChatMessages();
        Observer observer = new Observer<Chats>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                // show error dialog
            }

            @Override
            public void onNext(Chats chats) {
                arrayOfMessages.clear();
                arrayOfMessages.addAll(chats.getChats());
                mAdapter.notifyDataSetChanged();
            }
        };

        observable.subscribe(observer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.edit().remove(BootstrapActivity.PREFS_USER_NAME).commit();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
