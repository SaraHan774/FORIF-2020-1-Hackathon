package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.forif.honsullife.R;

public class MainActivity extends AppCompatActivity {

    /*1. 사용자 프로필 상단에 띄우기
    * 2. 우리팀 포스트 가로로 띄우기
    * 3. 다른 팀 포스트 작성 시간 순으로 세로로 띄우기
    * 4. AppBar 에다가 로그아웃, 랭킹보기 메뉴 넣기
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}