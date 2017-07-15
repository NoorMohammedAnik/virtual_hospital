package com.example.rr.virtual_hospital.Splash_Intro;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by USER on 7/13/2017.
 */

public class intromanager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public intromanager(Context context)
    {
        this.context=context;
        pref=context.getSharedPreferences("first",0);
        editor = pref.edit();
    }

    public void setFirst(boolean isFirst)
    {
        editor.putBoolean("check",isFirst);
        editor.commit();
    }

    public boolean check()
    {
        return pref.getBoolean("check",true);
    }

}
