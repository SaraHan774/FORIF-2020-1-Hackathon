package com.forif.honsullife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.forif.honsullife.R;
import com.forif.honsullife.auth.Authentication;
import com.forif.honsullife.model.Ranking;
import com.forif.honsullife.model.TeamBeer;
import com.forif.honsullife.model.User;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SelectTeamActivity extends AppCompatActivity {

    private static final String TAG = "SelectTeamActivity";
    private String selectedTeam = "";

    private DatabaseReference mRankDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);

        final Authentication authentication = Authentication.getInstance();
        authentication.setActivity(this);

        final User user = authentication.getUserInfo();

        MaterialCardView beerImg = findViewById(R.id.card_beer);
        MaterialCardView wineImg = findViewById(R.id.card_wine);
        MaterialCardView makguliImg = findViewById(R.id.card_makguli);
        MaterialCardView sojuImg = findViewById(R.id.card_soju);
        MaterialCardView whiskeyImg = findViewById(R.id.card_whiskey);
        MaterialCardView cocktailImg = findViewById(R.id.card_cocktail);
        MaterialCardView nonImg = findViewById(R.id.card_non);

        beerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeam = "맥주팀";
                Toast.makeText(SelectTeamActivity.this,
                        selectedTeam + "을 선택하셨습니다!",
                        Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: 카드 클릭");
            }
        });

        wineImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeam = "와인팀";
                Toast.makeText(SelectTeamActivity.this,
                        selectedTeam + "을 선택하셨습니다!",
                        Toast.LENGTH_LONG).show();
            }
        });

        makguliImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeam = "막걸리팀";
                Toast.makeText(SelectTeamActivity.this,
                        selectedTeam + "을 선택하셨습니다!",
                        Toast.LENGTH_LONG).show();
            }
        });
        sojuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeam = "소주팀";
                Toast.makeText(SelectTeamActivity.this,
                        selectedTeam + "을 선택하셨습니다!",
                        Toast.LENGTH_LONG).show();
            }
        });

        whiskeyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeam = "위스키팀";
                Toast.makeText(SelectTeamActivity.this,
                        selectedTeam + "을 선택하셨습니다!",
                        Toast.LENGTH_LONG).show();
            }
        });
        cocktailImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeam = "칵테일팀";
                Toast.makeText(SelectTeamActivity.this,
                        selectedTeam + "을 선택하셨습니다!",
                        Toast.LENGTH_LONG).show();
            }
        });
        nonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeam = "논알콜팀";
                Toast.makeText(SelectTeamActivity.this,
                        selectedTeam + "을 선택하셨습니다!",
                        Toast.LENGTH_LONG).show();
            }
        });

        final Button btnCompleteTeamSelection = findViewById(R.id.btn_select_team_complete);
        btnCompleteTeamSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTeam.equals("")) {
                    Toast.makeText(SelectTeamActivity.this,
                            "팀을 선택해 주세요!",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    authentication.setUserTeam(selectedTeam);
                    Intent intent = new Intent(SelectTeamActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}