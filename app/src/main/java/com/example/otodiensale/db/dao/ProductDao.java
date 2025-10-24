package com.example.otodiensale.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.otodiensale.db.entities.ProductEntity;
import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<ProductEntity> items);

    @Query("SELECT * FROM products")
    List<ProductEntity> getAll();
}
