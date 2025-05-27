package com.example.elementia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_welcome extends AppCompatActivity {

    private TextView welcomeTitle;
    private TextView subtitle;
    private ImageButton continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupClickListeners();
        startAnimations();
    }

    private void initializeViews() {
        welcomeTitle = findViewById(R.id.welcomeTitle);
        subtitle = findViewById(R.id.subtitle);
        continueButton = findViewById(R.id.continueButton);
    }

    private void setupClickListeners() {
        continueButton.setOnClickListener(v -> {
            // Navigate directly to elements activity
            navigateToElementsActivity();
        });
    }

    private void startAnimations() {
        // Title animation - slide up and fade in
        welcomeTitle.setAlpha(0f);
        welcomeTitle.setTranslationY(50f);
        welcomeTitle.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(800)
                .setStartDelay(200)
                .start();

        // Subtitle animation
        subtitle.setAlpha(0f);
        subtitle.setTranslationY(30f);
        subtitle.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(800)
                .setStartDelay(400)
                .start();

        // Button animation
        continueButton.setAlpha(0f);
        continueButton.setTranslationY(30f);
        continueButton.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(800)
                .setStartDelay(600)
                .start();
    }

    private void navigateToElementsActivity() {
        Intent intent = new Intent(activity_welcome.this, activity_elements.class);
        startActivity(intent);
        finish(); // Close welcome activity

        // Smooth transition
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        // Prevent going back from welcome screen
        super.onBackPressed();
    }
}