package com.example.evsaleapp.ui.customer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evsaleapp.R;
import com.example.evsaleapp.models.Product;
import com.example.evsaleapp.ui.customer.adapters.CartAdapter;
import com.example.evsaleapp.ui.customer.billing.BillingActivity;
import com.example.evsaleapp.utils.CartManager;

import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.OnCartChangedListener {

    private RecyclerView recyclerView;
    private TextView txtTotalAmount;
    private Button btnCheckout;
    private CartAdapter adapter;
    private List<Product> cartList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCart);
        txtTotalAmount = view.findViewById(R.id.txtTotalAmount);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadCart();

        btnCheckout.setOnClickListener(v -> {
            if (!cartList.isEmpty()) {
                startActivity(new Intent(getContext(), BillingActivity.class));
            }
        });

        return view;
    }

    private void loadCart() {
        cartList = CartManager.getCart(getContext());
        adapter = new CartAdapter(getContext(), cartList, this);
        recyclerView.setAdapter(adapter);
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (Product p : cartList) total += p.getPrice();
        txtTotalAmount.setText(String.format("Tổng: %,.0f VND", total));
    }

    @Override
    public void onCartUpdated() {
        loadCart();
    }
}
