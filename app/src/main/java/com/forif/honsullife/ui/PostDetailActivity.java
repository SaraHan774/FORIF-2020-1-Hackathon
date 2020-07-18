package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.forif.honsullife.R;

public class PostDetailActivity extends AppCompatActivity {

    private TextView tvPostTitle;
    private TextView tvPostUsername;
    private TextView tvPostDate;
    private ImageView imgPostImage;

    /*포스트 디테일 보여주기 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);


        tvPostTitle = findViewById(R.id.label_detail_title);
        tvPostUsername = findViewById(R.id.post_detail_username);
        tvPostDate = findViewById(R.id.post_detail_date);
        imgPostImage = findViewById(R.id.post_detail_image);
    }

    /* 포스트 정보를 메인 화면의 리스트 어댑터에서 넘겨받아 디스플레이한다. */
    private void setPostContent(
            String title,
            String username,
            String date,
            String imageUrl
    ){
        tvPostTitle.setText(title);
        tvPostUsername.setText(username);
        tvPostDate.setText(date);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imgPostImage);
    }
}