package com.example.kryguu.application;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Client implements Parcelable, IDatabaseObject {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    private String mSurname;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("email")
    private String mEmail;

    public Client() {
    }

    public Client(String name, String surname, String phone, String email) {
        this.mName = name;
        this.mSurname = surname;
        this.mPhone = phone;
        this.mEmail = email;
        this.mAddress = "";
    }

    protected Client(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mSurname = in.readString();
        mPhone = in.readString();
        mEmail = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mName);
        parcel.writeString(mSurname);
        parcel.writeString(mPhone);
        parcel.writeString(mEmail);
    }

    public long getmId() {
        return mId;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String phone) { mPhone = phone; }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String surname) {
        mSurname = surname;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        mName = name;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String email) {
        mEmail = email;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public void setId(long id) {
        mId = id;
    }
}
