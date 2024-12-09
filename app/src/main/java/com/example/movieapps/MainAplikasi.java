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
    private final HashMap<String, String> movieGenreMap = new HashMap<>();
    private final HashMap<String, String> movieRatingMap = new HashMap<>();


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
                    // LinearLayout utama (horizontal)
                    LinearLayout itemLayout = new LinearLayout(MainAplikasi.this);
                    itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                    itemLayout.setPadding(16, 16, 16, 16);
                    itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));

                    // Gambar film
                    ImageView ivMoviePoster = new ImageView(MainAplikasi.this);
                    ivMoviePoster.setId(View.generateViewId());
                    ivMoviePoster.setLayoutParams(new LinearLayout.LayoutParams(200, 300)); // Ukuran gambar
                    ivMoviePoster.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    itemLayout.addView(ivMoviePoster);

                    // LinearLayout untuk detail film (vertical)
                    LinearLayout detailLayout = new LinearLayout(MainAplikasi.this);
                    detailLayout.setOrientation(LinearLayout.VERTICAL);
                    detailLayout.setPadding(16, 0, 0, 0);
                    detailLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));

                    // Judul film
                    TextView tvMovieTitle = new TextView(MainAplikasi.this);
                    tvMovieTitle.setId(View.generateViewId());
                    tvMovieTitle.setTextSize(18);
                    tvMovieTitle.setTextColor(getResources().getColor(android.R.color.black));
                    detailLayout.addView(tvMovieTitle);

                    // Genre film
                    TextView tvMovieGenre = new TextView(MainAplikasi.this);
                    tvMovieGenre.setId(View.generateViewId());
                    tvMovieGenre.setTextSize(14);
                    tvMovieGenre.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    detailLayout.addView(tvMovieGenre);

                    // Rating film
                    TextView tvMovieRating = new TextView(MainAplikasi.this);
                    tvMovieRating.setId(View.generateViewId());
                    tvMovieRating.setTextSize(14);
                    tvMovieRating.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
                    detailLayout.addView(tvMovieRating);

                    // Tambahkan detailLayout ke itemLayout
                    itemLayout.addView(detailLayout);

                    // Simpan dalam convertView
                    convertView = itemLayout;
                    convertView.setTag(new ViewHolder(ivMoviePoster, tvMovieTitle, tvMovieGenre, tvMovieRating));
                }

                // Isi data ke elemen
                ViewHolder holder = (ViewHolder) convertView.getTag();
                String movieTitle = movieList.get(position);

                holder.ivMoviePoster.setImageResource(movieImageMap.get(movieTitle));
                holder.tvMovieTitle.setText(movieTitle);
                holder.tvMovieGenre.setText("Genre: " + movieGenreMap.get(movieTitle));
                holder.tvMovieRating.setText("Rating: " + movieRatingMap.get(movieTitle) + "/10");

                return convertView;
            }


            // Update ViewHolder
            class ViewHolder {
                final ImageView ivMoviePoster;
                final TextView tvMovieTitle;
                final TextView tvMovieGenre;
                final TextView tvMovieRating;

                ViewHolder(ImageView ivMoviePoster, TextView tvMovieTitle, TextView tvMovieGenre, TextView tvMovieRating) {
                    this.ivMoviePoster = ivMoviePoster;
                    this.tvMovieTitle = tvMovieTitle;
                    this.tvMovieGenre = tvMovieGenre;
                    this.tvMovieRating = tvMovieRating;
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
            intent.putExtra("genre", movieGenreMap.get(selectedMovie));
            intent.putExtra("rating", movieRatingMap.get(selectedMovie));
            startActivity(intent);
        });
    }

    // Inisialisasi data film
    private void initializeMovieData() {
        movieDescriptionMap.put("Ipar Adalah Maut", getString(R.string.desc_ipar));
        movieImageMap.put("Ipar Adalah Maut", R.drawable.iparmaut);
        movieGenreMap.put("Ipar Adalah Maut", "Drama, Romansa Gelap");
        movieRatingMap.put("Ipar Adalah Maut", "8");

        movieDescriptionMap.put("Kang Mak", getString(R.string.desc_kangmak));
        movieImageMap.put("Kang Mak", R.drawable.kangmak);
        movieGenreMap.put("Kang Mak", "Komedi, Romantis");
        movieRatingMap.put("Kang Mak", "8");

        movieDescriptionMap.put("Badarawuhi", getString(R.string.desc_badarawuhi));
        movieImageMap.put("Badarawuhi", R.drawable.badarawuhi);
        movieGenreMap.put("Badarawuhi", "Thriller, Misteri");
        movieRatingMap.put("Badarawuhi", "8");

        movieDescriptionMap.put("Sekawan", getString(R.string.desc_sekawan));
        movieImageMap.put("Sekawan", R.drawable.sekawan);
        movieGenreMap.put("Sekawan", "Thriller, Petualangan");
        movieRatingMap.put("Sekawan", "7");

        movieDescriptionMap.put("Ancika", getString(R.string.desc_ancika));
        movieImageMap.put("Ancika", R.drawable.ancika);
        movieGenreMap.put("Ancika", "Thriller, Petualangan");
        movieRatingMap.put("Ancika", "7");

        movieDescriptionMap.put("Home", getString(R.string.desc_home));
        movieImageMap.put("Home", R.drawable.home);
        movieGenreMap.put("Home", "Komedi, Keluarga");
        movieRatingMap.put("Home", "8");

        movieDescriptionMap.put("Love", getString(R.string.desc_love));
        movieImageMap.put("Love", R.drawable.love);
        movieGenreMap.put("Love", "Romansa, Drama Psikologis");
        movieRatingMap.put("Love", "8");

        movieDescriptionMap.put("Laura", getString(R.string.desc_laura));
        movieImageMap.put("Laura", R.drawable.laura);
        movieGenreMap.put("Laura", "Drama, Biografi");
        movieRatingMap.put("Laura", "8");
    }
}
