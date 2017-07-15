package com.example.rr.virtual_hospital.info.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramim on 7/6/2017.
 */

public class Hospital implements Parcelable{
 private    String hospID,hospTitle,hospDescription,hospDivision,hospLocation,hospContact,hospDocList,hospRating,hospPicture;

    public Hospital() {
    }

    protected Hospital(Parcel in) {
        hospID = in.readString();
        hospTitle = in.readString();
        hospDescription = in.readString();
        hospDivision = in.readString();
        hospLocation = in.readString();
        hospContact = in.readString();
        hospDocList = in.readString();
        hospRating = in.readString();
        hospPicture = in.readString();
    }

    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override
        public Hospital createFromParcel(Parcel in) {
            return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };

    public String getHospID() {
        return hospID;
    }

    public void setHospID(String hospID) {
        this.hospID = hospID;
    }

    public String getHospTitle() {
        return hospTitle;
    }

    public void setHospTitle(String hospTitle) {
        this.hospTitle = hospTitle;
    }

    public String getHospDescription() {
        return hospDescription;
    }

    public void setHospDescription(String hospDescription) {
        this.hospDescription = hospDescription;
    }

    public String getHospDivision() {
        return hospDivision;
    }

    public void setHospDivision(String hospDivision) {
        this.hospDivision = hospDivision;
    }

    public String getHospLocation() {
        return hospLocation;
    }

    public void setHospLocation(String hospLocation) {
        this.hospLocation = hospLocation;
    }

    public String getHospContact() {
        return hospContact;
    }

    public void setHospContact(String hospContact) {
        this.hospContact = hospContact;
    }

    public String getHospDocList() {
        return hospDocList;
    }

    public void setHospDocList(String hospDocList) {
        this.hospDocList = hospDocList;
    }

    public String getHospRating() {
        return hospRating;
    }

    public void setHospRating(String hospRating) {
        this.hospRating = hospRating;
    }

    public String getHospPicture() {
        return hospPicture;
    }

    public void setHospPicture(String hospPicture) {
        this.hospPicture = hospPicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(hospID);
        parcel.writeString(hospTitle);
        parcel.writeString(hospDescription);
        parcel.writeString(hospDivision);
        parcel.writeString(hospLocation);
        parcel.writeString(hospContact);
        parcel.writeString(hospDocList);
        parcel.writeString(hospRating);
        parcel.writeString(hospPicture);
    }
}
