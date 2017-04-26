package com.example.ahmad.jambprepguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class LeaderboardActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Leaderboard");
    }
}
