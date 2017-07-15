package com.example.rr.virtual_hospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rr.virtual_hospital.emergency_sms.EmergencySms;
import com.example.rr.virtual_hospital.info.InfoActivity;
import com.example.rr.virtual_hospital.map.MapsActivity;
import com.example.rr.virtual_hospital.user_initiation.Login;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnInformation,btnFindNearest,btnSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // bloodBank=(Button)findViewById(R.id.vb);
        //callForHelp=(Button)findViewById(R.id.Call);
       // firstAid=(Button)findViewById(R.id.Aid);
//        hospital=(Button)findViewById(R.id.hospital);
//        diago=(Button)findViewById(R.id.diagonostic);
//        map=(Button)findViewById(R.id.Map);
//        doc=(Button)findViewById(R.id.Doc);
//        ambu=(Button)findViewById(R.id.Amb);


        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnInformation=(Button)findViewById(R.id.btnInformation);
        btnFindNearest=(Button)findViewById(R.id.btnFindNearest);
        btnSMS=(Button)findViewById(R.id.btnSMS);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


  btnInformation.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
      }
    });

        btnFindNearest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent in = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(in);
                Toast.makeText(MainActivity.this, "Find Nearest", Toast.LENGTH_SHORT).show();
            }
        });


        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, EmergencySms.class);
                startActivity(intent);
            }
        });


    }
}
