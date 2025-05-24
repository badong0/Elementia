package com.example.elementia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class activity_difficulty extends AppCompatActivity {

    private CardView easyCard, mediumCard, hardCard;
    private BottomNavigationView bottomNavigationView;

    // Difficulty constants
    public static final String DIFFICULTY_EXTRA = "difficulty_level";
    public static final String DIFFICULTY_EASY = "easy";
    public static final String DIFFICULTY_MEDIUM = "medium";
    public static final String DIFFICULTY_HARD = "hard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        try {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_difficulty);


            // // DOUBLE CHECK THIS, KUNG GUMANA PAG WALA, COMMENT, PERO PAG NAG ERROR, UNCOMMENT TO
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

        } catch (Exception e) {
            Log.e("DifficultyDebug", "CRASH in onCreate: " + e.getMessage());
            e.printStackTrace();
        }
        // Comment out everything else for now

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });

    initializeViews();
    setupDifficultyButtons();
    setupBottomNavigation();

    }

    private void initializeViews() {
            easyCard = findViewById(R.id.easyCard);
            mediumCard = findViewById(R.id.mediumCard);
            hardCard = findViewById(R.id.hardCard);
            bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void setupDifficultyButtons() {
        easyCard.setOnClickListener(v -> startQuizWithDifficulty(DIFFICULTY_EASY));
        mediumCard.setOnClickListener(v -> startQuizWithDifficulty(DIFFICULTY_MEDIUM));
        hardCard.setOnClickListener(v -> startQuizWithDifficulty(DIFFICULTY_HARD));
    }

    private void startQuizWithDifficulty(String difficulty) {
        Intent intent = new Intent(activity_difficulty.this, activity_quiz.class);
        intent.putExtra(DIFFICULTY_EXTRA, difficulty);
        startActivity(intent);
        finish(); // Close difficulty selection
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (bottomNavigationView != null) {
//            bottomNavigationView.setSelectedItemId(R.id.challege);
//        }
//    }

//    @Override
//    public void onBackPressed() {
//        // Go back to challenge activity
//        Intent intent = new Intent(activity_difficulty.this, activity_challenge.class);
//        startActivity(intent);
//        finish();
//        super.onBackPressed();
//    }

    private void setupBottomNavigation() {

        bottomNavigationView.setSelectedItemId(R.id.challege);

        // Force UI refresh
        bottomNavigationView.post(() -> {
            bottomNavigationView.invalidate();
            bottomNavigationView.requestLayout();
        });

        // Set the listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.elements) {
                    Intent intent = new Intent(activity_difficulty.this, activity_elements.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (itemId == R.id.challege) {
                    Intent intent = new Intent(activity_difficulty.this, activity_challenge.class);
                    startActivity(intent);
                    finish();
                    return true;
                }

                return false;
            }
        });
    }
}