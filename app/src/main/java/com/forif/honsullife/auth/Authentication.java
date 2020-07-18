package com.forif.honsullife.auth;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.forif.honsullife.model.User;

import static android.content.Context.MODE_PRIVATE;
import static com.forif.honsullife.ui.LoginActivity.USER_EMAIL_KEY;
import static com.forif.honsullife.ui.LoginActivity.USER_NAME_KEY;
import static com.forif.honsullife.ui.LoginActivity.USER_PROFILE_KEY;

public class Authentication {

    public static final String TEAM_NAME_KEY = "team_name_key";
    private static final String TAG = "Authentication";
    public static final String USER_PREF = "user_pref";
    private Activity activity;

    public static Authentication instance;

    public static Authentication getInstance() {
        if(instance == null){
            instance = new Authentication();
        }
        return instance;
    }

    public Authentication(){

    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void saveUserInfo(String name, String email, String profileUrl){
        SharedPreferences pref = activity.getSharedPreferences(USER_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_NAME_KEY, name);
        editor.putString(USER_EMAIL_KEY, email);
        if(profileUrl != null){
            editor.putString(USER_PROFILE_KEY, profileUrl);
        }
        Log.d(TAG, "saveUserInfo: 유저 정보 저장");
        editor.apply();
    }

    public void saveUserInfo(String name, String email){
        SharedPreferences pref = activity.getSharedPreferences(USER_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_NAME_KEY, name);
        editor.putString(USER_EMAIL_KEY,email);
        editor.apply();
        Log.d(TAG, "saveUserInfo: 유저 정보 저장 (사진 없음)");
    }

    public User getUserInfo(){
        SharedPreferences pref = activity.getSharedPreferences(USER_PREF, MODE_PRIVATE);
        String name = pref.getString(USER_NAME_KEY, "Anonymous");
        String email = pref.getString(USER_EMAIL_KEY, "No Email");
        String profileUrl = pref.getString(USER_PROFILE_KEY, "");
        String teamName = pref.getString(TEAM_NAME_KEY, "팀 없음");

        User user = User.getInstance();
        user.setUserName(name);
        user.setEmail(email);
        user.setProfileUrl(profileUrl);
        user.setTeamName(teamName);

        Log.d(TAG, "getUserInfo: " + user.toString());
        return user;
    }

    public void setUserTeam(String teamName){
        User user = User.getInstance();
        user.setTeamName(teamName);
        SharedPreferences pref = activity.getSharedPreferences(USER_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TEAM_NAME_KEY, teamName);
        editor.apply();
        Log.d(TAG, "setUserTeam: " + user.toString());
    }
}
