package com.example.evsaleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String PREF_NAME = "EVSaleAppPrefs";
    private static final String KEY_USERNAME = "username";

    private static SharedPrefManager instance;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private SharedPrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveUser(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public String getUsername() {
        return prefs.getString(KEY_USERNAME, null);
    }

    public void logout() {
        editor.clear().apply();
    }

    public boolean isLoggedIn() {
        return getUsername() != null;
    }
}
