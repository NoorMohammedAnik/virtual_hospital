package com.example.rr.virtual_hospital.doctors;

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
import com.example.rr.virtual_hospital.hospital.CustomListAdapter;
import com.example.rr.virtual_hospital.hospital.HospitalListActivity;
import com.example.rr.virtual_hospital.info.OnlineDataLoader;
import com.example.rr.virtual_hospital.info.model.Diagonostics;
import com.example.rr.virtual_hospital.info.model.Doctors;
import com.example.rr.virtual_hospital.info.model.Hospital;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsActivity extends AppCompatActivity {
    private CustomDoctorsAdapter customDoctorsAdapter;
    private ListView listView;
    private List<Doctors> docList;

    private String[] docs_Name;
            /*={"Prof. (Dr.) M.A. Faiz","Prof. (Dr.) Faridul Islam","Prof. (Dr.) Md. Gofranul Hoque","Dr. Md. Amir Hossain",
            "Dr. Anisul Awal","Dr. Mehrunnissa Khanom","Prof. (Dr.) Emran Bin Yunus","Dr. M.A. Kashem","Dr. Md. Abdul Quader","Prof. (Dr.) Md. Ridwanur Rahman","Dr. Md. Hasanuzzaman","Prof (Dr.) Mulkutur Rahman","Prof. Brig Gen. (Dr.) SK. Md. Bahar Hussain","Dr. Shamim Boksha",
            "Dr. Md. Iftikher Hossain Khan","Dr. Jamal Ahmed","Dr. Mohammad Ibrahim Chowdhury","Dr. Mohammad Ibrahim Chowdhury","Dr. Khondker A K Azad",
            "Dr. Md. Sanaullah Shelly","Prof. (Dr.) S. M. Tariq","Prof. (Dr.) Omar Faruque Yusuf","Prof. (Dr.) M. Manzurul Islam","Dr. Md. O.F.G. Kibria",
            "Dr. Md. Samirul Islam",
            "Prof. (Dr.) Nil Kantha Bhatachharja","Prof. (Dr.) Md Zillur Rahman","Dr. Zeenat Meraj Chowdhury",
            "Prof. (Dr.) Wazir Ahmed","Dr. Ekramul Hoque","Dr. Rasheda Samad",
            "Prof. (Dr.) Shamsunnahar","Dr. Rowshan Morshed","Dr. Shahena Akter","Dr. Shoyela Shahnaz","Dr. Shamima Anwar","Dr. Sajjad Mohammed Yusuff","Prof. Aminuddin A Khan","Dr. Shaik Ahmed","Dr. Md. Qumrul Ahsan","Dr. Anisul Moula","Dr. Rehnuma Rashid","Dr. Syed Ahmed","Dr. Nasima Akter"};*/
    private int[] pic={R.drawable.my_doctor};
    private String listDataURL= "https://ideabinbd.com/src/v_hosp/list_data_loader.php";
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docs);
        listView=(ListView)findViewById(R.id.listDoc);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        docList=new ArrayList<>();
        pd=new ProgressDialog(this);
        pd.setTitle("Loading Data");
        fetchDataFromOnline("doctors");


        getSupportActionBar().setTitle("Doctor's List");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(DoctorsActivity.this, DoctorsDetailsActivity.class);
                intent.putExtra("docName", docList.get(position));
                Toast.makeText(getApplicationContext(), "" + docs_Name[position], Toast.LENGTH_SHORT).show();
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
                    docs_Name=new String[totalData.length()];
                    for (int i=0; i<totalData.length();i++){
                        Doctors doc=new Doctors();
                        JSONObject eachData=totalData.getJSONObject(i);
                        docs_Name[i]=eachData.getString("doctors_name");
                        doc.setDocID(eachData.getString("doctors_id"));
                        doc.setDocName(eachData.getString("doctors_name"));
                        doc.setDocSpeciality(eachData.getString("doctors_speciality"));
                        doc.setDocChamber(eachData.getString("doctors_chamber"));
                        doc.setDocVisitTime(eachData.getString("doctors_visit_hour"));
                        doc.setDocContact(eachData.getString("doctors_contact"));
                        doc.setDocDivision(eachData.getString("doctors_division"));
                        doc.setDocLocation(eachData.getString("doctors_location"));
                        docList.add(doc);
                    }
                    customDoctorsAdapter = new CustomDoctorsAdapter(getApplicationContext(),docs_Name,pic);
                    listView.setAdapter(customDoctorsAdapter);
                    customDoctorsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
                Toast.makeText(DoctorsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
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
    public boolean onOptionsItemSelected (MenuItem item){
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
