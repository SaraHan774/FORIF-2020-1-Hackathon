package com.forif.honsullife.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.forif.honsullife.R;
import com.forif.honsullife.auth.Authentication;
import com.forif.honsullife.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    /* 구글 로그인 구현하기 */
    private static final String TAG = "LoginActivity";
    public static final int RC_SING_IN = 1000;
    public static final String USER_NAME_KEY = "user_name_key";
    public static final String USER_PROFILE_KEY = "user_profile_key";
    public static final String USER_EMAIL_KEY = "user_email_key";

    private Button btnLogin;
    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authentication = Authentication.getInstance();
        authentication.setActivity(this);

        btnLogin = findViewById(R.id.btn_google_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSignInIntent();
            }
        });
    }

    private void createSignInIntent(){
        List<AuthUI.IdpConfig> provider = Collections.singletonList(
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(provider)
                        .build(),
                RC_SING_IN
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SING_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if(resultCode == RESULT_OK){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Log.d(TAG, "onActivityResult: "
                + user.getDisplayName() + "\n"
                + user.getEmail() + "\n"
                + user.getPhotoUrl());

                if(user.getPhotoUrl() != null) {
                    authentication.saveUserInfo(
                            user.getDisplayName(),
                            user.getEmail(),
                            user.getPhotoUrl().toString()
                    );
                }else{
                    authentication.saveUserInfo(
                            user.getDisplayName(),
                            user.getEmail()
                    );
                }
                navigateToTeamSelectionActivity();
            }else{
                //failed
                Log.d(TAG, "onActivityResult: " + response.getError().getErrorCode());
                Toast.makeText(this, "구글 로그인 실패", Toast.LENGTH_SHORT);
            }
        }
    }

    private void navigateToTeamSelectionActivity(){
        Intent intent = new Intent(LoginActivity.this, SelectTeamActivity.class);
        startActivity(intent);
        finish();
    }
}