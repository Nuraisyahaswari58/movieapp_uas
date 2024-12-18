package com.example.movieapps;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tambahkan kode untuk menangani klik tombol "Top Film" di sini
        findViewById(R.id.btnfilm).setOnClickListener(v -> {
            // Tambahkan kode untuk membuka halaman daftar film di sini
            Intent intent = new Intent(MainActivity.this, MainAplikasi.class);
            startActivity(intent);
        });

        // Tambahkan kode untuk menangani klik tombol "About" di sini
        findViewById(R.id.btnabout).setOnClickListener(v -> {
            // Tambahkan kode untuk membuka halaman tentang aplikasi di sini
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        });
        findViewById(R.id.btnreview).setOnClickListener(v -> {
            // Tambahkan kode untuk membuka halaman tentang aplikasi di sini
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }
}