package com.example.rr.virtual_hospital.nav_drawer.askanexpert;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.BaseFragment;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters.EachReply;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.adapters.ReplyFireRecyclerAdapter;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Conversations;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Doctors;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;
import com.example.rr.virtual_hospital.utils.Utilities;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class PostDetails extends AppCompatActivity {

    private DatabaseReference postRootDBRef,convoRootDB,docRootDB, patientsRootDB,unAnsweredPostsRoodDB;
    private StorageReference picStorageRoot,thisPost;

    private TextView txtDocTitle,txtDescription, txtGender,txtAge,txtTimeDate;
    private EditText edtReply;
    private Button btnSubmit,btnPoints;
    private DatabaseReference postRef,convoRef,profileRef;
    private ReplyFireRecyclerAdapter mAdapter;
    private RecyclerView replyRecycler;
    private ImageView postDetailPicture;

    private String profDataURL;
    private SharedPreferences postDetailsSP;
    private Utilities utils;
    private Posts receievedPost;
    private LinearLayout ll1;
    //doc profile info
    private TextView txtDocName,txtTitle,txtInstitution,txtSpeciality,txtYearofStudy,txtRegistrationNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        txtTitle=(TextView)findViewById(R.id.post_detail_title);
        txtDescription=(TextView)findViewById(R.id.post_detail_description);
        txtGender=(TextView)findViewById(R.id.post_detail_gender);
        txtAge=(TextView)findViewById(R.id.post_detail_poster_age);
        txtTimeDate= (TextView) findViewById(R.id.post_detail_timedate);
        postDetailPicture= (ImageView) findViewById(R.id.post_detail_picture);

        txtDocName= (TextView) findViewById(R.id.reply_doc_name);
        txtDocTitle= (TextView) findViewById(R.id.reply_doc_title);
        txtInstitution= (TextView) findViewById(R.id.reply_doc_institution);
        txtSpeciality= (TextView) findViewById(R.id.reply_doc_speciality);
       // txtPoints= (TextView) findViewById(R.id.reply_doc_points);
        txtYearofStudy= (TextView) findViewById(R.id.reply_doc_year);
        txtRegistrationNumber= (TextView) findViewById(R.id.reply_doc_reg_num);
        ll1= (LinearLayout) findViewById(R.id.post_detail_doc_intro);
        ll1.setVisibility(View.INVISIBLE);
        edtReply= (EditText) findViewById(R.id.post_detail_reply);
        btnSubmit= (Button) findViewById(R.id.post_detail_submit);

        postDetailsSP = getSharedPreferences("com.example.rr.virtual_hospital.UserPrefs",MODE_PRIVATE);
        utils=new Utilities();
        //receiving items from other activites
        receievedPost= getIntent().getExtras().getParcelable("postToDisplay");
        final String postKey= getIntent().getExtras().getString("postKey");
        extractInfoForLocalUse(receievedPost);


        //init roots
        postRootDBRef= FirebaseDatabase.getInstance().getReference().child("Posts");
        convoRootDB=FirebaseDatabase.getInstance().getReference().child("Conversations");
        patientsRootDB =FirebaseDatabase.getInstance().getReference().child("Patients");
        unAnsweredPostsRoodDB=FirebaseDatabase.getInstance().getReference().child("UnansweredPosts");
        picStorageRoot= FirebaseStorage.getInstance().getReference();
        docRootDB=FirebaseDatabase.getInstance().getReference().child("Doctors");

        Toast.makeText(this,"PostID-"+ receievedPost.getPostID(), Toast.LENGTH_SHORT).show();
        postRef=postRootDBRef.child(postDetailsSP.getString("userID",null)).child(receievedPost.getPostID());
        profileRef= patientsRootDB.child(postDetailsSP.getString("userID",null));
        convoRef= convoRootDB.child(receievedPost.getUserID()).child(receievedPost.getPostID());
        if(!receievedPost.getQuesPicture().isEmpty()){
            thisPost= picStorageRoot.child(postDetailsSP.getString("userID",null)).child(receievedPost.getPostID()).child(receievedPost.getQuesPicture());
        }

        //end

        //fireREC setup
        replyRecycler=(RecyclerView) findViewById(R.id.post_detail_all_replies);
        mAdapter=new ReplyFireRecyclerAdapter(Conversations.class,R.layout.each_reply, EachReply.class,convoRef);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(this);
        replyRecycler.setLayoutManager(llm);
        replyRecycler.setAdapter(mAdapter);
        replyRecycler.setItemAnimator(new DefaultItemAnimator());


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reply= edtReply.getText().toString();
                if(!reply.isEmpty() && reply!=null){
                    if(postDetailsSP.getString("party","").equals("P")){
                        postasConvo(reply,receievedPost.getPostID(),receievedPost.getConvoID());
                    }else {
                        postasDoc(reply,receievedPost.getConvoID(),receievedPost.getUserID(),postKey,receievedPost.getPostID());
                    }
                }else{
                    Toast.makeText(PostDetails.this, "Enter your reply", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void extractInfoForLocalUse(Posts receievedPost) {
        txtTitle.setText(receievedPost.getQuesTitle());
        txtDescription.setText(receievedPost.getQuesDescription());
        txtAge.setText(postDetailsSP.getString("user_age",""));
        txtGender.setText(postDetailsSP.getString("user_gender",""));
        txtTimeDate.setText(receievedPost.getPostTimestamp());
        if(!receievedPost.getQuesPicture().isEmpty()){
            Glide.with(this).load(receievedPost.getQuesPicture()).into(postDetailPicture);
        }
    }

    private void postasDoc(String reply, String convoID, final String postUserID, final String postKey, final String postID) {
        DatabaseReference replyRef=convoRootDB.child(postUserID).child(postID);

        Conversations convos=new Conversations();
        convos.setConvoID(convoID);
        convos.setConvoTimestamp(String.valueOf(System.currentTimeMillis()));
        convos.setParty(postDetailsSP.getString("party",null));
        convos.setReply(reply);
        convos.setUserID(postDetailsSP.getString("userID",""));
        replyRef.push().setValue(convos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                unAnsweredPostsRoodDB.child(postKey).removeValue();
                receievedPost.setAnswered("1");
                receievedPost.setDocID(postDetailsSP.getString("userID",null));

                postRootDBRef.child(postDetailsSP.getString("userID",null)).child(receievedPost.getPostID()).setValue(receievedPost);
                postRootDBRef.child(postUserID).child(postID).setValue(receievedPost);

                Toast.makeText(PostDetails.this, "Your Reply is posted,Thank you", Toast.LENGTH_SHORT).show();
                convoRootDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mAdapter.notifyDataSetChanged();
                        edtReply.setText("");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PostDetails.this, "Failed to upload reply", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postasConvo(String reply, String postID,String convoID) {
        /* things need to be sent:
        convoID
        convoTimestamp
        party
        reply
        userID
         */
        Conversations convos=new Conversations();
        convos.setConvoID(convoID);
        convos.setConvoTimestamp(String.valueOf(System.currentTimeMillis()));
        convos.setParty(postDetailsSP.getString("party",null));
        convos.setReply(reply);
        convos.setUserID(postDetailsSP.getString("userID",null));

        convoRef.push().setValue(convos);

        convoRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mAdapter.notifyDataSetChanged();
                edtReply.setText("");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
       convoRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()){
                  ll1.setVisibility(View.VISIBLE);
               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        DatabaseReference thisDoc= docRootDB.child(receievedPost.getDocID());
        try{
            thisDoc.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("postRef",dataSnapshot.getValue().toString());
                    Doctors responsingDoc=dataSnapshot.getValue(Doctors.class);
                    txtInstitution.setText(responsingDoc.getInstitute());
                    txtRegistrationNumber.setText(responsingDoc.getTitle());
                    txtDocTitle.setText(responsingDoc.getTitle());
                    txtDocName.setText(responsingDoc.getName());
//                    txtPoints.setText("have total "+responsingDoc.getTotalPoints()+" points");
                    txtSpeciality.setText(responsingDoc.getSpeciality());
                    txtYearofStudy.setText(responsingDoc.getYearOfStudy());
                     //fetchProfileData();
                    //  Log.d("postDetailsData","postid-"+post.getPostID()+"gender-"+pt[0].getGender()+"age-"+pt[0].getDateofbirth());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (NullPointerException e){
            Log.d("adminNotice","post not replied");
        }

    }
    private void fetchProfileData() {
        StringRequest profData=new StringRequest(Request.Method.GET, profDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject profData=new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vError",error.toString());
            }
        });
        NetworkSingleton.getInstance().getRequestQueue().add(profData);
    }
}
