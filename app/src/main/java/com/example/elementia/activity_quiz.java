package com.example.elementia;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class activity_quiz extends AppCompatActivity {

    // UI Elements
    private TextView scoreTextView, highScoreTextView, questionNumberTextView, questionTextView, resultTextView;
    private TextView answerTextView1, answerTextView2, answerTextView3, answerTextView4;
    private CardView answerCardView1, answerCardView2, answerCardView3, answerCardView4;
    private ImageView heartImage1, heartImage2, heartImage3;
    private ProgressBar progressBar;
    private Button quitButton, nextButton;
    private BottomNavigationView bottomNavigationView;

    // Game State
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int lives = 3;
    private int highScore = 0;
    private boolean questionAnswered = false;

    // Questions
    private List<QuizQuestion> questions;

    // SharedPreferences for high score
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "QuizPrefs";
    private static final String HIGH_SCORE_KEY = "high_score";

    private String currentDifficulty = activity_difficulty.DIFFICULTY_EASY; // Default

    private Handler autoAdvanceHandler;
    private Runnable autoAdvanceRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get difficulty from intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(activity_difficulty.DIFFICULTY_EXTRA)) {
            currentDifficulty = intent.getStringExtra(activity_difficulty.DIFFICULTY_EXTRA);
        }

        initializeViews();
        loadHighScore();
        setupQuestions(); // This will now use difficulty
        setupClickListeners();
        setupBottomNavigation();
        startQuiz();
    }

    private void initializeViews() {
        // Score and lives
        scoreTextView = findViewById(R.id.scoreTextView);
        highScoreTextView = findViewById(R.id.highScoreTextView);
        heartImage1 = findViewById(R.id.heartImage1);
        heartImage2 = findViewById(R.id.heartImage2);
        heartImage3 = findViewById(R.id.heartImage3);
        progressBar = findViewById(R.id.progressBar);

        // Question elements
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);

        // Answer cards and texts
        answerCardView1 = findViewById(R.id.answerCardView1);
        answerCardView2 = findViewById(R.id.answerCardView2);
        answerCardView3 = findViewById(R.id.answerCardView3);
        answerCardView4 = findViewById(R.id.answerCardView4);

        answerTextView1 = findViewById(R.id.answerTextView1);
        answerTextView2 = findViewById(R.id.answerTextView2);
        answerTextView3 = findViewById(R.id.answerTextView3);
        answerTextView4 = findViewById(R.id.answerTextView4);

        // Buttons
        quitButton = findViewById(R.id.quitButton);
        nextButton = findViewById(R.id.nextButton);

        // Bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void loadHighScore() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0);
        updateScoreDisplay();
    }

    private void saveHighScore() {
        if (score > highScore) {
            highScore = score;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(HIGH_SCORE_KEY, highScore);
            editor.apply();
            updateScoreDisplay();
        }
    }

    private void setupQuestions() {
        questions = new ArrayList<>();

        // Easy Questions (Basic symbols and common elements)
        if (currentDifficulty.equals(activity_difficulty.DIFFICULTY_EASY)) {
            questions.add(new QuizQuestion("What is the chemical symbol for Gold?",
                    new String[]{"Au", "Ag", "Gd", "Go"}, 0));
            questions.add(new QuizQuestion("What is the chemical symbol for Iron?",
                    new String[]{"Ir", "Fe", "In", "I"}, 1));
            questions.add(new QuizQuestion("What is the chemical symbol for Silver?",
                    new String[]{"Si", "S", "Ag", "Al"}, 2));
            questions.add(new QuizQuestion("What is the chemical symbol for Sodium?",
                    new String[]{"So", "Na", "N", "S"}, 1));
            questions.add(new QuizQuestion("Which element is essential for breathing?",
                    new String[]{"Nitrogen", "Oxygen", "Carbon", "Hydrogen"}, 1));
            questions.add(new QuizQuestion("Which element is used in balloons to make them float?",
                    new String[]{"Hydrogen", "Oxygen", "Helium", "Nitrogen"}, 2));
            questions.add(new QuizQuestion("Which element is essential for strong bones?",
                    new String[]{"Iron", "Calcium", "Magnesium", "Potassium"}, 1));
            questions.add(new QuizQuestion("What is water made of?",
                    new String[]{"Hydrogen and Oxygen", "Hydrogen and Carbon", "Oxygen and Carbon", "Nitrogen and Oxygen"}, 0));
            questions.add(new QuizQuestion("Which element makes plants green?",
                    new String[]{"Iron", "Magnesium", "Calcium", "Potassium"}, 1));
            questions.add(new QuizQuestion("What is the most common gas in Earth's atmosphere?",
                    new String[]{"Oxygen", "Carbon dioxide", "Nitrogen", "Argon"}, 2));
        }

        // Medium Questions (Properties, groups, and some advanced concepts)
        else if (currentDifficulty.equals(activity_difficulty.DIFFICULTY_MEDIUM)) {
            questions.add(new QuizQuestion("Which element is the lightest?",
                    new String[]{"Helium", "Hydrogen", "Lithium", "Carbon"}, 1));
            questions.add(new QuizQuestion("Which group do Noble Gases belong to?",
                    new String[]{"Group 17", "Group 18", "Group 1", "Group 2"}, 1));
            questions.add(new QuizQuestion("What are elements in Group 1 called?",
                    new String[]{"Noble gases", "Halogens", "Alkali metals", "Alkaline earth metals"}, 2));
            questions.add(new QuizQuestion("In the mnemonic 'Happy Cats Never Offer Fish', what does 'H' represent?",
                    new String[]{"Helium", "Hydrogen", "Hafnium", "Holmium"}, 1));
            questions.add(new QuizQuestion("Which element is commonly used in smoke detectors?",
                    new String[]{"Uranium", "Plutonium", "Americium", "Radium"}, 2));
            questions.add(new QuizQuestion("Which element has the chemical symbol 'Pb'?",
                    new String[]{"Phosphorus", "Lead", "Platinum", "Polonium"}, 1));
            questions.add(new QuizQuestion("What is the most abundant element in the universe?",
                    new String[]{"Oxygen", "Hydrogen", "Carbon", "Helium"}, 1));
            questions.add(new QuizQuestion("Which element is essential for photosynthesis?",
                    new String[]{"Nitrogen", "Phosphorus", "Carbon", "Sulfur"}, 2));
            questions.add(new QuizQuestion("Which mnemonic helps remember Noble Gases?",
                    new String[]{"Happy Cats Never Offer Fish", "He Never Asked King Xerxes", "Little Naomi Keeps Rubbing", "Be My Cat She's Big"}, 1));
            questions.add(new QuizQuestion("Which element is liquid at room temperature?",
                    new String[]{"Gallium", "Mercury", "Cesium", "Francium"}, 1));
        }

        // Hard Questions (Advanced properties, rare elements, complex concepts)
        else if (currentDifficulty.equals(activity_difficulty.DIFFICULTY_HARD)) {
            questions.add(new QuizQuestion("What is the densest naturally occurring element?",
                    new String[]{"Lead", "Gold", "Platinum", "Osmium"}, 3));
            questions.add(new QuizQuestion("Which element has the highest melting point?",
                    new String[]{"Carbon", "Tungsten", "Iron", "Titanium"}, 1));
            questions.add(new QuizQuestion("Which element is liquid at room temperature besides Mercury?",
                    new String[]{"Gallium", "Bromine", "Cesium", "Francium"}, 1));
            questions.add(new QuizQuestion("What does the 'lanthanide' series refer to?",
                    new String[]{"Transition metals", "Rare earth elements", "Noble gases", "Halogens"}, 1));
            questions.add(new QuizQuestion("Which element is named after a planet?",
                    new String[]{"Plutonium", "Neptunium", "Uranium", "All of the above"}, 3));
            questions.add(new QuizQuestion("Which element has the chemical symbol 'W'?",
                    new String[]{"Wolfram", "Tungsten", "Both A and B", "Neither"}, 2));
            questions.add(new QuizQuestion("What is the rarest naturally occurring element?",
                    new String[]{"Francium", "Astatine", "Promethium", "Technetium"}, 1));
            questions.add(new QuizQuestion("Which element was first discovered in the sun?",
                    new String[]{"Hydrogen", "Helium", "Neon", "Argon"}, 1));
            questions.add(new QuizQuestion("Which synthetic element is named after Einstein?",
                    new String[]{"Einsteinium", "Fermium", "Nobelium", "Mendelevium"}, 0));
            questions.add(new QuizQuestion("What is the atomic number of Carbon?",
                    new String[]{"4", "6", "8", "12"}, 1));
        }

        // Shuffle questions for randomness
        Collections.shuffle(questions);

        // Take only 10 questions
        if (questions.size() > 10) {
            questions = questions.subList(0, 10);
        }
    }

    private void setupClickListeners() {
        // Answer click listeners
        answerCardView1.setOnClickListener(v -> selectAnswer(0));
        answerCardView2.setOnClickListener(v -> selectAnswer(1));
        answerCardView3.setOnClickListener(v -> selectAnswer(2));
        answerCardView4.setOnClickListener(v -> selectAnswer(3));

        // Quit button
        quitButton.setOnClickListener(v -> showQuitDialog());

        // Next button
        nextButton.setOnClickListener(v -> nextQuestion());
    }


    private void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        lives = 3;
        questionAnswered = false;
        updateDisplay();
        showQuestion();
    }

    private void showQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            endQuiz();
            return;
        }

        QuizQuestion question = questions.get(currentQuestionIndex);
        questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.size());
        questionTextView.setText(question.getQuestion());

        // Set answer options with A, B, C, D labels
        String[] answers = question.getAnswers();
        answerTextView1.setText("A) " + answers[0]);
        answerTextView2.setText("B) " + answers[1]);
        answerTextView3.setText("C) " + answers[2]);
        answerTextView4.setText("D) " + answers[3]);

        // Reset card colors and enable clicking
        resetAnswerCards();
        questionAnswered = false;
        resultTextView.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);

        // Update progress bar
        progressBar.setProgress(currentQuestionIndex + 1);
    }

    private void selectAnswer(int selectedAnswer) {
        if (questionAnswered) return;

        questionAnswered = true;
        QuizQuestion question = questions.get(currentQuestionIndex);
        boolean isCorrect = selectedAnswer == question.getCorrectAnswer();

        // Show result
        if (isCorrect) {
            score += 10;
            resultTextView.setText("Correct! +10 points");
            resultTextView.setTextColor(getColor(android.R.color.holo_green_dark));
            highlightCorrectAnswer(selectedAnswer);
        } else {
            lives--;
            resultTextView.setText("Wrong! The correct answer is " +
                    getAnswerLabel(question.getCorrectAnswer()) + ") " +
                    question.getAnswers()[question.getCorrectAnswer()]);
            resultTextView.setTextColor(getColor(android.R.color.holo_red_dark));
            highlightWrongAnswer(selectedAnswer);
            highlightCorrectAnswer(question.getCorrectAnswer());
            updateLives();

            if (lives <= 0) {
                endQuiz();
                return;
            }
        }

        resultTextView.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        updateScoreDisplay();

        // Auto-advance after 2 seconds if not the last question
        if (currentQuestionIndex < questions.size() - 1) {
            autoAdvanceHandler = new Handler();
            autoAdvanceRunnable = () -> nextQuestion();
            autoAdvanceHandler.postDelayed(autoAdvanceRunnable, 2000);
        }
    }

    private String getAnswerLabel(int index) {
        switch (index) {
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            default: return "A";
        }
    }

    private void highlightCorrectAnswer(int correctAnswer) {
        CardView correctCard = getAnswerCard(correctAnswer);
        correctCard.setCardBackgroundColor(getColor(android.R.color.holo_green_light));
    }

    private void highlightWrongAnswer(int wrongAnswer) {
        CardView wrongCard = getAnswerCard(wrongAnswer);
        wrongCard.setCardBackgroundColor(getColor(android.R.color.holo_red_light));
    }

    private CardView getAnswerCard(int index) {
        switch (index) {
            case 0: return answerCardView1;
            case 1: return answerCardView2;
            case 2: return answerCardView3;
            case 3: return answerCardView4;
            default: return answerCardView1;
        }
    }

    private void resetAnswerCards() {
        answerCardView1.setCardBackgroundColor(getColor(android.R.color.white));
        answerCardView2.setCardBackgroundColor(getColor(android.R.color.white));
        answerCardView3.setCardBackgroundColor(getColor(android.R.color.white));
        answerCardView4.setCardBackgroundColor(getColor(android.R.color.white));

        answerCardView1.setClickable(true);
        answerCardView2.setClickable(true);
        answerCardView3.setClickable(true);
        answerCardView4.setClickable(true);
    }

    // method to cancel auto-advance nag auto advance siya ng isang question
    private void nextQuestion() {
        // Cancel any pending auto-advance
        if (autoAdvanceHandler != null && autoAdvanceRunnable != null) {
            autoAdvanceHandler.removeCallbacks(autoAdvanceRunnable);
        }
        currentQuestionIndex++;
        showQuestion();
    }

    private void updateDisplay() {
        updateScoreDisplay();
        updateLives();
    }

    private void updateScoreDisplay() {
        scoreTextView.setText("Score: " + score);
        highScoreTextView.setText("High Score: " + highScore);
    }

    private void updateLives() {
        heartImage1.setImageResource(lives >= 1 ? R.drawable.ic_heart_filled : R.drawable.ic_heart_empty);
        heartImage2.setImageResource(lives >= 2 ? R.drawable.ic_heart_filled : R.drawable.ic_heart_empty);
        heartImage3.setImageResource(lives >= 3 ? R.drawable.ic_heart_filled : R.drawable.ic_heart_empty);
    }

    private void endQuiz() {
        saveHighScore();

        String message;
        if (lives <= 0) {
            message = "Game Over!\n\nDifficulty: " + currentDifficulty.toUpperCase() +
                    "\nFinal Score: " + score + "\nHigh Score: " + highScore;
        } else {
            message = "Quiz Complete!\n\nDifficulty: " + currentDifficulty.toUpperCase() +
                    "\nFinal Score: " + score + "\nHigh Score: " + highScore +
                    "\n\nWell done!";
        }

        new AlertDialog.Builder(this)
                .setTitle("Quiz Finished")
                .setMessage(message)
                .setPositiveButton("Play Again", (dialog, which) -> {
                    // Go back to difficulty selection for another round
                    Intent intent = new Intent(activity_quiz.this, activity_difficulty.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Back to Challenge", (dialog, which) -> {
                    Intent intent = new Intent(activity_quiz.this, activity_challenge.class);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        setupQuestions(); // Reshuffle questions
        startQuiz();
    }

    private void showQuitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Quit Quiz")
                .setMessage("Are you sure you want to quit? Your progress will be lost.")
                .setPositiveButton("Yes, Quit", (dialog, which) -> {
                    // Go back to difficulty selection
                    Intent intent = new Intent(activity_quiz.this, activity_difficulty.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }



    // QuizQuestion class to hold question data
    private static class QuizQuestion {
        private String question;
        private String[] answers;
        private int correctAnswer;

        public QuizQuestion(String question, String[] answers, int correctAnswer) {
            this.question = question;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getAnswers() {
            return answers;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }
    }
    private void setupBottomNavigation() {


        // Set the selected item
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
                    Intent intent = new Intent(activity_quiz.this, activity_elements.class);
                    startActivity(intent);
                    finish();
                    return true;

                } else if (itemId == R.id.challege) {
                    Intent intent = new Intent(activity_quiz.this, activity_challenge.class);
                    startActivity(intent);
                    finish();
                    return true;
                }

                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        showQuitDialog();
    }

}