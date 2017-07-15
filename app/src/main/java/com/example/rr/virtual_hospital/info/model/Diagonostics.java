package com.example.rr.virtual_hospital.info.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramim on 7/6/2017.
 */

public class Diagonostics implements Parcelable {
  private   String diagID, diag_title,diag_facilities,diag_division,diag_location,diac_contact,diag_picture;

    public Diagonostics() {
    }

    protected Diagonostics(Parcel in) {
        diagID = in.readString();
        diag_title = in.readString();
        diag_facilities = in.readString();
        diag_division = in.readString();
        diag_location = in.readString();
        diac_contact = in.readString();
        diag_picture = in.readString();
    }

    public static final Creator<Diagonostics> CREATOR = new Creator<Diagonostics>() {
        @Override
        public Diagonostics createFromParcel(Parcel in) {
            return new Diagonostics(in);
        }

        @Override
        public Diagonostics[] newArray(int size) {
            return new Diagonostics[size];
        }
    };

    public String getDiagID() {
        return diagID;
    }

    public void setDiagID(String diagID) {
        this.diagID = diagID;
    }

    public String getDiag_title() {
        return diag_title;
    }

    public void setDiag_title(String diag_title) {
        this.diag_title = diag_title;
    }

    public String getDiag_facilities() {
        return diag_facilities;
    }

    public void setDiag_facilities(String diag_facilities) {
        this.diag_facilities = diag_facilities;
    }

    public String getDiag_division() {
        return diag_division;
    }

    public void setDiag_division(String diag_division) {
        this.diag_division = diag_division;
    }

    public String getDiag_location() {
        return diag_location;
    }

    public void setDiag_location(String diag_location) {
        this.diag_location = diag_location;
    }

    public String getDiac_contact() {
        return diac_contact;
    }

    public void setDiac_contact(String diac_contact) {
        this.diac_contact = diac_contact;
    }

    public String getDiag_picture() {
        return diag_picture;
    }

    public void setDiag_picture(String diag_picture) {
        this.diag_picture = diag_picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(diagID);
        parcel.writeString(diag_title);
        parcel.writeString(diag_facilities);
        parcel.writeString(diag_division);
        parcel.writeString(diag_location);
        parcel.writeString(diac_contact);
        parcel.writeString(diag_picture);
    }
}
