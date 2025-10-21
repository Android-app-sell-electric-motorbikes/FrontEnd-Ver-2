package com.example.evsaleapp.ui.customer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.evsaleapp.R;
import com.example.evsaleapp.data.api.ApiClient;
import com.example.evsaleapp.data.api.ApiService;
import com.example.evsaleapp.data.model.Product;
import com.example.evsaleapp.utils.CartManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView txtName, txtPrice, txtDescription;
    private Button btnAddToCart;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.imgProductDetail);
        txtName = findViewById(R.id.txtProductName);
        txtPrice = findViewById(R.id.txtProductPrice);
        txtDescription = findViewById(R.id.txtProductDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        productId = getIntent().getIntExtra("product_id", -1);
        loadProductDetail(productId);

        btnAddToCart.setOnClickListener(v -> addProductToCart());
    }

    private void loadProductDetail(int id) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getProductById(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    txtName.setText(product.getName());
                    txtPrice.setText(String.format("%,.0f VND", product.getPrice()));
                    txtDescription.setText(product.getDescription());

                    Glide.with(ProductDetailActivity.this)
                            .load(product.getImageUrl())
                            .placeholder(R.drawable.ic_bike)
                            .into(imgProduct);
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addProductToCart() {
        Product product = new Product(productId,
                txtName.getText().toString(),
                Double.parseDouble(txtPrice.getText().toString().replaceAll("[^\\d]", "")),
                txtDescription.getText().toString(),
                ""); // ảnh sẽ lấy từ API

        CartManager.addToCart(this, product);
        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
}
