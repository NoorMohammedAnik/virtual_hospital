package com.example.rr.virtual_hospital.user_initiation;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rr.virtual_hospital.MainActivity;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.Main_Nav;
import com.example.rr.virtual_hospital.profile.Profile;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;
import com.example.rr.virtual_hospital.utils.Utilities;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


//HAve To Done By Ramim Vai

public class Login extends AppCompatActivity {

    private EditText userMobile,password;
    private Button submit,signUp;
    private ProgressDialog loading;
    private String URL="";
    private SharedPreferences sp;
    private Utilities utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Login");

        URL=getString(R.string.serverIp)+"login.php";
        userMobile =(EditText)findViewById(R.id.txt_cell);
        password =(EditText)findViewById(R.id.txtPass);
        submit=(Button)findViewById(R.id.btn_login);
        signUp=(Button)findViewById(R.id.btn_register);
        utils=new Utilities();
        sp=getSharedPreferences("com.example.rr.virtual_hospital.UserPrefs",MODE_PRIVATE);


        checkLogged();

        loading=new ProgressDialog(this);
        loading.setTitle("Loging in....");
        loading.setIcon(R.mipmap.ic_launcher);
        loading.setButton(DialogInterface.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Login.this, com.example.rr.virtual_hospital.user_initiation.SignUp.class);
                startActivity(in);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername,strPassword;
                strUsername= userMobile.getText().toString().trim();
                strPassword= password.getText().toString().trim();

                if(TextUtils.isEmpty(strUsername)&&TextUtils.isEmpty(strPassword)){
                    Toast.makeText(Login.this, "Fill all the details", Toast.LENGTH_SHORT).show();
                }else{

                    sendDataToServer(strUsername,strPassword);
                    /*for phone purpose
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("userID","1");
                    editor.putString("user_mobile","123456789");
                    editor.putString("user_gender","male");
                    editor.putString("user_age","23");
                    editor.putString("party","P");
                    editor.apply();
                    startActivity(new Intent(Login.this,Main_Nav.class));
                    */
                }
            }
        });

    }

    private void checkLogged() {
        if(sp.getString("userID",null)!=null){
            Login.this.finish();
            //startActivity(new Intent(Login.this,MainActivity.class));
            startActivity(new Intent(Login.this,Main_Nav.class));
        }
    }

    private void sendDataToServer(final String strUsername, final String strPassword) {
        loading.show();
        StringRequest loginReq=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                loading.dismiss();


                try {
                    JSONObject userData=new JSONObject(response);
                    SharedPreferences.Editor editor=sp.edit();
                    String UserDOB=userData.getString("date_of_birth");
                    //TODO: make sure age is incremented even when logged in
                    String age= utils.getAge(UserDOB);
                  //  Log.d("serverResponse",response+"age="+age);

                    editor.putString("userID",userData.getString("id"));
                    editor.putString("user_mobile",userData.getString("mobile"));
                    editor.putString("user_gender",userData.getString("gender"));
                    editor.putString("user_name",userData.getString("user_name"));
                    editor.putString("user_age",age);
                    editor.putString("party",userData.getString("party"));
                    editor.apply();

                    Login.this.finish();
                    startActivity(new Intent(Login.this,Main_Nav.class));
                    loading.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Network Error", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> userData=new HashMap<>();
                userData.put("mobile",strUsername);
                userData.put("password",strPassword);
                return userData;
            }
        };

        NetworkSingleton.getInstance().getRequestQueue().add(loginReq);
    }
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
