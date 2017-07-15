package com.example.rr.virtual_hospital.nav_drawer.askanexpert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.Main_Nav;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Conversations;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.joda.time.DateTime;

public class PostQuestion extends AppCompatActivity {

    private DatabaseReference postRootDBRef,convoRootDB,profileRootDB, patientsRootDB,unAnsweredPostsRoodDB;
    private SharedPreferences postQuestionSP;
    private Intent galleryIntent;
    private Uri selectedPic;
    private ImageView picPreview;
    private ProgressDialog pd;
    private static final int GALLERY_INTENT_REQUEST_CODE=5;
    private DatabaseReference postDB,conversationDB;
    private EditText postTitle,postDescription;
    private Button addPicture,addPost;
    private DateTime dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);

        pd=new ProgressDialog(this);
        pd.setTitle("Uploading");
        pd.setCancelable(true);

        postRootDBRef= FirebaseDatabase.getInstance().getReference().child("Posts");
        convoRootDB=FirebaseDatabase.getInstance().getReference().child("Conversations");
        patientsRootDB =FirebaseDatabase.getInstance().getReference().child("Patients");
        unAnsweredPostsRoodDB=FirebaseDatabase.getInstance().getReference().child("UnansweredPosts");
        postQuestionSP = getSharedPreferences("com.example.rr.virtual_hospital.UserPrefs",MODE_PRIVATE);

        postDB=postRootDBRef.child(postQuestionSP.getString("userID","none"));
        conversationDB=convoRootDB.child(postQuestionSP.getString("userID","none"));

        postTitle= (EditText) findViewById(R.id.eaid_post_title);
        postDescription= (EditText) findViewById(R.id.eaid_post_description);

        picPreview= (ImageView) findViewById(R.id.eaid_pic_preview);

        addPicture=(Button) findViewById(R.id.eaid_post_add_picture);
        addPost=(Button) findViewById(R.id.eaid_post);

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,GALLERY_INTENT_REQUEST_CODE);
            }
        });

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                String title,description;
                //initing POST
                final Posts post=new Posts();
                int tobePostID= postQuestionSP.getInt("postID",0);
                int tobeConvoID=postQuestionSP.getInt("convoID",0);
                title=postTitle.getText().toString().trim();
                description=postDescription.getText().toString().trim();

                post.setUserID(postQuestionSP.getString("userID","none"));
                post.setPostID(String.valueOf(tobePostID++));
                post.setQuesTitle(title);
                post.setQuesDescription(description);
                post.setQuesPicture("");
                post.setPostTimestamp(String.valueOf(System.currentTimeMillis()));
                post.setAnswered("");
                post.setDocID("");
                post.setConvoID(String.valueOf(tobeConvoID++));
                post.setPoints("");

                //initing Picture if present
                if(picPreview.getDrawable()!=null){
                    final String uniqueName;
                    uniqueName= "post"+post.getPostID()+"Pic"+String.valueOf(System.currentTimeMillis());                 //post.setQuesPicture(uniqueName);
                    StorageReference picstorage= FirebaseStorage.getInstance().getReference();

                    final int finalTobePostID = tobePostID;
                    final int finalTobeConvoID = tobeConvoID;

                    picstorage.child(post.getUserID()).child(post.getPostID()).child(uniqueName).putFile(selectedPic).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            post.setQuesPicture(taskSnapshot.getDownloadUrl().toString());

                            postDB.child(post.getPostID()).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(PostQuestion.this, "Question Posted", Toast.LENGTH_SHORT).show();
                                    unAnsweredPostsRoodDB.push().setValue(post);
                                    //don't push convo
                                    // conversationDB.child(convo.getConvoID()).push().setValue(convo);
                                    SharedPreferences.Editor editor= postQuestionSP.edit();
                                    editor.putInt("postID", finalTobePostID);
                                    editor.putInt("convoID", finalTobeConvoID);
                                    editor.apply();
                                    pd.dismiss();
                                    Intent i=new Intent(PostQuestion.this, Main_Nav.class);
                                    i.putExtra("openFrag",R.id.nav_aae);
                                    startActivity(i);                    }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PostQuestion.this, "Failed due to network error", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostQuestion.this, "Failed to Upload Picture", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    });
                }else{
                    final int finalTobePostID = tobePostID;
                    final int finalTobeConvoID = tobeConvoID;
                    postDB.child(post.getPostID()).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(PostQuestion.this, "Question Posted", Toast.LENGTH_SHORT).show();
                            unAnsweredPostsRoodDB.push().setValue(post);
                            //don't push convo
                            // conversationDB.child(convo.getConvoID()).push().setValue(convo);
                            SharedPreferences.Editor editor= postQuestionSP.edit();
                            editor.putInt("postID", finalTobePostID);
                            editor.putInt("convoID", finalTobeConvoID);
                            editor.apply();
                            pd.dismiss();
                            Intent i=new Intent(PostQuestion.this, Main_Nav.class);
                            i.putExtra("openFrag",R.id.nav_aae);
                            startActivity(i);                    }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostQuestion.this, "Failed due to network error", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    });
                }

                }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==GALLERY_INTENT_REQUEST_CODE){
            selectedPic=data.getData();
            Glide.with(this).load(selectedPic).into(picPreview);
        }
    }
}
