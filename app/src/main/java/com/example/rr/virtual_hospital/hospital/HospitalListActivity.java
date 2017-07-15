package com.example.rr.virtual_hospital.hospital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.info.OnlineDataLoader;
import com.example.rr.virtual_hospital.info.model.*;
import com.example.rr.virtual_hospital.info.model.Hospital;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalListActivity extends AppCompatActivity {

   private String[] hospitalList;
   /* {"C.S.C.R","Chattagram Maa-Shishu General Hospital","CHEVRON","Chittagong Health Care Hospital Pvt. Ltd.","Chittagong Metropolitan Hospital Pvt. Ltd.","Chittagong Poly Clinic Pvt. Ltd."," Ekhushe Hospital","General Hospital","Holy Crescent Hospital (Pvt.) Ltd","Memon City Corporation","Medical Centre (Pvt) Clinic","Mahanagar Clinic","Labaid Specialized Hospital - Chittagong","Royal Hospital"};
    */

    private  CustomListAdapter customAdapter;
    private     ListView CustomList;
    private List<com.example.rr.virtual_hospital.info.model.Hospital> hospList;
    private     int flags[]={R.drawable.hospital_list};

    private String listDataURL= "https://ideabinbd.com/src/v_hosp/list_data_loader.php";
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);


        //for back button
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Hospital List");

        CustomList=(ListView)findViewById(R.id.listView);

        pd=new ProgressDialog(this);
        pd.setTitle("Loading Data");

        hospList=new ArrayList<>();

        fetchDataFromOnline("hospital");

        //list items click listener
        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(HospitalListActivity.this, HospitalDetailsActivity.class);
                intent.putExtra("hospitalName", hospList.get(position));
                Toast.makeText(getApplicationContext(), "" + hospitalList[position], Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        });
    }

    private void fetchDataFromOnline(final String tableName) {
        pd.show();
        StringRequest onlineDataLoader=new StringRequest(Request.Method.POST, listDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("serverResponse",response);
                try {
                    JSONArray totalData= new JSONArray(response);
                    hospitalList=new String[totalData.length()];
                    for (int i=0; i<totalData.length();i++){
                        Hospital hosp=new Hospital();
                        JSONObject eachData=totalData.getJSONObject(i);
                        hospitalList[i]=eachData.getString("hospital_title");
                        hosp.setHospID(eachData.getString("hospital_id"));
                        hosp.setHospTitle(eachData.getString("hospital_title"));
                        hosp.setHospDescription(eachData.getString("hospital_description"));
                        hosp.setHospDivision(eachData.getString("hospital_division"));
                        hosp.setHospLocation(eachData.getString("hospital_location"));
                        hosp.setHospContact(eachData.getString("hospital_contact"));
                        hosp.setHospDocList(eachData.getString("hospital_doctors_list"));
                        hosp.setHospRating(eachData.getString("hospital_rating"));
                        hosp.setHospPicture(eachData.getString("hospital_picture"));
                        hospList.add(hosp);
                    }
                    customAdapter=new CustomListAdapter(getApplicationContext(),hospitalList,flags);
                    CustomList.setAdapter(customAdapter);
                    customAdapter.notifyDataSetChanged();
                    pd.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
                Toast.makeText(HospitalListActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
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

    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
