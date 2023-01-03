package com.izza.pmobile.latihan;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    String TAG = SessionManager.class.getSimpleName();
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    int id;

    int PRIVATE_MODE = 0;
    String KEY_IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context){
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void saveSession(int user){
        this.id = user;
        editor.putInt(SESSION_KEY, id).commit();
    }

    public int getSession(){
        return sp.getInt(SESSION_KEY, -1);
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY, -1).commit();
    }

//    public void setLogin(boolean isLoggedIn){
//        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
//        editor.commit();
//        Log.d(TAG, "User login session modified");
//    }
//
//    public boolean isLoggedIn(){
//        return sp.getBoolean(KEY_IS_LOGGED_IN, false);
//    }
}
