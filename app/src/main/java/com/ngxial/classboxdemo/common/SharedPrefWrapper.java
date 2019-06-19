package com.ngxial.classboxdemo.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class SharedPrefWrapper {

    private static SharedPrefWrapper mInstance = null;
    private static SharedPreferences mPref = null;
    private static SharedPreferences.Editor mEditor = null;

    public static SharedPrefWrapper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new SharedPrefWrapper();
            mPref = ctx.getSharedPreferences(KeyTagList.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            mEditor = mPref.edit();
        }

        return mInstance;
    }


    public void clear() {
        mEditor.clear().commit();
    }

    public String getToken() {
        return mPref.getString(KeyTagList.TOKEN, "");
    }

    public void setToken(String token) {
        mEditor.putString(KeyTagList.TOKEN, token);
        mEditor.apply();
    }

    public String getID() {
        return mPref.getString(KeyTagList._ID, "");
    }

    public void setID(String _id) {
        mEditor.putString(KeyTagList._ID, _id);
        mEditor.apply();
    }

    public String getBoxGrouplID() {
        return mPref.getString(KeyTagList.BOX_GROUP_ID, "");
    }

    public void setBoxGroupID(String boxGroupID) {
        mEditor.putString(KeyTagList.BOX_GROUP_ID, boxGroupID);
        mEditor.apply();
    }

    public String getChanneGrouplID() {
        return mPref.getString(KeyTagList.CHANNEL_GROUP_ID, "");
    }

    public void setChannelGroupID(String channelGroupID) {
        mEditor.putString(KeyTagList.CHANNEL_GROUP_ID, channelGroupID);
        mEditor.apply();
    }

    public String getChannelID() {
        return mPref.getString(KeyTagList.CHANNEL_ID, "");
    }

    public void setChannelID(String channelID) {
        mEditor.putString(KeyTagList.CHANNEL_ID, channelID);
        mEditor.apply();
    }

    public String getProgramID() {
        return mPref.getString(KeyTagList.PROGRAM_ID, "");
    }

    public void setProgramID(String programID) {
        mEditor.putString(KeyTagList.PROGRAM_ID, programID);
        mEditor.apply();
    }
}
