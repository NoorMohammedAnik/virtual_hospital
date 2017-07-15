package com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters;

import android.widget.LinearLayout;

import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Conversations;
import com.example.rr.virtual_hospital.utils.Utilities;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;


import java.util.Locale;

/**
 * Created by Ramim on 7/3/2017.
 */

public class ReplyFireRecyclerAdapter extends FirebaseRecyclerAdapter<Conversations,EachReply> {
    private Utilities utils;

    public ReplyFireRecyclerAdapter(Class<Conversations> modelClass, int modelLayout, Class<EachReply> viewHolderClass, DatabaseReference ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        utils=new Utilities();
    }

    @Override
    protected void populateViewHolder(EachReply viewHolder, Conversations model, int position) {
        String dateTime= utils.getDateTime(Long.valueOf(model.getConvoTimestamp().toString()),"dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
        if(model.getParty().equals("P")){
            viewHolder.txtName.setText("Patient");
        }else{
            viewHolder.txtName.setText("Doctor");
        }
        viewHolder.txtReply.setText(model.getReply());
        viewHolder.txtTimeDate.setText(dateTime);

    }
}
