package com.forif.honsullife.auth;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.forif.honsullife.ui.LoginActivity.USER_EMAIL_KEY;
import static com.forif.honsullife.ui.LoginActivity.USER_NAME_KEY;
import static com.forif.honsullife.ui.LoginActivity.USER_PROFILE_KEY;

public class Authentication {

    private Activity activity;

    public Authentication(Activity activity){
        this.activity = activity;
    }

    public void saveUserInfo(String name, String email, String profileUrl){
        SharedPreferences pref = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, USER_NAME_KEY);
        editor.putString(email, USER_EMAIL_KEY);
        if(profileUrl != null){
            editor.putString(profileUrl, USER_PROFILE_KEY);
        }
    }

    public void saveUserInfo(String name, String email){
        SharedPreferences pref = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, USER_NAME_KEY);
        editor.putString(email, USER_EMAIL_KEY);
    }
}
