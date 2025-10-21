package com.example.evsaleapp.data.repository;

import com.example.evsaleapp.data.api.ApiClient;
import com.example.evsaleapp.data.api.ApiService;
import com.example.evsaleapp.data.model.Product;

import java.util.List;
import retrofit2.Call;

public class ProductRepository {
    private ApiService apiService;

    public ProductRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public Call<List<Product>> getProducts() {
        return apiService.getAllProducts();
    }
}
