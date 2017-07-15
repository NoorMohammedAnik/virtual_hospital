package com.example.rr.virtual_hospital.nav_drawer.askanexpert.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramim on 6/6/2017.
 */

public class Posts implements Parcelable {

    private String key,userID,postID,quesTitle,quesDescription,quesPicture,postTimestamp,answered,docID,convoID,points;

    public Posts() {

    }

    protected Posts(Parcel in) {
        key = in.readString();
        userID = in.readString();
        postID = in.readString();
        quesTitle = in.readString();
        quesDescription = in.readString();
        quesPicture = in.readString();
        postTimestamp = in.readString();
        answered = in.readString();
        docID = in.readString();
        convoID = in.readString();
        points = in.readString();
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getQuesTitle() {
        return quesTitle;
    }

    public void setQuesTitle(String quesTitle) {
        this.quesTitle = quesTitle;
    }

    public String getQuesDescription() {
        return quesDescription;
    }

    public void setQuesDescription(String quesDescription) {
        this.quesDescription = quesDescription;
    }

    public String getQuesPicture() {
        return quesPicture;
    }

    public void setQuesPicture(String quesPicture) {
        this.quesPicture = quesPicture;
    }

    public String getPostTimestamp() {
        return postTimestamp;
    }

    public void setPostTimestamp(String postTimestamp) {
        this.postTimestamp = postTimestamp;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getConvoID() {
        return convoID;
    }

    public void setConvoID(String convoID) {
        this.convoID = convoID;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(userID);
        parcel.writeString(postID);
        parcel.writeString(quesTitle);
        parcel.writeString(quesDescription);
        parcel.writeString(quesPicture);
        parcel.writeString(postTimestamp);
        parcel.writeString(answered);
        parcel.writeString(docID);
        parcel.writeString(convoID);
        parcel.writeString(points);
    }
}
