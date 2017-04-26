package com.example.ahmad.jambprepguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmad.jambprepguide.R;
import com.example.ahmad.jambprepguide.model.Subject;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ahmad on 3/10/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private List<Subject> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context context, List<Subject> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG,"onCreateViewHolder" + position);
        Subject currentObj = mData.get(position);
        holder.setData(currentObj, position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView bullet;
        TextView title;
        Subject current;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txtSubject);
            bullet = (ImageView) itemView.findViewById(R.id.img_row);

        }

        public void setData(Subject currentObj, int position) {
            this.title.setText(currentObj.getTitle());
            this.position= position;
            this.current = currentObj;
        }
    }
}
