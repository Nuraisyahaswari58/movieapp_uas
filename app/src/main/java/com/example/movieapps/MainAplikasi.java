package com.example.movieapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAplikasi extends AppCompatActivity {

    private final HashMap<String, String> movieDescriptionMap = new HashMap<>();
    private final HashMap<String, Integer> movieImageMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aplikasi);

        // Inisialisasi data film
        initializeMovieData();

        // Ambil ListView
        ListView lvMovies = findViewById(R.id.lvdatafilm);

        // Ambil daftar nama film
        ArrayList<String> movieList = new ArrayList<>(movieDescriptionMap.keySet());

        // Pasang custom adapter langsung
        lvMovies.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return movieList.size();
            }

            @Override
            public Object getItem(int position) {
                return movieList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    // Buat tata letak item di dalam Java
                    LinearLayout itemLayout = new LinearLayout(MainAplikasi.this);
                    itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                    itemLayout.setPadding(16, 16, 16, 16);

                    // Gambar film
                    ImageView ivMoviePoster = new ImageView(MainAplikasi.this);
                    ivMoviePoster.setId(View.generateViewId());
                    ivMoviePoster.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
                    ivMoviePoster.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    itemLayout.addView(ivMoviePoster);

                    // Judul film
                    TextView tvMovieTitle = new TextView(MainAplikasi.this);
                    tvMovieTitle.setId(View.generateViewId());
                    tvMovieTitle.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    tvMovieTitle.setPadding(16, 0, 0, 0);
                    tvMovieTitle.setTextSize(16);
                    itemLayout.addView(tvMovieTitle);

                    convertView = itemLayout;
                    convertView.setTag(new ViewHolder(ivMoviePoster, tvMovieTitle));
                }

                // Isi data ke elemen
                ViewHolder holder = (ViewHolder) convertView.getTag();
                String movieTitle = movieList.get(position);
                holder.tvMovieTitle.setText(movieTitle);
                holder.ivMoviePoster.setImageResource(movieImageMap.get(movieTitle));

                return convertView;
            }

            // ViewHolder pattern untuk efisiensi
            class ViewHolder {
                final ImageView ivMoviePoster;
                final TextView tvMovieTitle;

                ViewHolder(ImageView ivMoviePoster, TextView tvMovieTitle) {
                    this.ivMoviePoster = ivMoviePoster;
                    this.tvMovieTitle = tvMovieTitle;
                }
            }
        });

        // Set klik listener untuk item
        lvMovies.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMovie = movieList.get(position);

            // Kirim data film ke DetailActivity
            Intent intent = new Intent(MainAplikasi.this, DetailActivity.class);
            intent.putExtra("name", selectedMovie);
            intent.putExtra("description", movieDescriptionMap.get(selectedMovie));
            intent.putExtra("imageResId", movieImageMap.get(selectedMovie));
            startActivity(intent);
        });
    }

    // Inisialisasi data film
    private void initializeMovieData() {
        movieDescriptionMap.put("Ipar Adalah Maut", getString(R.string.desc_ipar));
        movieImageMap.put("Ipar Adalah Maut", R.drawable.iparmaut);

        movieDescriptionMap.put("Kang Mak", getString(R.string.desc_kangmak));
        movieImageMap.put("Kang Mak", R.drawable.kangmak);

        movieDescriptionMap.put("Badarawuhi", getString(R.string.desc_badarawuhi));
        movieImageMap.put("Badarawuhi", R.drawable.badarawuhi);

        movieDescriptionMap.put("Sekawan", getString(R.string.desc_sekawan));
        movieImageMap.put("Sekawan", R.drawable.sekawan);

        movieDescriptionMap.put("Ancika", getString(R.string.desc_ancika));
        movieImageMap.put("Ancika", R.drawable.ancika);

        movieDescriptionMap.put("Home", getString(R.string.desc_home));
        movieImageMap.put("Home", R.drawable.home);

        movieDescriptionMap.put("Love", getString(R.string.desc_love));
        movieImageMap.put("Love", R.drawable.love);

        movieDescriptionMap.put("Laura", getString(R.string.desc_laura));
        movieImageMap.put("Laura", R.drawable.laura);
    }
}
