package com.example.rr.virtual_hospital.nav_drawer.askanexpert;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.BaseFragment;
import com.example.rr.virtual_hospital.nav_drawer.Main_Nav;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters.EachPostQuestion;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters.UnansweredPostFireRecycler;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Doctors;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.services.UnansweredPostEventListener;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DoctorHome extends BaseFragment {

    private RecyclerView uapRecycler;
    private UnansweredPostFireRecycler uapFireRecyclerAdapter;
    private String docInfoUpdateURL;
    private ImageButton btnMyPost;
    public DoctorHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        docInfoUpdateURL=getContext().getResources().getString(R.string.serverIp)+"docInfo.php";
        if(baseSharedPref.getBoolean("firstTime",true)){
            initializeasDoc();
        }
        uapRecycler= (RecyclerView) view.findViewById(R.id.recycler_unanswered_question_list);
        btnMyPost=(ImageButton) view.findViewById(R.id.aae_doc_mypost);
        uapFireRecyclerAdapter=new UnansweredPostFireRecycler(getContext(), Posts.class,R.layout.each_question_post, EachPostQuestion.class,unAnsweredPostsRoodDB);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(getContext());
        uapRecycler.setAdapter(uapFireRecyclerAdapter);
        uapRecycler.setLayoutManager(llm);
        uapRecycler.setItemAnimator(new DefaultItemAnimator());
        btnMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), Main_Nav.class);
                i.putExtra("openFrag",R.layout.fragment_doc_my_post);
                startActivity(i);
            }
        });
    }

    private void initializeasDoc() {
        baseProgressBar.setMessage("Initializing your profile for the first time...");
        baseProgressBar.show();
        StringRequest docInfoUpdate=new StringRequest(Request.Method.POST, docInfoUpdateURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("serverResponse",response);
                Doctors pseudoDoc= new Doctors();
                try {
                    JSONObject docData=new JSONObject(response);
                    pseudoDoc.setInstitute(docData.getString("doc_institute"));
                    pseudoDoc.setSpeciality(docData.getString("doc_speciality"));
                    pseudoDoc.setTitle(docData.getString("doc_title"));
                    pseudoDoc.setTotalPoints("0");
                    pseudoDoc.setYearOfStudy(docData.getString("doc_yearofstudy"));
                    pseudoDoc.setDocRegistrationID(docData.getString("doc_registration_id"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    baseProgressBar.dismiss();
                    Log.d("jsonException",e.toString());
                }
                pseudoDoc.setDocID(baseSharedPref.getString("userID",null));
                pseudoDoc.setName(baseSharedPref.getString("user_name",null));

                DatabaseReference docDB= FirebaseDatabase.getInstance().getReference("Doctors");
                docDB.child(pseudoDoc.getDocID()).setValue(pseudoDoc);

//        Toast.makeText(getContext(), "as Doc", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor= baseSharedPref.edit();
                editor.putBoolean("firstTime",false);
                editor.apply();
                baseProgressBar.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                baseProgressBar.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("user_id",baseSharedPref.getString("userID",null));
                params.put("task","getDocInfo");
                return params;
            }
        };
        NetworkSingleton.getInstance().getRequestQueue().add(docInfoUpdate);
    }

    @Override
    public void onStart() {
        super.onStart();
        getContext().startService(new Intent(getContext(), UnansweredPostEventListener.class));
    }
}
