package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.forif.honsullife.R;
import com.forif.honsullife.auth.Authentication;
import com.forif.honsullife.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
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

        Authentication authentication = Authentication.getInstance();
        authentication.setActivity(this);
        Log.d(TAG, "onCreate: " + authentication.getUserInfo());
        User user = authentication.getUserInfo();

        TextView tvUserTeam = findViewById(R.id.tv_profile_team_name);
        TextView tvUserName = findViewById(R.id.tv_profile_username);
        ImageView profileImage = findViewById(R.id.img_profile_user_image);

        if(user.getUserName().equals("Anonymous")){
            String email = user.getEmail();
            String tempUsername = email.split("@")[0];
            tvUserName.setText(tempUsername);
        }else{
            tvUserName.setText(user.getUserName());
        }
        tvUserTeam.setText(user.getTeamName());

        if(!user.getProfileUrl().isEmpty()
        && !user.getProfileUrl().equals("")){
            Glide.with(this)
                    .load(user.getProfileUrl())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(profileImage);
        }

        ImageButton btnNavigateToRanking = findViewById(R.id.btn_ranking);
        btnNavigateToRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });

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
