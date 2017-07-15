package com.example.rr.virtual_hospital.info.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramim on 7/6/2017.
 */

public class Doctors implements Parcelable {
   private String docID,docName,docSpeciality,docChamber,docVisitTime,docContact,docDivision,docLocation;

    public Doctors() {
    }

    protected Doctors(Parcel in) {
        docID = in.readString();
        docName = in.readString();
        docSpeciality = in.readString();
        docChamber = in.readString();
        docVisitTime = in.readString();
        docContact = in.readString();
        docDivision = in.readString();
        docLocation = in.readString();
    }

    public static final Creator<Doctors> CREATOR = new Creator<Doctors>() {
        @Override
        public Doctors createFromParcel(Parcel in) {
            return new Doctors(in);
        }

        @Override
        public Doctors[] newArray(int size) {
            return new Doctors[size];
        }
    };

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocSpeciality() {
        return docSpeciality;
    }

    public void setDocSpeciality(String docSpeciality) {
        this.docSpeciality = docSpeciality;
    }

    public String getDocChamber() {
        return docChamber;
    }

    public void setDocChamber(String docChamber) {
        this.docChamber = docChamber;
    }

    public String getDocVisitTime() {
        return docVisitTime;
    }

    public void setDocVisitTime(String docVisitTime) {
        this.docVisitTime = docVisitTime;
    }

    public String getDocContact() {
        return docContact;
    }

    public void setDocContact(String docContact) {
        this.docContact = docContact;
    }

    public String getDocDivision() {
        return docDivision;
    }

    public void setDocDivision(String docDivision) {
        this.docDivision = docDivision;
    }

    public String getDocLocation() {
        return docLocation;
    }

    public void setDocLocation(String docLocation) {
        this.docLocation = docLocation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(docID);
        parcel.writeString(docName);
        parcel.writeString(docSpeciality);
        parcel.writeString(docChamber);
        parcel.writeString(docVisitTime);
        parcel.writeString(docContact);
        parcel.writeString(docDivision);
        parcel.writeString(docLocation);
    }
}
