package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.forif.honsullife.R;

public class LoginActivity extends AppCompatActivity {

    /* 구글 로그인 구현하기 */

    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = findViewById(R.id.btn_google_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 1. load spinner 시작하기
                //2. 인증정보 저장하기 (?)

                //3. 로그인 완료되면 화면 넘어가기
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}