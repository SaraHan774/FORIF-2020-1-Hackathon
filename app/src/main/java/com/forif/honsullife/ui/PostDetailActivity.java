package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.forif.honsullife.R;
import com.forif.honsullife.model.Post;

public class PostDetailActivity extends AppCompatActivity {

    private final static String CURRENT_POST = "curpost";

    private TextView tvPostTitle;
    private TextView tvPostUsername;
    private TextView tvPostDate;
    private TextView tvPostContent;
    private ImageView imgPostImage;
    private TextView tvPostTeamName;

    /*포스트 디테일 보여주기 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        tvPostTitle = findViewById(R.id.label_detail_title);
        tvPostUsername = findViewById(R.id.post_detail_username);
        tvPostDate = findViewById(R.id.post_detail_date);
        imgPostImage = findViewById(R.id.post_detail_image);
        tvPostContent = findViewById(R.id.post_detail_content);
        tvPostTeamName = findViewById(R.id.post_detail_team);


        Intent intent = getIntent();
        Post curPost = intent.getParcelableExtra(CURRENT_POST);
        setPostContent(curPost.getPostName(),
                curPost.getUserName(),
                curPost.getTeamName(),
                curPost.getCreatedAt(),
                curPost.getPhotoUrl(),
                curPost.getPostContent()
        );
    }

    /* 포스트 정보를 메인 화면의 리스트 어댑터에서 넘겨받아 디스플레이한다. */
    private void setPostContent(
            String title,
            String username,
            String teamName,
            String date,
            String imageUrl,
            String userContent
    ) {

        tvPostTitle.setText(title);
        tvPostUsername.setText(username);
        tvPostDate.setText(date);
        tvPostContent.setText(userContent);
        tvPostTeamName.setText(teamName);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imgPostImage);
    }
}