package com.example.rr.virtual_hospital.info;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rr.virtual_hospital.Manifest;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.ambulance.AmbulanceActivity;
import com.example.rr.virtual_hospital.diagonostic.DiagonosticActivity;
import com.example.rr.virtual_hospital.doctors.DoctorsActivity;
import com.example.rr.virtual_hospital.firstaid.FirstAiActivity;
import com.example.rr.virtual_hospital.hospital.HospitalListActivity;

public class InfoActivity extends AppCompatActivity {
    Button amb, dia,doc, first,call,hos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        amb=(Button)findViewById(R.id.buttonamb);
        dia=(Button)findViewById(R.id.buttondia);
        doc=(Button)findViewById(R.id.buttondoc);
        first=(Button)findViewById(R.id.buttofirst);
        call=(Button)findViewById(R.id.buttoncall);
        hos=(Button)findViewById(R.id.buttonhos);

        amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InfoActivity.this, AmbulanceActivity.class);
                startActivity(intent);
                Toast.makeText(InfoActivity.this, "Find Your Ambulance From Here", Toast.LENGTH_SHORT).show();
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent in= new Intent(InfoActivity.this, FirstAiActivity.class);
                startActivity(in);
                Toast.makeText(InfoActivity.this, "Lets Find What To Do From Here", Toast.LENGTH_SHORT).show();

            }
        });
        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent= new Intent(InfoActivity.this, DiagonosticActivity.class);
                        startActivity(intent);
                Toast.makeText(InfoActivity.this, "Find Your Diagnostics From Here", Toast.LENGTH_SHORT).show();

            }
        });
        hos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InfoActivity.this, HospitalListActivity.class);
                startActivity(intent);
                Toast.makeText(InfoActivity.this, "Find Your Desire Hospital From Here", Toast.LENGTH_SHORT).show();

            }
        });
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InfoActivity.this, DoctorsActivity.class);
                startActivity(intent);
                Toast.makeText(InfoActivity.this, "Find Your Desire Doctor From Here", Toast.LENGTH_SHORT).show();

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile="16263";

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + mobile));
                Log.i("test", mobile);
                if (ActivityCompat.checkSelfPermission(InfoActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });
    }
}
