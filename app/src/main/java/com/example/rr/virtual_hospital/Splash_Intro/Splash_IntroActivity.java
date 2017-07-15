package com.example.rr.virtual_hospital.Splash_Intro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rr.virtual_hospital.MainActivity;
import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.SplashActivity;

public class Splash_IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private intromanager introManager;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private ViewPagerAdapter viewPagerAdapter;
    Button next,skip;
    private int[] layouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introManager = new intromanager(this);
        if(introManager.check()==false)
        {
            introManager.setFirst(false);
            Intent i = new Intent(Splash_IntroActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            if(Build.VERSION.SDK_INT>=21)
            {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
            setContentView(R.layout.activity_splash__intro);

            viewPager = (ViewPager)findViewById(R.id.view_pager);
            dotsLayout=(LinearLayout)findViewById(R.id.laoutDots);
            skip=(Button)findViewById(R.id.btn_skip);
            next=(Button)findViewById(R.id.btn_next);
            layouts = new int[]{R.layout.activity_screen1,R.layout.activity_screen2,R.layout.activity_screen3,R.layout.activity_screen4,R.layout.activity_screen5};
            addButtonDots(0);
            changeStatusBarColor();
            viewPagerAdapter = new ViewPagerAdapter();
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.addOnPageChangeListener(vewListener);

            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Splash_IntroActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int current = getItem(+1);
                    if(current<layouts.length)
                    {
                        viewPager.setCurrentItem(current);
                    }
                    else
                    {
                        Intent i = new Intent(Splash_IntroActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                }
            });

        }
        introManager.setFirst(false);

    }

    private void addButtonDots(int position){

        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }

        if(dots.length>0)
        {
            dots[position].setTextColor(colorActive[position]);
        }

    }

    private int getItem(int i)
    {
        return viewPager.getCurrentItem()+i;
    }

    ViewPager.OnPageChangeListener vewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addButtonDots(position);
            if(position==layouts.length-1)
            {
                next.setText("PROCEED");
                skip.setVisibility(View.GONE);

            }
            else{
                next.setText("NEXT");
                skip.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class ViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v =(View)object;
            container.removeView(v);
        }
    }
}
