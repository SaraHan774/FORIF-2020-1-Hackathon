package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.forif.honsullife.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    /*1. 사용자 프로필 상단에 띄우기
    * 2. 우리팀 포스트 가로로 띄우기
    * 3. 다른 팀 포스트 작성 시간 순으로 세로로 띄우기
    * 4. AppBar 에다가 로그아웃, 랭킹보기 메뉴 넣기
    * */

    private FloatingActionButton fabNavigateToPublish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabNavigateToPublish = findViewById(R.id.fab_navigate_to_publish_activity);
        fabNavigateToPublish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PublishPostActivity.class);
                startActivity(intent);

            }
        });
    }
}
