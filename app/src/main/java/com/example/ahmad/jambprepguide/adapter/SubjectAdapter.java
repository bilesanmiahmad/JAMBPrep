package com.example.ahmad.jambprepguide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmad.jambprepguide.R;
import com.example.ahmad.jambprepguide.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 3/29/17.
 */

public class SubjectAdapter extends ArrayAdapter<Subject> {

    ArrayList<Subject> subjects = new ArrayList<Subject>();

    public SubjectAdapter(Context context, int resource) {

        super(context, resource);
    }

    static class ViewHolder{
        private ImageView bullet;
        private TextView subjetTitle;
        private CheckBox checkBox;

    }

    public SubjectAdapter(Context context, int resource, int textViewResourceId, List<Subject> objects) {
        super(context, resource, textViewResourceId, objects);
        this.subjects.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Subject subject = subjects.get(position);
        final int itemPosition = position;

        ViewHolder holder;
        if (convertView == null){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            convertView = mInflater.inflate(R.layout.item_layout, null);

            holder = new ViewHolder();

            holder.bullet = (ImageView) convertView.findViewById(R.id.bullet);
            holder.subjetTitle = (TextView) convertView.findViewById(R.id.txt);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.imgState);

            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(getContext(), "Check changed to " + isChecked, Toast.LENGTH_LONG).show();

                    (subjects.get(itemPosition)).setSelected(isChecked);
                }
            });

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bullet.setImageResource(R.drawable.circle);
        holder.subjetTitle.setText(subject.getTitle());
//        holder.checkBox.setChecked(subject.isSelected());

        return convertView;
    }
}
