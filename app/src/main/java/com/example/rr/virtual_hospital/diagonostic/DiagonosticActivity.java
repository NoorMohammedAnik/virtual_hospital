package com.example.rr.virtual_hospital.diagonostic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.rr.virtual_hospital.hospital.CustomListAdapter;
import com.example.rr.virtual_hospital.hospital.HospitalListActivity;
import com.example.rr.virtual_hospital.info.OnlineDataLoader;
import com.example.rr.virtual_hospital.info.model.Diagonostics;
import com.example.rr.virtual_hospital.info.model.Hospital;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Done By Rafid

public class DiagonosticActivity extends AppCompatActivity {
    private  DiagonosticAdapter diagonosticAdapter;
   private ListView listView;
    private List<Diagonostics> diagList;

    private    String[] diaList;
    /*={"APEX DIAGNOSTIC SERVICES (PVT.) LTD.",
            "BANGLADESH DIAGNOSTIC ","BIOPATH LABORATORY","BASIC LAB","BIOCHEMISTRY LAB","CARE INVESTIGATION ","CENTRAL LAB ","CHATTAGRAM METROPOLITAN HOSPITAL (PVT) LTD.","CHECK UP MEDICAL SERVICES LTD. ",
            "CHEVRON CLINICAL LABORATORY (PVT.) LTD.","CHITTAGONG BELLEVUE LTD.","CHITTAGONG DOCTORS LAB (PVT.) LTD. ","CHITTAGONG LAB LTD. ","CHITTAGONG POLY CLINIC (PVT) LTD","CITY HEALTH CLINIC ","CRESCENT DIAGNOSIS ","C.T. IMAGING & DIAGNOSTIC CENTRE LTD. ","CHITTAGONG METROPOLITAN HASPATAL DIAGNOSTIC LABORATORY","DIASONIC DIAGNOSTIC CENTRE","DIVINE DIAGNOSTIC LTD. ","DR. MAHFUZUR RAHMAN'S LAB ","DR. MUSH DENTAL CLINIC ","EAGLES EYE DIAGNOSTIC ","EVERGREEN CLINIC (PVT) LTD. ","HEALTH HOME PVT. LTD.","HEALTH SENSE DIAGNOSTIC CENTRE","KIDNEY DIAGNOSTIC COMPLEX ","LAB EXPERTS (PVT) LTD.","LANCET ","LIFE CARE CENTRE ","MAGNUM DIAGNOSTIC COMPLEX ","MAHANAGAR CLINIC (PVT) LTD.",
            "MEDI AID COMPLEX (PVT) LTD","MEDICAL CENTRE (PVT) CLINIC ","MEDICAL POINT","METRO DIAGNOSTI CENTER LIMITED"};
*/
    private    int[] pic={R.drawable.diagnosticforlist};

    private String listDataURL= "https://ideabinbd.com/src/v_hosp/list_data_loader.php";
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia);
        //for back button
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Diagnostic List");
        listView=(ListView)findViewById(R.id.listViewDia);

        diagList=new ArrayList<>();
        pd=new ProgressDialog(this);
        pd.setTitle("Loading Data");
       fetchDataFromOnline("diagonostics");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(DiagonosticActivity.this, DiagonosticDetailsActivity.class);
                intent.putExtra("docName", diagList.get(position));
                Toast.makeText(getApplicationContext(),"" + diaList[position], Toast.LENGTH_SHORT).show();
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
                    diaList=new String[totalData.length()];
                    for (int i=0; i<totalData.length();i++){
                        Diagonostics diag=new Diagonostics();
                        JSONObject eachData=totalData.getJSONObject(i);
                        diaList[i]=eachData.getString("diagonostics_title");
                        diag.setDiagID(eachData.getString("diagonostics_id"));
                        diag.setDiag_title(eachData.getString("diagonostics_title"));
                        diag.setDiag_division(eachData.getString("diagonostics_division"));
                        diag.setDiac_contact(eachData.getString("diagonostics_contact"));
                        diag.setDiag_facilities(eachData.getString("diagonostics_facilities"));
                        diag.setDiag_location(eachData.getString("diagonostics_location"));
                        diag.setDiag_picture(eachData.getString("diagonostics_picture"));
                        diagList.add(diag);
                    }
                    diagonosticAdapter = new DiagonosticAdapter(getApplicationContext(),diaList,pic);
                    listView.setAdapter(diagonosticAdapter);
                    diagonosticAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
                Toast.makeText(DiagonosticActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
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
