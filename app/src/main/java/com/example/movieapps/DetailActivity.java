package com.example.movieapps;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapps.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ambil referensi ke View
        ImageView imageView = findViewById(R.id.imageView);
        TextView textViewNama = findViewById(R.id.textViewNama);
        TextView textViewDetail = findViewById(R.id.textViewDetail);
        TextView textViewGenre = findViewById(R.id.textViewGenre);
        TextView textViewRating = findViewById(R.id.textViewRating);

        // Ambil data dari Intent
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        int imageResId = getIntent().getIntExtra("imageResId", -1);
        String genre = getIntent().getStringExtra("genre");
        String rating = getIntent().getStringExtra("rating");

        // Set data ke View
        textViewNama.setText("Judul: "+name);
        textViewDetail.setText(description);
        imageView.setImageResource(imageResId);
        textViewGenre.setText("Genre: "+genre);
        textViewRating.setText(String.valueOf("Rating: "+rating));
    }
}
