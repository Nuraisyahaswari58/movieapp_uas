package com.example.movieapps;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // Views
        EditText etReviewInput = findViewById(R.id.etReviewInput);
        Button btnSubmitReview = findViewById(R.id.btnSubmitReview);
        LinearLayout llReviewsContainer = findViewById(R.id.llReviewsContainer);

        // Handle Submit Button Click
        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = etReviewInput.getText().toString().trim();

                if (review.isEmpty()) {
                    Toast.makeText(ReviewActivity.this, "Review tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add Review to the List
                TextView newReview = new TextView(ReviewActivity.this);
                newReview.setText(review);
                newReview.setTextSize(16);
                newReview.setTextColor(getResources().getColor(android.R.color.black));
                newReview.setPadding(8, 8, 8, 8);

                // Add to container
                llReviewsContainer.addView(newReview);

                // Clear the input field
                etReviewInput.setText("");

                // Toast Message
                Toast.makeText(ReviewActivity.this, "Review berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
