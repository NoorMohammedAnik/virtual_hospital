package com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rr.virtual_hospital.R;

public class EachPostQuestion extends RecyclerView.ViewHolder{
    TextView title,timestamp;
    //ImageView clickr;

    public EachPostQuestion(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.each_question_title);
        timestamp= (TextView) itemView.findViewById(R.id.each_question_time);

    }
}
