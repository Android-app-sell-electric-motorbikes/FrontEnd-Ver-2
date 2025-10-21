package com.example.evsaleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.evsaleapp.R;
import com.example.evsaleapp.ui.map.MapActivity;

public class MainActivity extends AppCompatActivity {

    Button btnViewMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewMap = findViewById(R.id.btnViewMap);

        btnViewMap.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        });
    }
}
