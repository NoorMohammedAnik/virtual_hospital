package com.example.rr.virtual_hospital.nav_drawer.prescriptions;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.Main_Nav;
import com.example.rr.virtual_hospital.nav_drawer.prescriptions.model.*;
import com.example.rr.virtual_hospital.nav_drawer.prescriptions.model.Prescriptions;
import com.example.rr.virtual_hospital.utils.NetworkSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ramim on 2/16/2017.
 */

public class SlideShowFragment extends DialogFragment {
    String TAG= SlideShowFragment.class.getSimpleName();
    ArrayList<com.example.rr.virtual_hospital.nav_drawer.prescriptions.model.Prescriptions> images;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    TextView lblCount,lblTitle,lblDescription;
    Button btnDelete,btnEdit;
    int selectedPosition=0;
    private SharedPreferences sp;
    private String prescriptionTasksURL;
    private Prescriptions img;
    public static SlideShowFragment newInstance(){
        SlideShowFragment f=new SlideShowFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.presc_fullscreen_preview,container,false);
        viewPager=(ViewPager)v.findViewById(R.id.viewpager);
        lblCount=(TextView)v.findViewById(R.id.lbl_count);
        lblTitle=(TextView)v.findViewById(R.id.name);
        lblDescription=(TextView)v.findViewById(R.id.description);
        btnDelete= (Button) v.findViewById(R.id.btn_full_prev_delete);
        prescriptionTasksURL=getString(R.string.serverIp)+"prescription.php";
        sp= getContext().getSharedPreferences("UserAccountData",MODE_PRIVATE);
        images=(ArrayList<Prescriptions>) getArguments().getSerializable("images");
        selectedPosition=getArguments().getInt("position");
        Log.e(TAG, "position: " + selectedPosition);
        Log.e(TAG, "images size: " + images.size());

        myViewPagerAdapter=new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewpagerPageChangeListener);
        setCurrentItem(selectedPosition);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder verifyDelete= new AlertDialog.Builder(getContext())
                        .setTitle("Do You Want To Delete This Picture?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                removePicFromDB();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                verifyDelete.show();
                //delete codes
            }
        });


        return v;
    }

    private void removePicFromDB() {
        StringRequest picDel=new StringRequest(Request.Method.POST, prescriptionTasksURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("prescServerResponse",response);
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(), Main_Nav.class);
                i.putExtra("openFrag",R.id.nav_presc);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("task","delete_prescription");
                params.put("prescID",img.getId());
                params.put("prescName",img.getUrl());
                return params;
            }
        };
        NetworkSingleton.getInstance().getRequestQueue().add(picDel);
    }

    private void setCurrentItem(int selectedPosition) {
        viewPager.setCurrentItem(selectedPosition,false);
        displayMetaInfo(selectedPosition);
    }

    private void displayMetaInfo(int selectedPosition) {
        lblCount.setText((selectedPosition + 1) + "of" + images.size());
        img= images.get(selectedPosition);
        lblTitle.setText(img.getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    ViewPager.OnPageChangeListener viewpagerPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view= layoutInflater.inflate(R.layout.presc_fullscreen_single_content,container,false);
            ImageView imageView=(ImageView)view.findViewById(R.id.full_image);
            Prescriptions image= images.get(position);
            Glide.with(getActivity()).load(image.getUrl())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==((View) object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}