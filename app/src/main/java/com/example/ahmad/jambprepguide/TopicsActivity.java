package com.example.ahmad.jambprepguide;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmad.jambprepguide.adapter.TopicsAdapter;
import com.example.ahmad.jambprepguide.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {
    ListView topic_list;
    TextView title;
    ArrayList<Topic> topics;
    Context ctx;
    JSONObject object;
    JSONArray array;
    JSONObject topicObject;
    TopicsAdapter ta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        topic_list = (ListView) findViewById(R.id.topic_list);
        title = (TextView) findViewById(R.id.topic_name);
        topics = new ArrayList<Topic>();
        ctx = this;

        try {
            object = new JSONObject(Global.topicData);
            array = object.getJSONArray("data");
            title.setText(Global.topicTitle);
            for(int i=0; i < array.length(); i++){
                topicObject = array.getJSONObject(i);
                String txtTopic = topicObject.getString("topic");
                Topic topic = new Topic(txtTopic);
                Log.e("Topics", txtTopic);
                topics.add(topic);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ta = new TopicsAdapter(ctx, R.layout.list_row, topics);
        topic_list.setAdapter(ta);
        topic_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                Toast.makeText(ctx, choice, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
