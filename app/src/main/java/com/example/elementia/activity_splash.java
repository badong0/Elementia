package com.example.elementia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

        // Navigate to main activity after 3 seconds
        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(activity_splash.this, activity_elements.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    private void initializeViews() {
        welcomeText = findViewById(R.id.welcomeText);
        appNameText = findViewById(R.id.appNameText);
        logoImage = findViewById(R.id.logoImage);
    }

    private void startAnimations() {
        // Logo fade in and scale animation
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        logoImage.startAnimation(logoAnimation);

        // Welcome text slide up animation
        Animation welcomeAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_animation);
        welcomeText.startAnimation(welcomeAnimation);

        // App name text fade in animation (delayed)
        Animation appNameAnimation = AnimationUtils.loadAnimation(this, R.anim.app_name_animation);
        handler = new Handler();
        handler.postDelayed(() -> {
            appNameText.setVisibility(android.view.View.VISIBLE);
            appNameText.startAnimation(appNameAnimation);
        }, 800);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
    private void startLoadingDotsAnimation() {
        View dot1 = findViewById(R.id.dot1);
        View dot2 = findViewById(R.id.dot2);
        View dot3 = findViewById(R.id.dot3);

        Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse_animation);

        handler.postDelayed(() -> dot1.startAnimation(pulseAnimation), 1500);
        handler.postDelayed(() -> dot2.startAnimation(pulseAnimation), 1700);
        handler.postDelayed(() -> dot3.startAnimation(pulseAnimation), 1900);
    }
}