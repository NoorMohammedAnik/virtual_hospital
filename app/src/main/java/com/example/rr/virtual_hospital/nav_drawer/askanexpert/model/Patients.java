package com.example.rr.virtual_hospital.nav_drawer.askanexpert.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramim on 6/6/2017.
 */

public class Patients implements Parcelable {
    private String key,userID,name,postID,userProfilePicture, dateofbirth,gender;

    public Patients() {
    }

    protected Patients(Parcel in) {
        key = in.readString();
        userID = in.readString();
        name = in.readString();
        postID = in.readString();
        userProfilePicture = in.readString();
        dateofbirth = in.readString();
        gender = in.readString();
    }

    public static final Creator<Patients> CREATOR = new Creator<Patients>() {
        @Override
        public Patients createFromParcel(Parcel in) {
            return new Patients(in);
        }

        @Override
        public Patients[] newArray(int size) {
            return new Patients[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(userID);
        parcel.writeString(name);
        parcel.writeString(postID);
        parcel.writeString(userProfilePicture);
        parcel.writeString(dateofbirth);
        parcel.writeString(gender);
    }
}
