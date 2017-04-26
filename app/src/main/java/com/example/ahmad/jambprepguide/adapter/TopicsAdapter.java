package com.example.ahmad.jambprepguide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmad.jambprepguide.R;
import com.example.ahmad.jambprepguide.model.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 4/1/17.
 */

public class TopicsAdapter extends ArrayAdapter {
    ViewHolder holder;
    ArrayList<Topic> topics;

    public TopicsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.topics = new ArrayList<Topic>();
        this.topics.addAll(objects);
    }

    public TopicsAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    static class ViewHolder{
        private ImageView bullet;
        private TextView title;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.bullet = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.title = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Topic topic = topics.get(position);
        holder.bullet.setImageResource(R.drawable.circle);
        holder.title.setText(topic.getTitle());
        return convertView;
    }
}
