package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.RenderNode;
import android.os.Bundle;

import com.forif.honsullife.R;
import com.forif.honsullife.model.Post;
import com.forif.honsullife.model.Ranking;
import com.forif.honsullife.model.RankingAdapter;
import com.forif.honsullife.model.RankingRvAdapter;
import com.forif.honsullife.model.ViewPagerRanking;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

    /*Firebase 에서 랭킹 정보 가져와서 랭킹 띄워주기.
    * 1, 2, 3등은 ViewPager 로 상단에, 나머지 팀은 RecyclerView 혹은 ListView 로 하단에 띄우기 */

    private ViewPager viewPager;
    private Ranking mRank;
    private List<ViewPagerRanking> highest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

//        viewPager = findViewById(R.id.rank_view_pager);
//        highest = new ArrayList<>();
//
//
//
//        highest.add(new ViewPagerRanking(R.drawable.ic_beer,0,"맥주팀"));
//        highest.add(new ViewPagerRanking(R.drawable.ic_wine,0,"와인팀"));
//        highest.add(new ViewPagerRanking(R.drawable.ic_makguli,0,"막걸팀"));
//        highest.add(new ViewPagerRanking(R.drawable.ic_soju,0,"소주팀"));
//        highest.add(new ViewPagerRanking(R.drawable.ic_whiskey,0,"위스키팀"));
//        highest.add(new ViewPagerRanking(R.drawable.ic_drink,0,"칵테일팀"));
//        highest.add(new ViewPagerRanking(R.drawable.ic_non_alcoholic,0,"논알콜팀"));
//
//        RankingAdapter rankingAdapter = new RankingAdapter(RankingActivity.this,highest);
//        viewPager.setAdapter(rankingAdapter);
//        viewPager.setPadding(130,0,130,0);

        ArrayList<String> teamNames = new ArrayList<>();
        if(teamNames.isEmpty()){
            teamNames.add("맥주팀");
            teamNames.add("와인팀");
            teamNames.add("막걸리팀");
            teamNames.add("소주팀");
            teamNames.add("위스키팀");
            teamNames.add("칵테일팀");
            teamNames.add("논알콜팀");
        }

        RecyclerView recyclerView = findViewById(R.id.rv_ranking);
        RankingRvAdapter rankingRvAdapter = new RankingRvAdapter(this, teamNames);
        recyclerView.setAdapter(rankingRvAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}