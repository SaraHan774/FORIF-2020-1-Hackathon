package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.forif.honsullife.R;

public class RankingActivity extends AppCompatActivity {

    /*Firebase 에서 랭킹 정보 가져와서 랭킹 띄워주기.
    * 1, 2, 3등은 ViewPager 로 상단에, 나머지 팀은 RecyclerView 혹은 ListView 로 하단에 띄우기 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);


    }
}