package com.schibsted.android.chatbot.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.schibsted.android.chatbot.R;

/**
 * A login screen that offers login via Name & Surname.
 */
public class LoginActivity extends AppCompatActivity {

    public static String EXTRA_USER_NAME = "extra_user_name";
    // UI references.
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mName = (EditText) findViewById(R.id.name);
        Button mNameSignInButton = (Button) findViewById(R.id.sign_in_button);
        mNameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mName.getText().toString().isEmpty()) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(BootstrapActivity.PREFS_USER_NAME, mName.getText().toString());
                    editor.apply();
                    attemptLogin();
                }
            }
        });
    }


    /**
     * Attempts to sign in with Name & Surname.
     * If there are form errors (invalid name, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mName.setError(null);

        // Store values at the time of the login attempt.
        String name = mName.getText().toString();

        boolean cancel = false;
        View focusView = null;
        String error = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(name)) {
            mName.setError(getString(R.string.error_field_required));
            focusView = mName;
            cancel = true;
        } else if ((error = isNameValid(name)) != null) {
            mName.setError(error);
            focusView = mName;
            cancel = true;
        }

        if (cancel) {
            // There was an error: don't attempt login and focus the first form field with an error.
            focusView.requestFocus();
        } else {
            // Start the new activity
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra(EXTRA_USER_NAME, mName.getText().toString());
            startActivityForResult(intent, 0);
        }
    }

    private String isNameValid(String name) {
        if (name.contains(" ")) {
            String namesArray[] = name.split(" ");
            if (namesArray.length != 2) {
                return getResources().getString(R.string.error_first_last_only);
            } else {
                if (!namesArray[0].matches("[A-Z][a-zA-Z]*") || !namesArray[1].matches("[A-Z][a-zA-Z]*") ) {
                    return getResources().getString(R.string.error_wrong_characters);
                }
            }
        };
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }
}

