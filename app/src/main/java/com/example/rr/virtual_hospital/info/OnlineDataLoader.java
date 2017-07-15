package com.example.rr.virtual_hospital.info;


import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;
import com.google.android.gms.fitness.data.Application;

import java.util.HashMap;
import java.util.Map;

public class OnlineDataLoader {
    private String tableName,returnData;
    private String listDataURL="";
    private Context context;
    private ProgressDialog pd;

    public OnlineDataLoader(String tableName, Context context) {
        this.tableName=tableName;
        this.context=context;
        listDataURL= "https://ideabinbd.com/src/v_hosp/list_data_loader.php";
        pd=new ProgressDialog(context);
        pd.setTitle("Loading Data");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);
    }
    public void getData(){
        pd.show();
        StringRequest onlineDataLoader=new StringRequest(Request.Method.GET, listDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            returnData=response;
            pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                returnData= error.toString();
            pd.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("table_name",tableName);
                return map;
            }
        };
        NetworkSingleton.getInstance().getRequestQueue().add(onlineDataLoader);
    }

}
