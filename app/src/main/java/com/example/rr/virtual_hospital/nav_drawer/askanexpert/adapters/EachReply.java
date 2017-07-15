package com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rr.virtual_hospital.R;


/**
 * Created by Ramim on 7/3/2017.
 */

public class EachReply extends RecyclerView.ViewHolder {
    TextView txtName,txtReply,txtTimeDate;


    public EachReply(View itemView) {
        super(itemView);
        txtName= (TextView) itemView.findViewById(R.id.reply_uname);
        txtReply= (TextView) itemView.findViewById(R.id.reply_text);
        txtTimeDate= (TextView) itemView.findViewById(R.id.reply_timedate);
    }
}
