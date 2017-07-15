package com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.PostDetails;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;


import java.util.List;

/**
 * Created by Ramim on 6/6/2017.
 */

public class MyPostRecycler extends RecyclerView.Adapter<EachPostQuestion> {

    Context context;
    List<Posts> postList;

    public MyPostRecycler(Context context, List<Posts> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public EachPostQuestion onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_question_post, parent, false);

        return new EachPostQuestion(itemView);
    }

    @Override
    public void onBindViewHolder(EachPostQuestion holder, final int position) {
        holder.title.setText(postList.get(position).getQuesTitle());
        holder.timestamp.setText(postList.get(position).getPostTimestamp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postID= postList.get(position).getPostID();
                String convoID= postList.get(position).getConvoID();
                String postUserID= postList.get(position).getUserID();
                String postPicName= postList.get(position).getQuesPicture();
                Intent intent=new Intent(context, PostDetails.class);
                intent.putExtra("postID",postID);
                intent.putExtra("convoID",convoID);
                intent.putExtra("postUserID",postUserID);
                intent.putExtra("postPicName",postPicName);

                Posts toSendPost= postList.get(position);
                intent.putExtra("postToDisplay",toSendPost);
                //Toast.makeText(context, postUserID, Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //TODO: Create deleting post part
                return false;
            }
        });

        /*if doctor
        holder.clickr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String postID= postList.get(position).getPostID();
                String convoID= postList.get(position).getConvoID();
                Intent intent=new Intent(context, PostDetails.class);
                intent.putExtra("postID",postID);
                intent.putExtra("convoID",convoID);
                context.startActivity(intent);
            }
        }); */

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}

