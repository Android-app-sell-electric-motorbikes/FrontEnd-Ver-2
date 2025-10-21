package com.example.evsaleapp.ui.customer.billing;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.evsaleapp.R;
import com.example.evsaleapp.models.Product;
import com.example.evsaleapp.utils.CartManager;

import java.util.List;

public class BillingActivity extends AppCompatActivity {

    private TextView textTotalAmount;
    private EditText editAddress, editPhone;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        textTotalAmount = findViewById(R.id.textTotalAmount);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        btnPay = findViewById(R.id.btnPay);

        double total = calculateTotal();
        textTotalAmount.setText(String.format("Tổng thanh toán: %,.0f VND", total));

        btnPay.setOnClickListener(v -> {
            if (editAddress.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_LONG).show();
                CartManager.clearCart(this);
                finish();
            }
        });
    }

    private double calculateTotal() {
        List<Product> cart = CartManager.getCart(this);
        double total = 0;
        for (Product p : cart) total += p.getPrice();
        return total;
    }
}
