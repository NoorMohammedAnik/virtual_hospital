package com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.rr.virtual_hospital.nav_drawer.askanexpert.PostDetails;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;
import com.example.rr.virtual_hospital.utils.Utilities;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

/**
 * Created by Ramim on 7/3/2017.
 */

public class UnansweredPostFireRecycler extends FirebaseRecyclerAdapter<Posts,EachPostQuestion> {
   private Context context;
    private Utilities utils;

    public UnansweredPostFireRecycler(Context context, Class<Posts> modelClass, int modelLayout, Class<EachPostQuestion> viewHolderClass, DatabaseReference ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context=context;
        utils=new Utilities();
    }

    @Override
    protected void populateViewHolder(EachPostQuestion viewHolder, final Posts model, final int position) {
        final String timedate= utils.getDateTime(Long.valueOf(model.getPostTimestamp()),"dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
        viewHolder.title.setText(model.getQuesTitle());
        viewHolder.timestamp.setText(timedate);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Posts toSendPost=model;
                //toSendPost.setPostTimestamp(timedate);
                String postKey= getRef(position).getKey();
                Intent intent=new Intent(context, PostDetails.class);
                intent.putExtra("postToDisplay",toSendPost);
                intent.putExtra("postKey",postKey);

                context.startActivity(intent);
            }
        });

    }
}
