package com.example.kryguu.application;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kryguu on 10.12.2016.
 */

public class UserCredentials {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @SerializedName("username")
    private String mUsername;
    @SerializedName("password")
    private String mPassword;

    public UserCredentials() {
    }

    public UserCredentials(String username, String password) {
        mUsername = username;
        mPassword = password;
    }


    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
