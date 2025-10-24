package com.example.otodiensale.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.otodiensale.db.dao.ProductDao;
import com.example.otodiensale.db.entities.ProductEntity;
import com.example.otodiensale.db.entities.CartEntity;
import com.example.otodiensale.db.dao.CartDao;

@Database(entities = {ProductEntity.class, CartEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract CartDao cartDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx, AppDatabase.class, "otodiensale_db").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
