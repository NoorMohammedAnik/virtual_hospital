package com.example.rr.virtual_hospital.nav_drawer.blood_bank;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.BaseFragment;
import com.example.rr.virtual_hospital.nav_drawer.blood_bank.adapter.BloodBankRecyclerAdapter;
import com.example.rr.virtual_hospital.profile.adapters.RecyclerAdapter;
import com.example.rr.virtual_hospital.profile.model.User;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BloodBank extends BaseFragment {

    private String filter_bldGroup="All Blood Groups", filter_division="All Cities";
    private List<com.example.rr.virtual_hospital.nav_drawer.blood_bank.model.User> users;
    private RecyclerView directoryRecycler;
    private BloodBankRecyclerAdapter directoryRecyclerAdapter;
    private Spinner spn_filter_city,spn_filter_blood_group;
    private Resources res;
    private String[] cities,bloodGroups;
    private String alldataURL;
    //private RequestQueue directoryReq;
   // private ProgressDialog pd;


    public BloodBank() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_bank, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alldataURL=getResources().getString(R.string.serverIp)+"blood_bank_data.php";
        users=new ArrayList<>();
        directoryRecycler= (RecyclerView) view.findViewById(R.id.recycler_directory);
        RecyclerView.LayoutManager llm= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        directoryRecycler.setLayoutManager(llm);
        directoryRecyclerAdapter=new BloodBankRecyclerAdapter(users,getContext());
        directoryRecycler.setAdapter(directoryRecyclerAdapter);
        baseProgressBar.setCancelable(true);

        spn_filter_city=(Spinner) view.findViewById(R.id.spn_filter_city);
        spn_filter_blood_group= (Spinner) view.findViewById(R.id.spn_filter_blood_group);
        res= getResources();
        cities= res.getStringArray(R.array.cities);
        bloodGroups= res.getStringArray(R.array.blood_groups);
        //init adapters
        ArrayAdapter<String> cityFilter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,cities);
        ArrayAdapter<String> bloodGroupFilter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,bloodGroups);

        //init resourcefile:
        cityFilter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupFilter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_filter_city.setAdapter(cityFilter);
        spn_filter_blood_group.setAdapter(bloodGroupFilter);

        spn_filter_blood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filter_bldGroup=adapterView.getItemAtPosition(i).toString();

                if(filter_bldGroup.equals("All Blood Groups") && filter_division.equals("All Cities")){
                    fetchDataFromServer("filter_both","1=1","1=1");
                }else
                if(filter_bldGroup.equals("All Blood Groups") && !filter_division.equals("All Cities")) {
                    fetchDataFromServer("filter_both", filter_division, "1=1");
                    Log.d("filtercalled", "city default from bloodgroup");
                    //filter city all city
                }else
                if(!filter_bldGroup.equals("All Blood Groups") && filter_division.equals("All Cities")){
                    //Filter city is not all city and filter blood groups is not all bloodgroup
                    fetchDataFromServer("filter_both","1=1",filter_bldGroup);
                    Log.d("filtercalled","both set from bloodgroup");
                }else{
                    fetchDataFromServer("filter_both",filter_division,filter_bldGroup);
                }
                Toast.makeText(getContext(), filter_bldGroup+" N "+filter_division , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "NothingSelected", Toast.LENGTH_SHORT).show();
            }
        });

        spn_filter_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filter_division=adapterView.getItemAtPosition(i).toString();
                if(filter_bldGroup.equals("All Blood Groups") && filter_division.equals("All Cities")){
                    fetchDataFromServer("filter_both","1=1","1=1");
                }else
                if(filter_bldGroup.equals("All Blood Groups") && !filter_division.equals("All Cities")) {
                    fetchDataFromServer("filter_both", filter_division, "1=1");
                    Log.d("filtercalled", "city default from bloodgroup");
                    //filter city all city
                }else
                if(!filter_bldGroup.equals("All Blood Groups") && filter_division.equals("All Cities")){
                    //Filter city is not all city and filter blood groups is not all bloodgroup
                    fetchDataFromServer("filter_both","1=1",filter_bldGroup);
                    Log.d("filtercalled","both set from bloodgroup");
                }else{
                    fetchDataFromServer("filter_both",filter_division,filter_bldGroup);
                }

                Toast.makeText(getContext(), filter_bldGroup+" N "+filter_division , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "NothingSelected", Toast.LENGTH_SHORT).show();
            }
        });

        fetchDataFromServer("alldata");

    }

    //fetch all data
    private void fetchDataFromServer(final String task){
        baseProgressBar.show();
        StringRequest alldata=new StringRequest(Request.Method.POST, alldataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("directoryResponse",response);
                users.clear();
                try {
                    JSONArray alldata=new JSONArray(response);
                    for(int i=0;i<alldata.length();i++){
                        com.example.rr.virtual_hospital.nav_drawer.blood_bank.model.User user=new com.example.rr.virtual_hospital.nav_drawer.blood_bank.model.User();
                        JSONObject singleData= alldata.getJSONObject(i);
                        user.setName(singleData.getString("user_name"));
                        user.setCotactNumber(singleData.getString("user_mobile"));
                        user.setBloodGroup(singleData.getString("blood_group"));
                        user.setDivision(singleData.getString("division"));
                        // user.setLastDonated(singleData.getString("last_blood_donated"));
                        users.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                directoryRecyclerAdapter.notifyDataSetChanged();
                baseProgressBar.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("directoryError",error.toString());
                baseProgressBar.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("task",task);
                return params;
            }
        };
        NetworkSingleton.getInstance().getRequestQueue().add(alldata);
    }

    private void fetchDataFromServer(final String task, final String city,final String bloodgroup) {
        baseProgressBar.show();
        StringRequest alldata=new StringRequest(Request.Method.POST, alldataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("directoryResponse",response);
                users.clear();
                try {
                    JSONArray alldata=new JSONArray(response);
                    for(int i=0;i<alldata.length();i++){
                        com.example.rr.virtual_hospital.nav_drawer.blood_bank.model.User user=new com.example.rr.virtual_hospital.nav_drawer.blood_bank.model.User();
                        JSONObject singleData= alldata.getJSONObject(i);
                        user.setName(singleData.getString("user_name"));
                        user.setCotactNumber(singleData.getString("user_mobile"));
                        user.setBloodGroup(singleData.getString("blood_group"));
                        user.setDivision(singleData.getString("division"));
                        users.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                directoryRecyclerAdapter.notifyDataSetChanged();
                baseProgressBar.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("directoryError",error.toString());
                baseProgressBar.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("task",task);
                params.put("data_city",city);
                params.put("data_bloodgroup",bloodgroup);

                return params;
            }
        };
        NetworkSingleton.getInstance().getRequestQueue().add(alldata);
    }
}
