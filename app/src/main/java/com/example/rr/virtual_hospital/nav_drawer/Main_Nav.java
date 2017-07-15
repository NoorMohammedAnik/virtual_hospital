package com.example.rr.virtual_hospital.nav_drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.rr.virtual_hospital.R;

import com.example.rr.virtual_hospital.nav_drawer.askanexpert.DocMyPost;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.DoctorHome;
import com.example.rr.virtual_hospital.nav_drawer.askanexpert.PatientHome;
import com.example.rr.virtual_hospital.nav_drawer.blood_bank.BloodBank;
import com.example.rr.virtual_hospital.nav_drawer.prescriptions.Prescriptions;
import com.example.rr.virtual_hospital.nav_drawer.profile.Profile;
import com.example.rr.virtual_hospital.user_initiation.Login;

public class Main_Nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences baseSharedPref;
    private TextView username,userMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__nav);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        baseSharedPref= getSharedPreferences("com.example.rr.virtual_hospital.UserPrefs",MODE_PRIVATE);
  /*

*/
//   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header= navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        username= (TextView) header.findViewById(R.id.main_nav_username);
        userMobile = (TextView) header.findViewById(R.id.main_nav_user_mobile);
        username.setText(baseSharedPref.getString("user_name",""));
        userMobile.setText(baseSharedPref.getString("user_mobile",""));
        //for default selected fragment
        try{
            getIntent().getExtras().getInt("openFrag");
            displaySelectedScreen(getIntent().getExtras().getInt("openFrag"));
        }catch (NullPointerException e){
            displaySelectedScreen(R.id.nav_profile);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__nav, menu);
        return true;
    }
*/
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(item.getItemId());

        return true;
    }


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
       switch (itemId) {
            case R.id.nav_profile:
                fragment = new Profile();
                break;
            case R.id.nav_presc:
                fragment = new Prescriptions();
                break;


            case R.id.nav_aae:
                if(baseSharedPref.getString("party","").equals("P")){
                    fragment = new PatientHome();
                }else{
                    fragment = new DoctorHome();
                }
                break;

            case R.id.nav_blood_bank:
                fragment = new BloodBank();
                break;

           case R.layout.fragment_doc_my_post:
               fragment= new DocMyPost();
               break;

            case R.id.nav_logout:
                SharedPreferences.Editor editor=baseSharedPref.edit();
                editor.clear().apply();
                startActivity(new Intent(Main_Nav.this, Login.class));
                Main_Nav.this.finish();
                break;
//
//            case R.id.nav_about:
//                fragment = new About();
//                break;
//
//            case R.id.nav_logout:
//                finish();
//                startActivity(new Intent(this, SignInActivity.class));
//
//                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
}
