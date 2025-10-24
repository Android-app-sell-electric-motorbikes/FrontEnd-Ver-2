package com.example.otodiensale.ui.product;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.otodiensale.api.ApiClient;
import com.example.otodiensale.api.ApiService;
import com.example.otodiensale.data.model.Product;
import com.example.otodiensale.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ProgressBar pb;
    private ProductAdapter adapter;
    private ApiService api;
    private int page = 0, size = 20;
    private boolean isLoading = false, isLast = false;
    private String query = null, sort = null;

    @Override protected void onCreate(@Nullable Bundle s) {
        super.onCreate(s); setContentView(R.layout.activity_product_list);
        rv = findViewById(R.id.recycler);
        pb = findViewById(R.id.progress);
        adapter = new ProductAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        api = ApiClient.getRetrofit().create(ApiService.class);
        setupScroll();
        loadNext();
    }

    private void setupScroll() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override public void onScrolled(RecyclerView r, int dx, int dy){
                LinearLayoutManager lm = (LinearLayoutManager) r.getLayoutManager();
                if (!isLoading && !isLast && lm != null && lm.findLastVisibleItemPosition() >= adapter.getItemCount() - 5) {
                    loadNext();
                }
            }
        });
    }

    private void loadNext() {
        isLoading = true; pb.setVisibility(View.VISIBLE);
        api.getProducts(page, size, query, sort).enqueue(new Callback<List<Product>>() {
            @Override public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                pb.setVisibility(View.GONE); isLoading = false;
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> list = response.body();
                    adapter.append(list);
                    if (list.size() < size) isLast = true; else page++;
                }
            }
            @Override public void onFailure(Call<List<Product>> call, Throwable t) { pb.setVisibility(View.GONE); isLoading = false; }
        });
    }
}
