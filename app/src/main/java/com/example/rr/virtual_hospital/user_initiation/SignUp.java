package com.example.rr.virtual_hospital.user_initiation;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private SharedPreferences signUpSP;
    private ProgressDialog loading;
    private EditText edtName,edtPassword,edtMobile,edtDob;
    private RadioButton rdoMale,rdoFemale;

    private Spinner spnBloodGroup,spnDivisions;
    private Button register;
    private String BloodGroups[]= {"A+","A-","B+","B-","O+","O-","AB+","AB-"},
            Divisions[]= {"Barishal","Chittagong","Dhaka","Khulna","Mymensingh","Rajshahi","Rangpur","Sylhet"};

    private String userRegURL="",selectedY,selectedM,selectedD;
    private Calendar today;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Register");

        signUpSP= getSharedPreferences("com.example.rr.virtual_hospital.UserPrefs",MODE_PRIVATE);
        userRegURL=getString(R.string.serverIp)+"registration.php";

        edtName=(EditText) findViewById(R.id.txt_name);
        edtPassword=(EditText) findViewById(R.id.txt_password);
        edtMobile=(EditText) findViewById(R.id.txt_cell);
        edtDob= (EditText) findViewById(R.id.txt_dob);
        rdoMale= (RadioButton) findViewById(R.id.rdo_male);
        rdoFemale= (RadioButton) findViewById(R.id.rdo_female);

        spnBloodGroup= (Spinner) findViewById(R.id.spn_reg_blood_group);

        spnDivisions= (Spinner) findViewById(R.id.spn_reg_division);
       register=(Button)findViewById(R.id.btnSignup);

        ArrayAdapter<String> bloodGroupAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,BloodGroups);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> divisionsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Divisions);
        divisionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnDivisions.setAdapter(divisionsAdapter);
        spnBloodGroup.setAdapter(bloodGroupAdapter);

        today=Calendar.getInstance();
        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialog datePickerDialog= new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Calendar c=Calendar.getInstance();
                        c.set(i,i1,i2);

                        selectedY=String.valueOf(i2);
                        selectedM=String.valueOf(i1+1);
                        selectedD=String.valueOf(i);
                        if(i2<10 && i1<10){
                            edtDob.setText("0"+selectedY+"-0"+selectedM+"-"+selectedD);
                        }else if(i<10){
                            edtDob.setText("0"+selectedY+"-"+selectedM+"-"+selectedD);
                        }else if(i2<10){
                            edtDob.setText(selectedY+"-0"+selectedM+"-"+selectedD);
                        }else{
                            edtDob.setText(selectedY+"-"+selectedM+"-"+selectedD);
                        }

                    }
                }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE) );

                datePickerDialog.setCancelable(true);
                datePickerDialog.setCanceledOnTouchOutside(true);
                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        edtDob.setText("");
                        dialogInterface.dismiss();
                    }
                });
                datePickerDialog.show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile,password,name,blood_group, division,gender,dateof_birth;
                mobile=edtMobile.getText().toString();
                password=edtPassword.getText().toString();
                name=edtName.getText().toString();
                if(rdoMale.isChecked()){gender="Male";}else{gender="Female";}
                dateof_birth=edtDob.getText().toString();
                blood_group=spnBloodGroup.getSelectedItem().toString();
                division=spnDivisions.getSelectedItem().toString();

                if(mobile.isEmpty() || password.isEmpty() || name.isEmpty() || blood_group.isEmpty() || division.isEmpty() || gender.isEmpty() ||dateof_birth.isEmpty()){
                    Toast.makeText(SignUp.this, "Please enter all the data", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<4){
                    edtPassword.setError("Password length should be greater than 4 characters");
                    requestFocus(edtPassword);
                }
                else{
                    loading = ProgressDialog.show(SignUp.this, "Sign up...", "Please Wait...", false, false);

                    loading.show();
                    dataToserver(mobile,password,name,blood_group,division,gender,dateof_birth);
                }
            }
        });
    }

    private void dataToserver(final String mobile, final String password, final String name, final String blood_group, final String division, final String gender, final String dateof_birth) {
        StringRequest registerUser= new StringRequest(Request.Method.POST, userRegURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SignUp.this, response, Toast.LENGTH_SHORT).show();
                Log.d("regServerResponse",response);
                loading.dismiss();
                startActivity(new Intent(SignUp.this,Login.class));
                SignUp.this.finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUp.this, error.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<>();
                data.put("mobile",mobile);
                data.put("password",password);
                data.put("name",name);
                data.put("district",division);
                data.put("blood_group",blood_group);
                data.put("gender",gender);
                data.put("dob",dateof_birth);

                return data;
            }
        };

        NetworkSingleton.getInstance().getRequestQueue().add(registerUser);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
