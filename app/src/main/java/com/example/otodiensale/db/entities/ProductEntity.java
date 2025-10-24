package com.example.otodiensale.db.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductEntity {
    @PrimaryKey
    public long id;
    public String name;
    public String description;
    public String imageUrl;
    public double price;
    public String category;
}
