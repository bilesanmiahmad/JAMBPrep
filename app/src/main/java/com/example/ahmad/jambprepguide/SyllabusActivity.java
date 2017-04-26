package com.example.ahmad.jambprepguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.ahmad.jambprepguide.adapter.RecyclerAdapter;
import com.example.ahmad.jambprepguide.model.Subject;

public class SyllabusActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Syllabus");

        //setupRecyclerView();
    }

//    private void setupRecyclerView(){
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_subjects);
//        RecyclerAdapter adapter = new RecyclerAdapter(this, Subject.getData());
//        recyclerView.setAdapter(adapter);
//
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
//        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(mLinearLayoutManager);
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//    }
}
