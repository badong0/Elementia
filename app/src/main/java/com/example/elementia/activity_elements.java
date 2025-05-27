package com.example.elementia;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class activity_elements extends AppCompatActivity {

    private EditText searchBar;
    private TextView welcomeMessage;
    private Switch mnemonicsSwitch;

    // Section Cards
    private CardView reactiveNonmetalCard;
    private CardView nobleGasCard;
    private CardView alkaliMetalCard;
    private CardView alkalineEarthMetalCard;
    private CardView transitionMetalP4Card;
    private CardView transitionMetalP5Card;
    private CardView transitionMetalP6Card;
    private CardView transitionMetalP7Card;
    private CardView lanthanidesCard;
    private CardView actinidesCard;
    private CardView postTransitionMetalsCard;
    private CardView metalloidsCard;
    private CardView superheavyElementsCard;

    // Element Grids
    private GridLayout reactiveNonmetalGrid;
    private GridLayout nobleGasGrid;
    private GridLayout alkaliMetalGrid;
    private GridLayout alkalineEarthMetalGrid;
    private GridLayout transitionMetalP4Grid;
    private GridLayout transitionMetalP5Grid;
    private GridLayout transitionMetalP6Grid;
    private GridLayout transitionMetalP7Grid;
    private GridLayout lanthanidesGrid;
    private GridLayout actinidesGrid;
    private GridLayout postTransitionMetalsGrid;
    private GridLayout metalloidsGrid;
    private GridLayout superheavyElementsGrid;

    // Mnemonic TextViews
    private TextView reactiveNonmetalMnemonic;
    private TextView nobleGasMnemonic;
    private TextView alkaliMetalMnemonic;
    private TextView alkalineEarthMetalMnemonic;
    private TextView transitionMetalP4Mnemonic;
    private TextView transitionMetalP5Mnemonic;
    private TextView transitionMetalP6Mnemonic;
    private TextView transitionMetalP7Mnemonic;
    private TextView lanthanidesMnemonic;
    private TextView actinidesMnemonic;
    private TextView postTransitionMetalsMnemonic;
    private TextView metalloidsMnemonic;
    private TextView superheavyElementsMnemonic;

    // Title TextViews (for sliding animation)
    private TextView reactiveNonmetalTitle;
    private TextView nobleGasTitle;
    private TextView alkaliMetalTitle;
    private TextView alkalineEarthMetalTitle;
    private TextView transitionMetalP4Title;
    private TextView transitionMetalP5Title;
    private TextView transitionMetalP6Title;
    private TextView transitionMetalP7Title;
    private TextView lanthanidesTitle;
    private TextView actinidesTitle;
    private TextView postTransitionMetalsTitle;
    private TextView metalloidsTitle;
    private TextView superheavyElementsTitle;

    private BottomNavigationView bottomNavigationView;
    private Map<Integer, String> elementDescriptions;
    private boolean isMnemonicsMode = false; // Track current mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elements);


        // Initialize views
        initializeViews();

        // Set welcome message (you can make this dynamic later)
        welcomeMessage.setText("Good Day! Tommy!");

        // Initialize element descriptions
        initializeElementDescriptions();

        // Setup click listeners
        setupSectionClickListeners();
        setupElementClickListeners();

        // Setup mnemonics switch with enhanced functionality
        setupEnhancedMnemonicsSwitch();

        // Setup bottom navigation
        setupBottomNavigation();
    }

    private void initializeViews() {
        searchBar = findViewById(R.id.searchBar);
        welcomeMessage = findViewById(R.id.welcomeMessage);
        mnemonicsSwitch = findViewById(R.id.mnemonicsSwitch);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Section cards
        reactiveNonmetalCard = findViewById(R.id.reactiveNonmetalCard);
        nobleGasCard = findViewById(R.id.nobleGasCard);
        alkaliMetalCard = findViewById(R.id.alkaliMetalCard);
        alkalineEarthMetalCard = findViewById(R.id.alkalineEarthMetalCard);
        transitionMetalP4Card = findViewById(R.id.transitionMetalP4Card);
        transitionMetalP5Card = findViewById(R.id.transitionMetalP5Card);
        transitionMetalP6Card = findViewById(R.id.transitionMetalP6Card);
        transitionMetalP7Card = findViewById(R.id.transitionMetalP7Card);
        lanthanidesCard = findViewById(R.id.lanthanidesCard);
        actinidesCard = findViewById(R.id.actinidesCard);
        postTransitionMetalsCard = findViewById(R.id.postTransitionMetalsCard);
        metalloidsCard = findViewById(R.id.metalloidsCard);
        superheavyElementsCard = findViewById(R.id.superheavyElementsCard);

        // Element grids
        reactiveNonmetalGrid = findViewById(R.id.reactiveNonmetalGrid);
        nobleGasGrid = findViewById(R.id.nobleGasGrid);
        alkaliMetalGrid = findViewById(R.id.alkaliMetalGrid);
        alkalineEarthMetalGrid = findViewById(R.id.alkalineEarthMetalGrid);
        transitionMetalP4Grid = findViewById(R.id.transitionMetalP4Grid);
        transitionMetalP5Grid = findViewById(R.id.transitionMetalP5Grid);
        transitionMetalP6Grid = findViewById(R.id.transitionMetalP6Grid);
        transitionMetalP7Grid = findViewById(R.id.transitionMetalP7Grid);
        lanthanidesGrid = findViewById(R.id.lanthanidesGrid);
        actinidesGrid = findViewById(R.id.actinidesGrid);
        postTransitionMetalsGrid = findViewById(R.id.postTransitionMetalsGrid);
        metalloidsGrid = findViewById(R.id.metalloidsGrid);
        superheavyElementsGrid = findViewById(R.id.superheavyElementsGrid);

        // Mnemonic text views
        reactiveNonmetalMnemonic = findViewById(R.id.reactiveNonmetalMnemonic);
        nobleGasMnemonic = findViewById(R.id.nobleGasMnemonic);
        alkaliMetalMnemonic = findViewById(R.id.alkaliMetalMnemonic);
        alkalineEarthMetalMnemonic = findViewById(R.id.alkalineEarthMetalMnemonic);
        transitionMetalP4Mnemonic = findViewById(R.id.transitionMetalP4Mnemonic);
        transitionMetalP5Mnemonic = findViewById(R.id.transitionMetalP5Mnemonic);
        transitionMetalP6Mnemonic = findViewById(R.id.transitionMetalP6Mnemonic);
        transitionMetalP7Mnemonic = findViewById(R.id.transitionMetalP7Mnemonic);
        lanthanidesMnemonic = findViewById(R.id.lanthanidesMnemonic);
        actinidesMnemonic = findViewById(R.id.actinidesMnemonic);
        postTransitionMetalsMnemonic = findViewById(R.id.postTransitionMetalsMnemonic);
        metalloidsMnemonic = findViewById(R.id.metalloidsMnemonic);
        superheavyElementsMnemonic = findViewById(R.id.superheavyElementsMnemonic);

        // Title text views ids
        reactiveNonmetalTitle = findViewById(R.id.reactiveNonmetalTitle);
        nobleGasTitle = findViewById(R.id.nobleGasTitle);
        alkaliMetalTitle = findViewById(R.id.alkaliMetalTitle);
        alkalineEarthMetalTitle = findViewById(R.id.alkalineEarthMetalTitle);


        transitionMetalP4Title = findViewById(R.id.transitionMetalP4Title);
    }

    private void setupSectionClickListeners() {
        reactiveNonmetalCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(reactiveNonmetalGrid);
        });
        nobleGasCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(nobleGasGrid);
        });
        alkaliMetalCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(alkaliMetalGrid);
        });
        alkalineEarthMetalCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(alkalineEarthMetalGrid);
        });
        transitionMetalP4Card.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(transitionMetalP4Grid);
        });
        transitionMetalP5Card.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(transitionMetalP5Grid);
        });
        transitionMetalP6Card.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(transitionMetalP6Grid);
        });
        transitionMetalP7Card.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(transitionMetalP7Grid);
        });
        lanthanidesCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(lanthanidesGrid);
        });
        actinidesCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(actinidesGrid);
        });
        postTransitionMetalsCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(postTransitionMetalsGrid);
        });
        metalloidsCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(metalloidsGrid);
        });
        superheavyElementsCard.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(superheavyElementsGrid);
        });
        transitionMetalP4Card.setOnClickListener(v -> {
            if (isMnemonicsMode) toggleSection(transitionMetalP4Grid);
        });

    }
        private void setupElementClickListeners() {
            // Reactive Nonmetal Elements
            setupElementClickListener(R.id.imageButtonH);
            setupElementClickListener(R.id.imageButtonC);
            setupElementClickListener(R.id.imageButtonN);
            setupElementClickListener(R.id.imageButtonO);
            setupElementClickListener(R.id.imageButtoF);
            setupElementClickListener(R.id.imageButtonP);
            setupElementClickListener(R.id.imageButtonS);
            setupElementClickListener(R.id.imageButtonCl);
            setupElementClickListener(R.id.imageButtonSe);
            setupElementClickListener(R.id.imageButtonBr);
            setupElementClickListener(R.id.imageButtonI);

            // Noble Gas Elements
            setupElementClickListener(R.id.imageButtonHe);
            setupElementClickListener(R.id.imageButtonNe);
            setupElementClickListener(R.id.imageButtonAr);
            setupElementClickListener(R.id.imageButtonKr);
            setupElementClickListener(R.id.imageButtonXe);
            setupElementClickListener(R.id.imageButtonRn);

            // Alkali Metal Elements
            setupElementClickListener(R.id.imageButtonLi);
            setupElementClickListener(R.id.imageButtonNa);
            setupElementClickListener(R.id.imageButtonK);
            setupElementClickListener(R.id.imageButtonRb);
            setupElementClickListener(R.id.imageButtonCs);
            setupElementClickListener(R.id.imageButtonFr);

            // Alkaline Earth Metal Elements
            setupElementClickListener(R.id.imageButtonBe);
            setupElementClickListener(R.id.imageButtonMg);
            setupElementClickListener(R.id.imageButtonCa);
            setupElementClickListener(R.id.imageButtonSr);
            setupElementClickListener(R.id.imageButtonBa);
            setupElementClickListener(R.id.imageButtonRa);

            // Transition Metals Period 4
            setupElementClickListener(R.id.imageButtonSc);
            setupElementClickListener(R.id.imageButtonTi);
            setupElementClickListener(R.id.imageButtonV);
            setupElementClickListener(R.id.imageButtonCr);
            setupElementClickListener(R.id.imageButtonMn);
            setupElementClickListener(R.id.imageButtonFe);
            setupElementClickListener(R.id.imageButtonCo);
            setupElementClickListener(R.id.imageButtonNi);
            setupElementClickListener(R.id.imageButtonCu);

            // Transition Metals Period 5
            setupElementClickListener(R.id.imageButtonY);
            setupElementClickListener(R.id.imageButtonZr);
            setupElementClickListener(R.id.imageButtonNb);
            setupElementClickListener(R.id.imageButtonMo);
            setupElementClickListener(R.id.imageButtonTc);
            setupElementClickListener(R.id.imageButtonRu);
            setupElementClickListener(R.id.imageButtonRh);
            setupElementClickListener(R.id.imageButtonPd);
            setupElementClickListener(R.id.imageButtonAg);

            // Transition Metals Period 6
            setupElementClickListener(R.id.imageButtonHf);
            setupElementClickListener(R.id.imageButtonTa);
            setupElementClickListener(R.id.imageButtonW);
            setupElementClickListener(R.id.imageButtonRe);
            setupElementClickListener(R.id.imageButtonOs);
            setupElementClickListener(R.id.imageButtonIr);
            setupElementClickListener(R.id.imageButtonPt);
            setupElementClickListener(R.id.imageButtonAu);

            // Transition Metals Period 7
            setupElementClickListener(R.id.imageButtonRf);
            setupElementClickListener(R.id.imageButtonDb);
            setupElementClickListener(R.id.imageButtonSg);
            setupElementClickListener(R.id.imageButtonBh);
            setupElementClickListener(R.id.imageButtonHs);

            // Lanthanides
            setupElementClickListener(R.id.imageButtonLa);
            setupElementClickListener(R.id.imageButtonCe);
            setupElementClickListener(R.id.imageButtonPr);
            setupElementClickListener(R.id.imageButtonNd);
            setupElementClickListener(R.id.imageButtonPm);
            setupElementClickListener(R.id.imageButtonSm);
            setupElementClickListener(R.id.imageButtonEu);
            setupElementClickListener(R.id.imageButtonGd);
            setupElementClickListener(R.id.imageButtonTb);
            setupElementClickListener(R.id.imageButtonDy);
            setupElementClickListener(R.id.imageButtonHo);
            setupElementClickListener(R.id.imageButtonEr);
            setupElementClickListener(R.id.imageButtonTm);
            setupElementClickListener(R.id.imageButtonYb);
            setupElementClickListener(R.id.imageButtonLu);

            // Actinides
            setupElementClickListener(R.id.imageButtonAc);
            setupElementClickListener(R.id.imageButtonTh);
            setupElementClickListener(R.id.imageButtonPa);
            setupElementClickListener(R.id.imageButtonU);
            setupElementClickListener(R.id.imageButtonNp);
            setupElementClickListener(R.id.imageButtonPu);
            setupElementClickListener(R.id.imageButtonAm);
            setupElementClickListener(R.id.imageButtonCm);
            setupElementClickListener(R.id.imageButtonBk);
            setupElementClickListener(R.id.imageButtonCf);
            setupElementClickListener(R.id.imageButtonEs);
            setupElementClickListener(R.id.imageButtonFm);
            setupElementClickListener(R.id.imageButtonMd);
            setupElementClickListener(R.id.imageButtonNo);
            setupElementClickListener(R.id.imageButtonLr);

            // Post-Transition Metals
            setupElementClickListener(R.id.imageButtonAl);
            setupElementClickListener(R.id.imageButtonZn);
            setupElementClickListener(R.id.imageButtonGa);
            setupElementClickListener(R.id.imageButtonCd);
            setupElementClickListener(R.id.imageButtonIn);
            setupElementClickListener(R.id.imageButtonSn);
            setupElementClickListener(R.id.imageButtonHg);
            setupElementClickListener(R.id.imageButtonTl);
            setupElementClickListener(R.id.imageButtonPb);
            setupElementClickListener(R.id.imageButtonBi);
            setupElementClickListener(R.id.imageButtonPo);
            setupElementClickListener(R.id.imageButtonCn);

            // Metalloids
            setupElementClickListener(R.id.imageButtonB);
            setupElementClickListener(R.id.imageButtonSi);
            setupElementClickListener(R.id.imageButtonGe);
            setupElementClickListener(R.id.imageButtonAs);
            setupElementClickListener(R.id.imageButtonSb);
            setupElementClickListener(R.id.imageButtonTe);
            setupElementClickListener(R.id.imageButtonAt);

            // Superheavy Elements
            setupElementClickListener(R.id.imageButtonMt);
            setupElementClickListener(R.id.imageButtonDs);
            setupElementClickListener(R.id.imageButtonRg);
            setupElementClickListener(R.id.imageButtonNh);
            setupElementClickListener(R.id.imageButtonFl);
            setupElementClickListener(R.id.imageButtonMc);
            setupElementClickListener(R.id.imageButtonLv);
            setupElementClickListener(R.id.imageButtonTs);
            setupElementClickListener(R.id.imageButtonOg);


        // Add all other element click listeners here...
        // (Include all the transition metals, lanthanides, actinides, etc.)
    }

    private void setupElementClickListener(int elementId) {
        ImageButton elementButton = findViewById(elementId);
        if (elementButton != null) {
            elementButton.setOnClickListener(v -> {
                String description = elementDescriptions.get(elementId);
                if (description != null) {
                    String[] parts = description.split("\n", 2);
                    String elementName = parts[0];
                    String elementDescription = parts.length > 1 ? parts[1] : "";
                    showElementDescription(elementName, elementDescription);
                }
            });
        }
    }

    private void toggleSection(GridLayout gridLayout) {
        if (gridLayout.getVisibility() == View.VISIBLE) {
            // Collapse with animation
            Animation slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
            slideUp.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    gridLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            gridLayout.startAnimation(slideUp);
        } else {
            // Expand with animation
            gridLayout.setVisibility(View.VISIBLE);
            Animation slideDown = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            gridLayout.startAnimation(slideDown);
        }
    }

    private void setupEnhancedMnemonicsSwitch() {
        // Set initial state to match isMnemonicsMode
        mnemonicsSwitch.setChecked(isMnemonicsMode);

        // Apply initial state
        if (isMnemonicsMode) {
            switchToMnemonicsMode();
        } else {
            switchToReferenceMode();
        }

        mnemonicsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMnemonicsMode = isChecked;

                if (isChecked) {
                    // Switch to MNEMONICS MODE (Interactive mode)
                    switchToMnemonicsMode();
                } else {
                    // Switch to REFERENCE MODE (All elements visible)
                    switchToReferenceMode();
                }
            }
        });
    }

    private void switchToMnemonicsMode() {
        // Show mnemonics
        setMnemonicsVisibility(View.VISIBLE);

        // Collapse all grids
        collapseAllGrids();

        // Enable card interactivity
        setCardInteractivity(true);

        // Animate titles back to normal state
        animateTitlesToNormalState();
    }

    private void switchToReferenceMode() {
        // Hide mnemonics with animation
        setMnemonicsVisibility(View.GONE);

        // Expand all grids
        expandAllGrids();

        // Disable card interactivity
        setCardInteractivity(false);

        // Animate titles with sliding effect
        animateTitlesWithSlidingEffect();
    }

    private void setMnemonicsVisibility(int visibility) {
        reactiveNonmetalMnemonic.setVisibility(visibility);
        nobleGasMnemonic.setVisibility(visibility);
        alkaliMetalMnemonic.setVisibility(visibility);
        alkalineEarthMetalMnemonic.setVisibility(visibility);
        transitionMetalP4Mnemonic.setVisibility(visibility);
        transitionMetalP5Mnemonic.setVisibility(visibility);
        transitionMetalP6Mnemonic.setVisibility(visibility);
        transitionMetalP7Mnemonic.setVisibility(visibility);
        lanthanidesMnemonic.setVisibility(visibility);
        actinidesMnemonic.setVisibility(visibility);
        postTransitionMetalsMnemonic.setVisibility(visibility);
        metalloidsMnemonic.setVisibility(visibility);
        superheavyElementsMnemonic.setVisibility(visibility);
    }

    private void collapseAllGrids() {
        GridLayout[] grids = {
                reactiveNonmetalGrid, nobleGasGrid, alkaliMetalGrid, alkalineEarthMetalGrid,
                transitionMetalP4Grid, transitionMetalP5Grid, transitionMetalP6Grid, transitionMetalP7Grid, transitionMetalP4Grid,
                lanthanidesGrid, actinidesGrid, postTransitionMetalsGrid, metalloidsGrid, superheavyElementsGrid
        };

        for (GridLayout grid : grids) {
            if (grid != null) {
                grid.setVisibility(View.GONE);
            }
        }
    }

    private void expandAllGrids() {
        GridLayout[] grids = {
                reactiveNonmetalGrid, nobleGasGrid, alkaliMetalGrid, alkalineEarthMetalGrid,
                transitionMetalP4Grid, transitionMetalP5Grid, transitionMetalP6Grid, transitionMetalP7Grid, transitionMetalP4Grid,
                lanthanidesGrid, actinidesGrid, postTransitionMetalsGrid, metalloidsGrid, superheavyElementsGrid
        };

        for (GridLayout grid : grids) {
            if (grid != null) {
                grid.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setCardInteractivity(boolean interactive) {
        CardView[] cards = {
                reactiveNonmetalCard, nobleGasCard, alkaliMetalCard, alkalineEarthMetalCard,
                transitionMetalP4Card, transitionMetalP5Card, transitionMetalP6Card, transitionMetalP7Card,
                lanthanidesCard, actinidesCard, postTransitionMetalsCard, metalloidsCard, superheavyElementsCard
        };

        for (CardView card : cards) {
            if (card != null) {
                card.setClickable(interactive);
                card.setFocusable(interactive);

                // Handle ripple effect properly
                if (interactive) {
                    // Get the selectableItemBackground drawable from theme
                    android.util.TypedValue outValue = new android.util.TypedValue();
                    getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                    card.setForeground(getDrawable(outValue.resourceId));
                } else {
                    // Remove foreground ripple effect
                    card.setForeground(null);
                }
            }
        }
    }

    private void animateTitlesWithSlidingEffect() {
        TextView[] titles = {
                reactiveNonmetalTitle, nobleGasTitle, alkaliMetalTitle, alkalineEarthMetalTitle
                // Add other titles when you add their IDs to XML
        };

        for (TextView title : titles) {
            if (title != null) {
                // Create subtle sliding animation only
                ObjectAnimator slideAnimator = ObjectAnimator.ofFloat(title, "translationX", 0f, 8f);
                slideAnimator.setDuration(300);
                slideAnimator.setInterpolator(new DecelerateInterpolator());

                // Add subtle scale animation for emphasis
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(title, "scaleX", 1f, 1.02f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(title, "scaleY", 1f, 1.02f);
                scaleX.setDuration(300);
                scaleY.setDuration(300);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(slideAnimator, scaleX, scaleY);
                animatorSet.start();
            }
        }
    }

    private void animateTitlesToNormalState() {
        TextView[] titles = {
                reactiveNonmetalTitle, nobleGasTitle, alkaliMetalTitle, alkalineEarthMetalTitle
                // Add other titles when you add their IDs to XML
        };

        for (TextView title : titles) {
            if (title != null) {
                // Return to normal position and scale
                ObjectAnimator slideAnimator = ObjectAnimator.ofFloat(title, "translationX", 0f);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(title, "scaleX", 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(title, "scaleY", 1f);

                slideAnimator.setDuration(300);
                scaleX.setDuration(300);
                scaleY.setDuration(300);
                slideAnimator.setInterpolator(new DecelerateInterpolator());

                // Remove any background that might have been set
                title.setBackground(null);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(slideAnimator, scaleX, scaleY);
                animatorSet.start();
            }
        }
    }

    private void initializeElementDescriptions() {
        elementDescriptions = new HashMap<>();

        // Reactive Nonmetals
        elementDescriptions.put(R.id.imageButtonH, "Hydrogen (H)\nThe lightest and most abundant element in the universe. It is a colorless, odorless gas and serves as the building block for stars and water molecules.");
        elementDescriptions.put(R.id.imageButtonC, "Carbon (C)\nA fundamental element for all known life on Earth. It can form a vast number of compounds.");
        elementDescriptions.put(R.id.imageButtonN, "Nitrogen (N)\nA colorless, odorless, and mostly inert diatomic gas, making up about 78% of Earth's atmosphere.");
        elementDescriptions.put(R.id.imageButtonO, "Oxygen (O)\nA highly reactive nonmetal and oxidizing agent that readily forms oxides with most elements as well as other compounds.");
        elementDescriptions.put(R.id.imageButtoF, "Fluorine (F)\nThe most electronegative element and a pale yellow-green diatomic gas under standard conditions.");
        elementDescriptions.put(R.id.imageButtonP, "Phosphorus (P)\nA highly reactive nonmetal that exists in several allotropes.");
        elementDescriptions.put(R.id.imageButtonS, "Sulfur (S)\nA multivalent nonmetal, abundant, multivalent, and nonmetallic.");
        elementDescriptions.put(R.id.imageButtonCl, "Chlorine (Cl)\nA yellow-green gas at room temperature, a halogen that is a powerful oxidizing agent and disinfectant.");
        elementDescriptions.put(R.id.imageButtonSe, "Selenium (Se)\nA nonmetal with properties intermediate between those of sulfur and tellurium.");
        elementDescriptions.put(R.id.imageButtonBr, "Bromine (Br)\nA reddish-brown liquid at room temperature, a halogen that is less reactive than chlorine but more reactive than iodine.");
        elementDescriptions.put(R.id.imageButtonI, "Iodine (I)\nA lustrous, purple-black halogen that sublimes at standard conditions to form a purple gas.");

        // Noble Gases
        elementDescriptions.put(R.id.imageButtonHe, "Helium (He)\nThe second lightest element. An inert gas used in balloons and as a coolant in scientific applications.");
        elementDescriptions.put(R.id.imageButtonNe, "Neon (Ne)\nA noble gas that glows orange-red when electrically excited. Famous for its use in advertising signs.");
        elementDescriptions.put(R.id.imageButtonAr, "Argon (Ar)\nThe third-most abundant gas in Earth's atmosphere. Used as a protective atmosphere for welding and growing silicon crystals.");
        elementDescriptions.put(R.id.imageButtonKr, "Krypton (Kr)\nA colorless, odorless noble gas. Used in some types of electric lights and has several radioactive isotopes.");
        elementDescriptions.put(R.id.imageButtonXe, "Xenon (Xe)\nA heavy, colorless noble gas. Used in specialized light bulbs and as a general anesthetic.");
        elementDescriptions.put(R.id.imageButtonRn, "Radon (Rn)\nA radioactive, colorless noble gas produced by uranium decay. Can be a health hazard in buildings.");

        // Alkali Metals
        elementDescriptions.put(R.id.imageButtonLi, "Lithium (Li)\nThe lightest metal and lightest solid element. Used in batteries, mood-stabilizing drugs, and ceramics.");
        elementDescriptions.put(R.id.imageButtonNa, "Sodium (Na)\nA soft, silvery-white, highly reactive metal. Essential for life and commonly found in salt (NaCl).");
        elementDescriptions.put(R.id.imageButtonK, "Potassium (K)\nA silvery-white metal soft enough to cut with a knife. Essential for cellular function and found in bananas.");
        elementDescriptions.put(R.id.imageButtonRb, "Rubidium (Rb)\nA soft, silvery-white metallic element that ignites spontaneously in air. Used in atomic clocks.");
        elementDescriptions.put(R.id.imageButtonCs, "Cesium (Cs)\nA soft, silvery-gold alkali metal. Used to define the second and in atomic clocks.");
        elementDescriptions.put(R.id.imageButtonFr, "Francium (Fr)\nA highly radioactive metallic element. The rarest naturally occurring element with no stable isotopes.");

        // Alkaline Earth Metals
        elementDescriptions.put(R.id.imageButtonBe, "Beryllium (Be)\nA lightweight, steel-gray metal. Used in aerospace applications and nuclear reactors due to its strength and transparency to X-rays.");
        elementDescriptions.put(R.id.imageButtonMg, "Magnesium (Mg)\nA shiny gray metal that burns with a brilliant white flame. Essential for chlorophyll and used in fireworks.");
        elementDescriptions.put(R.id.imageButtonCa, "Calcium (Ca)\nA soft gray metal essential for bones and teeth. The fifth most abundant element in Earth's crust.");
        elementDescriptions.put(R.id.imageButtonSr, "Strontium (Sr)\nA soft silver-white metal that burns with a red flame. Used in fireworks and flares.");
        elementDescriptions.put(R.id.imageButtonBa, "Barium (Ba)\nA soft, silvery-white metal that burns with a green flame. Used in medical imaging and fireworks.");
        elementDescriptions.put(R.id.imageButtonRa, "Radium (Ra)\nA highly radioactive metallic element that glows in the dark. Once used in luminous paints.");

        // Transition Metals Period 4
        elementDescriptions.put(R.id.imageButtonSc, "Scandium (Sc)\nA silvery-white metal often found with rare earth elements. Used in aerospace alloys and sports equipment.");
        elementDescriptions.put(R.id.imageButtonTi, "Titanium (Ti)\nA lustrous, strong, low-density metal with excellent corrosion resistance. Used in aircraft and medical implants.");
        elementDescriptions.put(R.id.imageButtonV, "Vanadium (V)\nA hard, silvery-grey metal used to strengthen steel. Named after Vanadis, the Norse goddess of beauty.");
        elementDescriptions.put(R.id.imageButtonCr, "Chromium (Cr)\nA steely-grey, lustrous metal that gives stainless steel its stain resistance. Used in chrome plating.");
        elementDescriptions.put(R.id.imageButtonMn, "Manganese (Mn)\nA hard, brittle, silvery metal essential for steel production and plant photosynthesis.");
        elementDescriptions.put(R.id.imageButtonFe, "Iron (Fe)\nThe most common element on Earth by mass. Essential for hemoglobin and the backbone of modern civilization.");
        elementDescriptions.put(R.id.imageButtonCo, "Cobalt (Co)\nA hard, lustrous, silver-gray metal. Essential for vitamin B12 and used in rechargeable batteries.");
        elementDescriptions.put(R.id.imageButtonNi, "Nickel (Ni)\nA silvery-white metal with a slight golden tinge. Used in coins, stainless steel, and batteries.");
        elementDescriptions.put(R.id.imageButtonCu, "Copper (Cu)\nA reddish-orange metal with excellent electrical conductivity. Used in wiring, plumbing, and coins.");

        // Transition Metals Period 5
        elementDescriptions.put(R.id.imageButtonY, "Yttrium (Y)\nA silvery metal used in red phosphors for TV screens and LED lights. Named after Ytterby, Sweden.");
        elementDescriptions.put(R.id.imageButtonZr, "Zirconium (Zr)\nA lustrous, grey-white metal extremely resistant to corrosion. Used in nuclear reactors and jewelry.");
        elementDescriptions.put(R.id.imageButtonNb, "Niobium (Nb)\nA grey, crystalline metal used in superconducting magnets and rocket engines.");
        elementDescriptions.put(R.id.imageButtonMo, "Molybdenum (Mo)\nA silvery metal used to strengthen steel alloys. Essential trace element for plants and animals.");
        elementDescriptions.put(R.id.imageButtonTc, "Technetium (Tc)\nThe first artificially produced element. All isotopes are radioactive; used in medical imaging.");
        elementDescriptions.put(R.id.imageButtonRu, "Ruthenium (Ru)\nA rare, hard, brittle platinum group metal. Used in electrical contacts and as a catalyst.");
        elementDescriptions.put(R.id.imageButtonRh, "Rhodium (Rh)\nA rare, silvery-white metal and one of the most expensive elements. Used in catalytic converters.");
        elementDescriptions.put(R.id.imageButtonPd, "Palladium (Pd)\nA lustrous silvery-white metal used in catalytic converters, electronics, and jewelry.");
        elementDescriptions.put(R.id.imageButtonAg, "Silver (Ag)\nA lustrous white metal with the highest electrical and thermal conductivity. Used in jewelry, coins, and electronics.");

        // Transition Metals Period 6
        elementDescriptions.put(R.id.imageButtonHf, "Hafnium (Hf)\nA lustrous, silvery metal chemically similar to zirconium. Used in nuclear control rods.");
        elementDescriptions.put(R.id.imageButtonTa, "Tantalum (Ta)\nA rare, hard, blue-gray metal highly resistant to corrosion. Used in electronics and medical implants.");
        elementDescriptions.put(R.id.imageButtonW, "Tungsten (W)\nThe element with the highest melting point. Used in light bulb filaments and armor-piercing ammunition.");
        elementDescriptions.put(R.id.imageButtonRe, "Rhenium (Re)\nOne of the rarest elements in Earth's crust. Used in jet engine components and catalysts.");
        elementDescriptions.put(R.id.imageButtonOs, "Osmium (Os)\nThe densest naturally occurring element with a blue-tinged appearance. Extremely hard and brittle.");
        elementDescriptions.put(R.id.imageButtonIr, "Iridium (Ir)\nThe second-densest element and highly corrosion-resistant. Used in spark plugs and crucibles.");
        elementDescriptions.put(R.id.imageButtonPt, "Platinum (Pt)\nA dense, malleable, precious metal that doesn't tarnish. Used in jewelry, catalytic converters, and laboratory equipment.");
        elementDescriptions.put(R.id.imageButtonAu, "Gold (Au)\nA bright yellow, dense, soft metal that doesn't tarnish. Valued for jewelry, electronics, and as a store of value.");

        // Transition Metals Period 7
        elementDescriptions.put(R.id.imageButtonRf, "Rutherfordium (Rf)\nA synthetic, radioactive transactinide element. Named after Ernest Rutherford, the nuclear physicist.");
        elementDescriptions.put(R.id.imageButtonDb, "Dubnium (Db)\nA synthetic, radioactive transactinide element. Named after Dubna, Russia, where it was first produced.");
        elementDescriptions.put(R.id.imageButtonSg, "Seaborgium (Sg)\nA synthetic, radioactive element. Named after Glenn T. Seaborg, who discovered many transuranium elements.");
        elementDescriptions.put(R.id.imageButtonBh, "Bohrium (Bh)\nA synthetic, radioactive element. Named after Niels Bohr, the Danish physicist.");
        elementDescriptions.put(R.id.imageButtonHs, "Hassium (Hs)\nA synthetic, radioactive element. Named after the German state of Hesse.");

        // Lanthanides
        elementDescriptions.put(R.id.imageButtonLa, "Lanthanum (La)\nA soft, ductile, silvery-white rare earth metal. Used in camera lenses and battery electrodes.");
        elementDescriptions.put(R.id.imageButtonCe, "Cerium (Ce)\nThe most abundant rare earth element. Used in catalytic converters and lighter flints.");
        elementDescriptions.put(R.id.imageButtonPr, "Praseodymium (Pr)\nA soft, silvery metal that develops a green oxide coating in air. Used in aircraft engines and magnets.");
        elementDescriptions.put(R.id.imageButtonNd, "Neodymium (Nd)\nA rare earth metal used to make the strongest permanent magnets. Found in headphones and hard drives.");
        elementDescriptions.put(R.id.imageButtonPm, "Promethium (Pm)\nThe only radioactive lanthanide with no stable isotopes. Used in nuclear batteries and luminous paint.");
        elementDescriptions.put(R.id.imageButtonSm, "Samarium (Sm)\nA moderately hard silvery metal. Used in permanent magnets and as a neutron absorber in nuclear reactors.");
        elementDescriptions.put(R.id.imageButtonEu, "Europium (Eu)\nThe most reactive lanthanide. Used in red phosphors for TV screens and anti-counterfeiting inks.");
        elementDescriptions.put(R.id.imageButtonGd, "Gadolinium (Gd)\nA silvery-white metal with unusual magnetic properties. Used in MRI contrast agents and nuclear reactors.");
        elementDescriptions.put(R.id.imageButtonTb, "Terbium (Tb)\nA silvery-white rare earth metal. Used in green phosphors and solid-state devices.");
        elementDescriptions.put(R.id.imageButtonDy, "Dysprosium (Dy)\nA rare earth element with a bright silver metallic luster. Used in permanent magnets and nuclear reactors.");
        elementDescriptions.put(R.id.imageButtonHo, "Holmium (Ho)\nA rare earth metal with the highest magnetic strength of any element. Used in magnetic field concentrators.");
        elementDescriptions.put(R.id.imageButtonEr, "Erbium (Er)\nA silvery-white metal used in fiber-optic cables and nuclear control rods.");
        elementDescriptions.put(R.id.imageButtonTm, "Thulium (Tm)\nThe second-least abundant lanthanide. Used in portable X-ray devices and blue phosphors.");
        elementDescriptions.put(R.id.imageButtonYb, "Ytterbium (Yb)\nA soft, malleable rare earth metal. Used in stainless steel and as a gamma ray source.");
        elementDescriptions.put(R.id.imageButtonLu, "Lutetium (Lu)\nThe hardest and densest lanthanide. Used in medical imaging and as a catalyst in hydrogenation.");

        // Actinides
        elementDescriptions.put(R.id.imageButtonAc, "Actinium (Ac)\nA radioactive metallic element that glows blue in the dark. Used in neutron sources and cancer treatment.");
        elementDescriptions.put(R.id.imageButtonTh, "Thorium (Th)\nA weakly radioactive metal more abundant than uranium. Potential future nuclear fuel.");
        elementDescriptions.put(R.id.imageButtonPa, "Protactinium (Pa)\nA dense, silvery-gray radioactive metal. One of the rarest and most expensive elements.");
        elementDescriptions.put(R.id.imageButtonU, "Uranium (U)\nA heavy, radioactive metal used as fuel in nuclear reactors and weapons. Named after the planet Uranus.");
        elementDescriptions.put(R.id.imageButtonNp, "Neptunium (Np)\nThe first transuranium element discovered. Radioactive and used in neutron detection equipment.");
        elementDescriptions.put(R.id.imageButtonPu, "Plutonium (Pu)\nA radioactive actinide metal used in nuclear weapons and reactors. Extremely toxic to humans.");
        elementDescriptions.put(R.id.imageButtonAm, "Americium (Am)\nA synthetic radioactive element used in smoke detectors and industrial gauges.");
        elementDescriptions.put(R.id.imageButtonCm, "Curium (Cm)\nA hard, dense, silvery radioactive metal. Used in space missions as a power source.");
        elementDescriptions.put(R.id.imageButtonBk, "Berkelium (Bk)\nA radioactive synthetic element. Named after Berkeley, California, where it was discovered.");
        elementDescriptions.put(R.id.imageButtonCf, "Californium (Cf)\nA radioactive element used in neutron sources and nuclear reactors. Extremely expensive to produce.");
        elementDescriptions.put(R.id.imageButtonEs, "Einsteinium (Es)\nA synthetic radioactive element. Named after Albert Einstein, with no practical applications.");
        elementDescriptions.put(R.id.imageButtonFm, "Fermium (Fm)\nA synthetic radioactive element. Named after Enrico Fermi, used only for scientific research.");
        elementDescriptions.put(R.id.imageButtonMd, "Mendelevium (Md)\nA synthetic radioactive element. Named after Dmitri Mendeleev, creator of the periodic table.");
        elementDescriptions.put(R.id.imageButtonNo, "Nobelium (No)\nA synthetic radioactive element. Named after Alfred Nobel, with no known uses outside research.");
        elementDescriptions.put(R.id.imageButtonLr, "Lawrencium (Lr)\nA synthetic radioactive element. Named after Ernest Lawrence, inventor of the cyclotron.");

        // Post-Transition Metals
        elementDescriptions.put(R.id.imageButtonAl, "Aluminum (Al)\nA lightweight, silvery-white metal that doesn't rust. The most abundant metal in Earth's crust.");
        elementDescriptions.put(R.id.imageButtonZn, "Zinc (Zn)\nA bluish-white metal essential for immune function. Used in batteries, galvanization, and alloys.");
        elementDescriptions.put(R.id.imageButtonGa, "Gallium (Ga)\nA soft, silvery metal that melts at 29.8°C (85.6°F). Used in electronics and solar panels.");
        elementDescriptions.put(R.id.imageButtonCd, "Cadmium (Cd)\nA soft, bluish-white toxic metal. Used in batteries, pigments, and nuclear reactor control rods.");
        elementDescriptions.put(R.id.imageButtonIn, "Indium (In)\nA soft, malleable metal with a brilliant luster. Used in touchscreens and LED lights.");
        elementDescriptions.put(R.id.imageButtonSn, "Tin (Sn)\nA silvery metal that 'cries' when bent. Used in solder, bronze, and food cans.");
        elementDescriptions.put(R.id.imageButtonHg, "Mercury (Hg)\nThe only metal that's liquid at room temperature. Toxic and used in thermometers and switches.");
        elementDescriptions.put(R.id.imageButtonTl, "Thallium (Tl)\nA soft, gray post-transition metal. Highly toxic and once used as rat poison.");
        elementDescriptions.put(R.id.imageButtonPb, "Lead (Pb)\nA heavy, soft metal that has been used for thousands of years. Toxic and now banned in many applications.");
        elementDescriptions.put(R.id.imageButtonBi, "Bismuth (Bi)\nA brittle metal with a silvery-pink hue. The most diamagnetic element and used in medicines.");
        elementDescriptions.put(R.id.imageButtonPo, "Polonium (Po)\nA rare, highly radioactive metal discovered by Marie Curie. Extremely dangerous to handle.");
        elementDescriptions.put(R.id.imageButtonCn, "Copernicium (Cn)\nA synthetic, extremely radioactive transactinide element. Named after astronomer Nicolaus Copernicus.");

        // Metalloids
        elementDescriptions.put(R.id.imageButtonB, "Boron (B)\nA hard, black metalloid essential for plant growth. Used in glass, ceramics, and nuclear reactors.");
        elementDescriptions.put(R.id.imageButtonSi, "Silicon (Si)\nThe second most abundant element in Earth's crust. The foundation of modern electronics and computers.");
        elementDescriptions.put(R.id.imageButtonGe, "Germanium (Ge)\nA lustrous, hard, grayish-white metalloid. Used in semiconductors and fiber optics.");
        elementDescriptions.put(R.id.imageButtonAs, "Arsenic (As)\nA metalloid notorious as a poison. Also used in semiconductors and wood preservatives.");
        elementDescriptions.put(R.id.imageButtonSb, "Antimony (Sb)\nA lustrous gray metalloid used in flame retardants and lead-acid batteries.");
        elementDescriptions.put(R.id.imageButtonTe, "Tellurium (Te)\nA brittle, mildly toxic metalloid. Used in solar panels and as a coloring agent for glass.");
        elementDescriptions.put(R.id.imageButtonAt, "Astatine (At)\nThe rarest naturally occurring element on Earth. Radioactive with no stable isotopes.");

        // Superheavy Elements
        elementDescriptions.put(R.id.imageButtonMt, "Meitnerium (Mt)\nA synthetic, extremely radioactive element. Named after physicist Lise Meitner.");
        elementDescriptions.put(R.id.imageButtonDs, "Darmstadtium (Ds)\nA synthetic, extremely radioactive element. Named after Darmstadt, Germany.");
        elementDescriptions.put(R.id.imageButtonRg, "Roentgenium (Rg)\nA synthetic, extremely radioactive element. Named after Wilhelm Röntgen, discoverer of X-rays.");
        elementDescriptions.put(R.id.imageButtonNh, "Nihonium (Nh)\nA synthetic radioactive element. Named after Japan (Nihon in Japanese).");
        elementDescriptions.put(R.id.imageButtonFl, "Flerovium (Fl)\nA synthetic, extremely radioactive element. Named after Soviet physicist Georgy Flyorov.");
        elementDescriptions.put(R.id.imageButtonMc, "Moscovium (Mc)\nA synthetic radioactive element. Named after Moscow, Russia.");
        elementDescriptions.put(R.id.imageButtonLv, "Livermorium (Lv)\nA synthetic, extremely radioactive element. Named after Lawrence Livermore National Laboratory.");
        elementDescriptions.put(R.id.imageButtonTs, "Tennessine (Ts)\nA synthetic, extremely radioactive element. Named after Tennessee, USA.");
        elementDescriptions.put(R.id.imageButtonOg, "Oganesson (Og)\nThe heaviest known element. Named after Russian physicist Yuri Oganessian.");
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.elements) {
                // Already on elements page
                return true;
            } else if (itemId == R.id.challege) {
                // Navigate to challenge activity
                Intent intent = new Intent(activity_elements.this, activity_challenge.class);
                startActivity(intent);
                return true;
            }

            return false;
        });

        // Set Elements as selected
        bottomNavigationView.setSelectedItemId(R.id.elements);
    }

    private void showElementDescription(String elementName, String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(elementName)
                .setMessage(description)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
//    protected void onResume() {
//        super.onResume();
//        // Ensure Elements is selected when returning to this activity
//        if (bottomNavigationView != null) {
//            bottomNavigationView.setSelectedItemId(R.id.elements);
//        }
//    }
}