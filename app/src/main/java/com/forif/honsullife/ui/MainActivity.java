package com.forif.honsullife.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.forif.honsullife.R;
import com.forif.honsullife.auth.Authentication;
import com.forif.honsullife.model.OurRvAdapter;
import com.forif.honsullife.model.User;
import com.forif.honsullife.model.OtherRvAdapter;
import com.forif.honsullife.model.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OtherRvAdapter.OnItemClickListener {


    private static final String TAG = "MainActivity";
    /*1. 사용자 프로필 상단에 띄우기
    * 2. 우리팀 포스트 가로로 띄우기
    * 3. 다른 팀 포스트 작성 시간 순으로 세로로 띄우기
    * 4. AppBar 에다가 로그아웃, 랭킹보기 메뉴 넣기
    * */

    private final static int POST = 1000;
    public final static String CURRENT_POST = "curpost";

    private FloatingActionButton fabNavigateToPublish;
    private RecyclerView othersRecyclerView;


    //Storage&DB
    private FirebaseStorage mStorage;
    private DatabaseReference mDBRef;
    private ValueEventListener mValueListener;

    //RV
    private OtherRvAdapter othersAdapter;
    private List<Post> mPosts;

    private String teamName;
    private ArrayList<Post> ourTeamPostList = new ArrayList<>();
    private OurRvAdapter ourRvAdapter;
    private RecyclerView ourRecyclerView;

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
        teamName = user.getTeamName();
        tvUserTeam.setText(teamName);

        if(user.getProfileUrl() != null
        && !user.getProfileUrl().equals("")){
            Glide.with(this)
                    .load(user.getProfileUrl())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(profileImage);
        }

        ImageButton btnNavigateToRanking = findViewById(R.id.btn_ranking);
        btnNavigateToRanking.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RankingActivity.class);
            startActivity(intent);
        });

        fabNavigateToPublish = findViewById(R.id.fab_navigate_to_publish_activity);
        fabNavigateToPublish.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PublishPostActivity.class);
            startActivityForResult(intent,POST);

        });

        mStorage = FirebaseStorage.getInstance();
        mDBRef = FirebaseDatabase.getInstance().getReference("uploads");

        mPosts = new ArrayList<>();

        othersRecyclerView = findViewById(R.id.rv_other_team_post_list);
        othersRecyclerView.setHasFixedSize(true);
        othersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        othersAdapter = new OtherRvAdapter(this,mPosts);
        othersAdapter.setOnItemClickListener(this);
        othersRecyclerView.setAdapter(othersAdapter);

        /*우리팀 리스트 */
        ourRecyclerView = findViewById(R.id.rv_my_team_post_list);
        filterOurTeamPosts((ArrayList<Post>) mPosts);


        mValueListener = mDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts.clear();
                for(DataSnapshot postSnapshots : dataSnapshot.getChildren()){
                    Post curUpload = postSnapshots.getValue(Post.class);
                    mPosts.add(curUpload);
                    filterOurTeamPosts((ArrayList<Post>) mPosts);
                }

                othersAdapter.notifyDataSetChanged();
                ourRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void filterOurTeamPosts(ArrayList<Post> postArrayList){
        ArrayList<Post> ourTeamPostList = new ArrayList<>();

        for (int i = 0; i < postArrayList.size(); i++) {
            Post post = postArrayList.get(i);
            if(post.getTeamName() == null){
                continue;
            }
            Log.d(TAG, "filterOurTeamPosts: " + post.getTeamName());
            Log.d(TAG, "filterOurTeamPosts: " + teamName);
            if(post.getTeamName().equals(teamName)){
                ourTeamPostList.add(post);
                Log.d(TAG, "filterOurTeamPosts: 같은 것 추가");
            }
        }

        ourRvAdapter = new OurRvAdapter(this, ourTeamPostList);
        ourRecyclerView.setAdapter(ourRvAdapter);
        ourRecyclerView.setHasFixedSize(true);
        ourRecyclerView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false)
        );
    }

    @Override
    public void OnItemClickListener(int position) {
        Intent intent = new Intent(getApplicationContext(),PostDetailActivity.class);
        Post curPost = mPosts.get(position);
        intent.putExtra(CURRENT_POST,curPost);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == POST && resultCode == RESULT_OK){
            Toast.makeText(this, "Uploaded!", Toast.LENGTH_SHORT).show();
        }
    }
}
