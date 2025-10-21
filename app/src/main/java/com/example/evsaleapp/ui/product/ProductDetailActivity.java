package com.example.evsaleapp.ui.product;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.evsaleapp.R;
import com.example.evsaleapp.utils.CartManager;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView txtName, txtPrice, txtDescription;
    Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.imgProductDetail);
        txtName = findViewById(R.id.txtProductNameDetail);
        txtPrice = findViewById(R.id.txtProductPriceDetail);
        txtDescription = findViewById(R.id.txtProductDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Nhận dữ liệu từ intent
        int id = getIntent().getIntExtra("product_id", 0);
        String name = getIntent().getStringExtra("product_name");
        double price = getIntent().getDoubleExtra("product_price", 0);
        String desc = getIntent().getStringExtra("product_desc");
        String image = getIntent().getStringExtra("product_image");

        txtName.setText(name);
        txtPrice.setText(price + " VND");
        txtDescription.setText(desc);
        Glide.with(this).load(image).into(imgProduct);

        btnAddToCart.setOnClickListener(v -> {
            CartManager.getInstance(this).addToCart(id, name, price, image);
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });
    }
}
