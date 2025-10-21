package com.example.evsaleapp.data.api;

import com.example.evsaleapp.data.model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<List<Product>> getAllProducts();
}
