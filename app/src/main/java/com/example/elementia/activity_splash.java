package com.example.elementia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_splash extends AppCompatActivity {

    private TextView welcomeText, appNameText;
    private ImageView logoImage;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        startAnimations();

        // Navigate to welcome activity after 3 seconds
        handler = new Handler();
        handler.postDelayed(() -> {
            navigateToWelcomeScreen();
        }, 3000);
    }

    private void navigateToWelcomeScreen() {
        Intent intent = new Intent(activity_splash.this, activity_welcome.class);
        startActivity(intent);
        finish();

        // Add smooth transition
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void initializeViews() {
        welcomeText = findViewById(R.id.welcomeText);
        appNameText = findViewById(R.id.appNameText);
        logoImage = findViewById(R.id.logoImage);
    }

    private void startAnimations() {
        // Logo animation
        if (logoImage != null) {
            logoImage.setAlpha(0f);
            logoImage.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(1000)
                    .setStartDelay(200)
                    .start();
        }

        // Welcome text animation
        if (welcomeText != null) {
            welcomeText.setAlpha(0f);
            welcomeText.animate()
                    .alpha(1f)
                    .setDuration(1000)
                    .setStartDelay(500)
                    .start();
        }

        // App name text animation
        if (appNameText != null) {
            appNameText.setAlpha(0f);
            appNameText.animate()
                    .alpha(1f)
                    .setDuration(1000)
                    .setStartDelay(1000)
                    .start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}