package com.example.rr.virtual_hospital.nav_drawer.askanexpert.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.Main_Nav;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.model.Posts;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ConvoEventListener extends IntentService {
    private DatabaseReference myPostRef;
    private SharedPreferences servicePref;

    public ConvoEventListener() {
        super("PostEventListener");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
      //  List<String> postIDs= (List<String>) intent.getSerializableExtra("postIDs");

        servicePref=getSharedPreferences("com.example.rr.virtual_hospital.UserPrefs",MODE_PRIVATE);
        if(servicePref.getString("party","").equals("P")){
            myPostRef = FirebaseDatabase.getInstance().getReference().child("Conversations").child(servicePref.getString("userID",null));
        }else{
            myPostRef = FirebaseDatabase.getInstance().getReference().child("Conversations");
        }

        myPostRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("serviceChildChanged",dataSnapshot.toString()+" and "+s);
                Posts mypost= dataSnapshot.getValue(Posts.class);

                Intent resultIntent = new Intent(getApplicationContext(), Main_Nav.class);
                resultIntent.putExtra("openFrag",R.id.nav_aae);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                        resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder mNotifyBuilder;
                NotificationManager mNotificationManager;
                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // Sets an ID for the notification, so it can be updated
                int notifyID = 9001;
                mNotifyBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Your post is replied")
                        .setContentText(mypost.getQuesTitle())
                        .setSmallIcon(R.mipmap.ic_launcher);
                // Set pending intent
                mNotifyBuilder.setContentIntent(resultPendingIntent);
                // Set Vibrate, Sound and Light
                int defaults = 0;
                //defaults = defaults | Notification.DEFAULT_LIGHTS;
                //defaults = defaults | Notification.DEFAULT_VIBRATE;
                defaults = defaults | Notification.DEFAULT_SOUND;
                mNotifyBuilder.setDefaults(defaults);
                // Set the content for Notification

                // Set autocancel
                mNotifyBuilder.setAutoCancel(true);
                // Post a notification
                mNotificationManager.notify(notifyID, mNotifyBuilder.build());
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


}
