package com.forif.honsullife.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.forif.honsullife.R;
import com.forif.honsullife.model.RvAdapter;
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

public class MainActivity extends AppCompatActivity implements RvAdapter.OnItemClickListener {

    /*1. 사용자 프로필 상단에 띄우기
    * 2. 우리팀 포스트 가로로 띄우기
    * 3. 다른 팀 포스트 작성 시간 순으로 세로로 띄우기
    * 4. AppBar 에다가 로그아웃, 랭킹보기 메뉴 넣기
    * */

    private final static int POST = 1000;
    private final static String CURRENT_POST = "curpost";

    private FloatingActionButton fabNavigateToPublish;
    private RecyclerView othersRecyclerView;


    //Storage&DB
    private FirebaseStorage mStorage;
    private DatabaseReference mDBRef;
    private ValueEventListener mValueListener;

    //RV
    private RvAdapter othersAdapter;
    private List<Post> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabNavigateToPublish = findViewById(R.id.fab_navigate_to_publish_activity);
        fabNavigateToPublish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PublishPostActivity.class);
                startActivityForResult(intent,POST);

            }
        });

        mStorage = FirebaseStorage.getInstance();
        mDBRef = FirebaseDatabase.getInstance().getReference("uploads");

        mPosts = new ArrayList<>();

        othersRecyclerView = findViewById(R.id.rv_other_team_post_list);
        othersRecyclerView.setHasFixedSize(true);
        othersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        othersAdapter = new RvAdapter(this,mPosts);
        othersAdapter.setOnItemClickListener(this);
        othersRecyclerView.setAdapter(othersAdapter);

        mValueListener = mDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPosts.clear();
                for(DataSnapshot postSnapshots : dataSnapshot.getChildren()){
                    Post curUpload = postSnapshots.getValue(Post.class);
                    mPosts.add(curUpload);
                }

                othersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
