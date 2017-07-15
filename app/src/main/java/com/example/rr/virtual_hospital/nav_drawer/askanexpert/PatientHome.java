package com.example.rr.virtual_hospital.nav_drawer.askanexpert;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.BaseFragment;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters.MyPostRecycler;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.services.ConvoEventListener;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.services.PostEventListener;
import com.example.rr.virtual_hospital.utils.Utilities;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PatientHome extends BaseFragment {

    private MyPostRecycler myPostRecycler;
    private List<Posts> postList;
    private RecyclerView postRecycler;
    private Utilities utils;
    private ImageButton addPost;
    private Intent serviceIntent,convoServiceIntent;
    private List<String> myPostID;
    public PatientHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(baseSharedPref.getBoolean("firstTime",true)){
            SharedPreferences.Editor editor= baseSharedPref.edit();
            editor.putInt("postID",0);
            editor.putInt("convoID",0);
            editor.putBoolean("firstTime",false);
            editor.apply();
        }
        serviceIntent= new Intent(getContext(), PostEventListener.class);
        postRecycler= (RecyclerView) view.findViewById(R.id.aae_recycler_myquestion_list);
        addPost= (ImageButton) view.findViewById(R.id.aae_post_question);
        postList=new ArrayList<>();
        myPostRecycler=new MyPostRecycler(getContext(),postList);
        RecyclerView.LayoutManager llm=new LinearLayoutManager(getContext());
        postRecycler.setLayoutManager(llm);
        postRecycler.setAdapter(myPostRecycler);
        postRecycler.setItemAnimator(new DefaultItemAnimator());
        myPostID=new ArrayList<>();
        fetchData();

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),PostQuestion.class));
            }
        });
    }

    private void fetchData() {
        baseProgressBar.setMessage("Loading Your Questions");
        baseProgressBar.show();
        utils=new Utilities();
        Log.d("loggeduserID",baseSharedPref.getString("userID","none"));
        final DatabaseReference myPosts= postRootDBRef.child(baseSharedPref.getString("userID","none"));
        myPosts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                for(DataSnapshot singlePost: dataSnapshot.getChildren()){
                    Posts eachPost= singlePost.getValue(Posts.class);
                    String dateTime= utils.getDateTime(Long.valueOf(eachPost.getPostTimestamp()),"dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
                    eachPost.setPostTimestamp(dateTime);
                    postList.add(eachPost);
                    myPostID.add(eachPost.getPostID());
                }
                convoServiceIntent=new Intent(getContext(), ConvoEventListener.class);
                    convoServiceIntent.putExtra("postIDs", (Serializable) myPostID);

                if (postList.isEmpty()){
                    postRecycler.setBackgroundResource(R.drawable.speech_bubble);
                    baseProgressBar.dismiss();
                }else{
                    myPostRecycler.notifyDataSetChanged();
                    baseProgressBar.dismiss();
                }


            }catch (NullPointerException e){
                    Log.d("NPexception","No Post Yet");
                    postRecycler.setBackgroundResource(R.drawable.question_placeholder);
                    baseProgressBar.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getContext().startService(serviceIntent);
//        getContext().startService(convoServiceIntent);
    }
}
