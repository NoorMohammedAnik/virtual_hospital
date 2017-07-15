package com.example.rr.virtual_hospital.nav_drawer.profile;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rr.virtual_hospital.MainActivity;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.BaseFragment;
import com.example.rr.virtual_hospital.nav_drawer.Main_Nav;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.PostQuestion;
import com.example.rr.virtual_hospital.nav_drawer.prescriptions.model.Prescriptions;
import com.example.rr.virtual_hospital.prescription.UploadPrescription;

import com.example.rr.virtual_hospital.profile.BloodBank;
import com.example.rr.virtual_hospital.profile.adapters.PagerAdapterSmall;
import com.example.rr.virtual_hospital.user_initiation.Login;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import customfonts.customfonts.MyTextView;



public class Profile extends BaseFragment implements View.OnClickListener {

    private String profileDataURL = "";
    private String prescriptionTasksURL= "";

    private MyTextView mobile,name,division,bloodgroup;
    private LinearLayout llname, llcell, lldivision, llblood;

    private Button btnLogout;
    //temps
    private ArrayList<Prescriptions> prescs;
    private Map<String, String> updateData;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileDataURL=getString(R.string.serverIp)+"profile.php";
        name = (MyTextView) view.findViewById(R.id.txt_profile_name);
        bloodgroup = (MyTextView) view.findViewById(R.id.txt_profile_blood);
        division = (MyTextView) view.findViewById(R.id.txt_profile_division);
        mobile = (MyTextView) view.findViewById(R.id.txt_profile_mobile);
//        btnLogout=(Button) view.findViewById(R.id.btn_logout);

        prescriptionTasksURL=getString(R.string.serverIp)+"prescription.php";
        updateData=new HashMap<>();
//        sp=getSharedPreferences("vHospUserData",MODE_PRIVATE);



        llname = (LinearLayout) view.findViewById(R.id.profile_llname);
        llcell = (LinearLayout) view.findViewById(R.id.profile_llcell);
        lldivision = (LinearLayout) view.findViewById(R.id.profile_lldivision);
        llblood = (LinearLayout) view.findViewById(R.id.profile_llblood);

//        llname.setOnClickListener(this);
  //      llcell.setOnClickListener(this);
    //    lldivision.setOnClickListener(this);
      //  llblood.setOnClickListener(this);

        fetchUserProfileFromServer();

        // fetchFromServer();

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //startActivity(new Intent(getContext(), PostQuestion.class));
//                SharedPreferences.Editor editor=baseSharedPref.edit();
//                editor.clear().apply();
//                startActivity(new Intent(getContext(), Login.class));
//                getActivity().finish();
//            }
//        });

    }



    private void fetchUserProfileFromServer() {
        baseProgressBar.show();
        StringRequest profileDataStr = new StringRequest(Request.Method.POST, profileDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("profile_rsp", response);
                try {
                    JSONObject userData = new JSONObject(response);
                    name.setText(userData.getString("user_name"));
                    mobile.setText(userData.getString("user_mobile"));
                    division.setText(userData.getString("division"));
                    bloodgroup.setText(userData.getString("blood_group"));

                    //load image using Glide

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();

                }
                baseProgressBar.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                Log.d("profile_err",error.toString());
                baseProgressBar.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("task", "getData");
                params.put("userID", baseSharedPref.getString("userID", "none"));
                return params;
            }
        };
        NetworkSingleton.getInstance().getRequestQueue().add(profileDataStr);
    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        // updateData.clear();
        builder.setTitle("Edit Data");
        //set message inside case:
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String data = input.getText().toString();
                updateData.put("value", data);
                sendDataToServer(dialogInterface, updateData);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        })
                .setView(input);

        switch (view.getId()) {
            case R.id.profile_llcell:
                builder.setMessage("Update your phone number:");
                updateData.put("updateVar", "mobile");
                builder.show();
                break;
            case R.id.profile_llname:
                builder.setMessage("Update your name");
                updateData.put("updateVar", "name");
                builder.show();
                break;
            case R.id.profile_llblood:
                builder.setMessage("Update your blood Group");
                updateData.put("updateVar", "blood_group");
                builder.show();
                break;
            case R.id.profile_lldivision:
                builder.setMessage("Update your Division");
                updateData.put("updateVar", "division");
                builder.show();
                break;
        }
    }


    private void sendDataToServer(final DialogInterface dialogInterface, final Map<String, String> updateData) {
        StringRequest profileRequest = new StringRequest(Request.Method.POST, profileDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                if(updateData.get("updateVar").equals("mobile")){
                    SharedPreferences.Editor editor=baseSharedPref.edit();
                    editor.putString("user_mobile",updateData.get("value"));
                    editor.apply();
                }
                fetchUserProfileFromServer();
                dialogInterface.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                updateData.put("task", "editData");
                updateData.put("userID", baseSharedPref.getString("userID", "none"));
                return updateData;
            }
        };

        NetworkSingleton.getInstance().getRequestQueue().add(profileRequest);
    }
}
