package com.example.evsaleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.evsaleapp.data.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static final String PREF_NAME = "cart_prefs";
    private static final String KEY_CART = "cart_items";

    public static void addToCart(Context context, Product product) {
        List<Product> cart = getCart(context);
        cart.add(product);
        saveCart(context, cart);
    }

    public static void removeFromCart(Context context, int productId) {
        List<Product> cart = getCart(context);
        for (Product p : new ArrayList<>(cart)) {
            if (p.getId() == productId) {
                cart.remove(p);
                break;
            }
        }
        saveCart(context, cart);
    }

    public static List<Product> getCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CART, null);
        if (json == null) return new ArrayList<>();

        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearCart(Context context) {
        saveCart(context, new ArrayList<>());
    }

    private static void saveCart(Context context, List<Product> cart) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cart);
        editor.putString(KEY_CART, json);
        editor.apply();
    }

    public static int getCartCount(Context context) {
        return getCart(context).size();
    }
}
