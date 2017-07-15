package com.example.rr.virtual_hospital.nav_drawer.prescriptions.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramim on 4/18/2017.
 */

public class Prescriptions implements Parcelable{

    String id,userid,name,url;
//String imageServerLocation="https://ideabinbd.com/src/v_hosp/vhosp_prescriptions/";
//String imageServerLocation="http://10.0.3.2/virtual_hospital/vhosp_prescriptions/";
    String imageServerLocation="https://ideabinbd.com/src/v_hosp/vhosp_prescriptions/";
    public Prescriptions() {
    }

    protected Prescriptions(Parcel in) {
        id = in.readString();
        userid = in.readString();
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Prescriptions> CREATOR = new Creator<Prescriptions>() {
        @Override
        public Prescriptions createFromParcel(Parcel in) {
            return new Prescriptions(in);
        }

        @Override
        public Prescriptions[] newArray(int size) {
            return new Prescriptions[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return imageServerLocation+url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(userid);
        parcel.writeString(name);
        parcel.writeString(url);
    }
}
