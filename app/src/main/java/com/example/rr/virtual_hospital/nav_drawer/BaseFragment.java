package com.example.rr.virtual_hospital.nav_drawer;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rr.virtual_hospital.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;


public class BaseFragment extends Fragment {

    protected SharedPreferences baseSharedPref;
    protected ProgressDialog baseProgressBar;
    protected DatabaseReference postRootDBRef,convoRootDB,profileRootDB, patientsRootDB,unAnsweredPostsRoodDB;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseSharedPref= getContext().getSharedPreferences("com.example.rr.virtual_hospital.UserPrefs",MODE_PRIVATE);
        baseProgressBar= new ProgressDialog(getContext());
        baseProgressBar.setTitle("Loading....");


        //database references
        postRootDBRef= FirebaseDatabase.getInstance().getReference().child("Posts");
        convoRootDB=FirebaseDatabase.getInstance().getReference().child("Conversations");
        patientsRootDB =FirebaseDatabase.getInstance().getReference().child("Patients");
        unAnsweredPostsRoodDB=FirebaseDatabase.getInstance().getReference().child("UnansweredPosts");
    }
}
