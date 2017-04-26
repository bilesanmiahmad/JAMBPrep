package com.example.ahmad.jambprepguide.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmad.jambprepguide.R;
import com.example.ahmad.jambprepguide.model.Institution;

import java.util.List;

/**
 * Created by ahmad on 4/1/17.
 */

public class InstitutionAdapter extends ArrayAdapter<Institution> {
    ViewHolder holder;

    public InstitutionAdapter(Context context, int resource, int textViewResourceId, List<Institution> objects) {
        super(context, resource, textViewResourceId, objects);
    }



    static class ViewHolder{
        private ImageView bullet;
        private TextView instTitle;
        private CheckBox checkBox;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            convertView = mInflater.inflate(R.layout.item_layout, null);
            holder = new ViewHolder();
            holder.bullet = (ImageView) convertView.findViewById(R.id.bullet);
            holder.instTitle = (TextView) convertView.findViewById(R.id.txt);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.imgState);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Institution institution = getItem(position);
        holder.bullet.setImageResource(R.drawable.circle);
        holder.checkBox.setChecked(false);
        holder.instTitle.setText(institution.getTitle());
        return convertView;
    }
}
