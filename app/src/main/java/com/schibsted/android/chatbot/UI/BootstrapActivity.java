package com.schibsted.android.chatbot.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by manasidiwan on 23/08/2016.
 */
public class BootstrapActivity extends AppCompatActivity {

    public static String PREFS_USER_NAME = "pref_user_name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString(PREFS_USER_NAME, null);
        if (name == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 0);
        } else {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra(LoginActivity.EXTRA_USER_NAME, name);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
