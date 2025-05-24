package com.example.elementia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class activity_challenge extends AppCompatActivity {
    private LinearLayout quizButtonLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challege);

        // Initialize views
        initializeViews();

        // Setup quiz button click listener
        setupQuizButton();

        // Initialize bottom navigation
        setupBottomNavigation();
    }

    private void initializeViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        quizButtonLayout = findViewById(R.id.quizButton);
    }

    private void setupQuizButton() {
        // Set click listener on the LinearLayout (which acts as the quiz button)
        quizButtonLayout.setOnClickListener(v -> {
            Intent intent = new Intent(activity_challenge.this, activity_quiz.class);
            startActivity(intent);
        });
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.elements) {
                    // Navigate to Elements activity
                    Intent intent = new Intent(activity_challenge.this, activity_elements.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (itemId == R.id.challege) {
                    // Already on Challenge page
                    return true;
                }

                return false;
            }
        });

        // Set Challenge as selected
        bottomNavigationView.setSelectedItemId(R.id.challege);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ensure Challenge is selected when on challenge activity
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.challege);
        }
    }
}