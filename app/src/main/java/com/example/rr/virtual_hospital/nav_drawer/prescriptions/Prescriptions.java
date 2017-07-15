package com.example.rr.virtual_hospital.nav_drawer.prescriptions;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.rr.virtual_hospital.nav_drawer.prescriptions.adapter.PrescriptionRecyclerAdapter;
import com.example.rr.virtual_hospital.profile.Profile;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Prescriptions extends BaseFragment {
    private RecyclerView prescRecycler;
    private ImageButton FAB;
    private List<com.example.rr.virtual_hospital.nav_drawer.prescriptions.model.Prescriptions> images;
    private PrescriptionRecyclerAdapter mAdapter;
    private FragmentTransaction ft;
    private String prescriptionTasksURL;

    public Prescriptions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_prescriptions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FAB= (ImageButton) view.findViewById(R.id.btn_upload_prescs);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),UploadPrescription.class));
            }
        });
        images=new ArrayList<>();
        mAdapter=new PrescriptionRecyclerAdapter(images,getActivity());
        prescriptionTasksURL=getString(R.string.serverIp)+"prescription.php";
        prescRecycler= (RecyclerView) view.findViewById(R.id.recycler_prescription_display);
        prescRecycler.setAdapter(mAdapter);
        //Toast.makeText(getContext(),baseSharedPref.getString("userID","none") , Toast.LENGTH_SHORT).show();
        RecyclerView.LayoutManager llm=new GridLayoutManager(view.getContext(),2);
        prescRecycler.setLayoutManager(llm);
        prescRecycler.setItemAnimator(new DefaultItemAnimator());


        fetchImages();

        prescRecycler.addOnItemTouchListener(new PrescriptionRecyclerAdapter.RecyclerTouchListener(view.getContext(), prescRecycler, new PrescriptionRecyclerAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("images", (Serializable) images);
                bundle.putInt("position",position);
                ft= getFragmentManager().beginTransaction();
                SlideShowFragment sdf= SlideShowFragment.newInstance();
                sdf.setArguments(bundle);
                sdf.show(ft,"slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void fetchImages() {
        baseProgressBar.setMessage("Loading");
        baseProgressBar.show();
        StringRequest streq=new StringRequest(Request.Method.POST, prescriptionTasksURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("prescResp",response);
                JSONArray allData= null;
                try {
                    allData = new JSONArray(response);

                    for (int i = 0; i < allData.length(); i++) {
                        JSONObject singleData = allData.getJSONObject(i);
                        com.example.rr.virtual_hospital.nav_drawer.prescriptions.model.Prescriptions prsc=new com.example.rr.virtual_hospital.nav_drawer.prescriptions.model.Prescriptions();
                        prsc.setId(singleData.getString("id"));
                        prsc.setUserid(singleData.getString("user_id"));
                        prsc.setName(singleData.getString("prescription_name"));
                        prsc.setUrl(singleData.getString("prescription_image"));
                        images.add(prsc);
                    }
                    if(images.isEmpty()){
                        prescRecycler.setBackgroundResource(R.drawable.prescription_rx_placeholder);

                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    baseProgressBar.dismiss();
                }

                baseProgressBar.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error Loading Prescriptions", Toast.LENGTH_SHORT).show();                baseProgressBar.dismiss();
                Log.d("prescsErr",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("task","download_prescription");
                params.put("user_id",baseSharedPref.getString("userID","none"));

                return params;
            }
        };
        NetworkSingleton.getInstance().getRequestQueue().add(streq);
    }
}
