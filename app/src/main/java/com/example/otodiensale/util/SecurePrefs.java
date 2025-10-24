package com.example.otodiensale.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class SecurePrefs {
    private static final String PREF_NAME = "secure_prefs";
    private static final String SECRET_KEY = "OtoDienSale@2025!";

    public static void saveEncrypted(Context context, String key, String value) {
        try {
            String encoded = Base64.encodeToString(
                    xor(value.getBytes(StandardCharsets.UTF_8), SECRET_KEY.getBytes()),
                    Base64.DEFAULT
            );
            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            prefs.edit().putString(key, encoded).apply();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static String getDecrypted(Context context, String key) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            String encoded = prefs.getString(key, null);
            if (encoded == null) return null;
            byte[] decoded = Base64.decode(encoded, Base64.DEFAULT);
            return new String(xor(decoded, SECRET_KEY.getBytes()), StandardCharsets.UTF_8);
        } catch (Exception e) { return null; }
    }

    private static byte[] xor(byte[] data, byte[] key) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++)
            result[i] = (byte) (data[i] ^ key[i % key.length]);
        return result;
    }
}

