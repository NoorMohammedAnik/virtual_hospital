package com.example.rr.virtual_hospital.nav_drawer.askanexpert;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.BaseFragment;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters.EachPostQuestion;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters.UnansweredPostFireRecycler;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocMyPost extends BaseFragment {
    private RecyclerView uapRecycler;
    private UnansweredPostFireRecycler uapFireRecyclerAdapter;
    private String docInfoUpdateURL;
    private ImageButton btnMyPost;
    private DatabaseReference docMyPostDB;

    public DocMyPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_my_post, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        docInfoUpdateURL=getContext().getResources().getString(R.string.serverIp)+"docInfo.php";
        docMyPostDB= postRootDBRef.child(baseSharedPref.getString("userID","none"));
        uapRecycler= (RecyclerView) view.findViewById(R.id.aae_recycler_docmyquestion_list);
        uapFireRecyclerAdapter=new UnansweredPostFireRecycler(getContext(), Posts.class,R.layout.each_question_post, EachPostQuestion.class,docMyPostDB);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(getContext());
        uapRecycler.setAdapter(uapFireRecyclerAdapter);
        uapRecycler.setLayoutManager(llm);
        uapRecycler.setItemAnimator(new DefaultItemAnimator());

    }
}
