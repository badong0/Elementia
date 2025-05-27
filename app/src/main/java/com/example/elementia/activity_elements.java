package com.example.elementia;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import android.app.AlertDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


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

    private Map<Integer, String> elementTrivia;

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

        // Initialize element descriptions
        initializeElementDescriptions();

        // Setup click listeners
        setupSectionClickListeners();
        setupElementClickListeners();
        initializeElementTrivia();

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
        // Create custom layout for dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_element_description, null);

        ImageView elementImage = dialogView.findViewById(R.id.elementImage);
        TextView elementTitle = dialogView.findViewById(R.id.elementTitle);
        TextView elementDescription = dialogView.findViewById(R.id.elementDescription);
        TextView elementTrivia = dialogView.findViewById(R.id.elementTrivia); // NEW LINE

        // Set the content
        elementTitle.setText(elementName);
        elementDescription.setText(description);

        // NEW: Set trivia content
        String triviaText = getTriviaByElementName(elementName);
        if (triviaText != null && !triviaText.isEmpty()) {
            elementTrivia.setText(triviaText);
            elementTrivia.setVisibility(View.VISIBLE);
        } else {
            elementTrivia.setVisibility(View.GONE);
        }

        // Set the element image based on element name
        setElementImage(elementImage, elementName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    // Add this new helper method
    private String getTriviaByElementName(String elementName) {
        // Safety check: if trivia map is null, return default message
        if (elementTrivia == null) {
            return "🌟 Each element has its own unique story in the universe!";
        }

        // Extract element symbol from name (e.g., "Hydrogen (H)" -> "H")
        String symbol = elementName.substring(elementName.indexOf("(") + 1, elementName.indexOf(")"));

        // Find the element ID by symbol and return its trivia
        for (Map.Entry<Integer, String> entry : elementDescriptions.entrySet()) {
            String entryElementName = entry.getValue().split("\n")[0];
            if (entryElementName.equals(elementName)) {
                String trivia = elementTrivia.get(entry.getKey());
                return trivia != null ? trivia : "🌟 Each element has its own unique story in the universe!";
            }
        }

        return "🌟 Each element has its own unique story in the universe!"; // Default trivia
    }

    private void setElementImage(ImageView imageView, String elementName) {
        // Extract element symbol from name (e.g., "Hydrogen (H)" -> "H")
        String symbol = elementName.substring(elementName.indexOf("(") + 1, elementName.indexOf(")"));

        // Map element symbols to drawable resources
        switch (symbol.toLowerCase()) {
            // Reactive Nonmetals
            case "h":
                imageView.setImageResource(R.drawable.h_image);
                break;
            case "c":
                imageView.setImageResource(R.drawable.c_image);
                break;
            case "n":
                imageView.setImageResource(R.drawable.n_image);
                break;
            case "o":
                imageView.setImageResource(R.drawable.o_image);
                break;
            case "f":
                imageView.setImageResource(R.drawable.f_image);
                break;
            case "p":
                imageView.setImageResource(R.drawable.p_image);
                break;
            case "s":
                imageView.setImageResource(R.drawable.s_image);
                break;
            case "cl":
                imageView.setImageResource(R.drawable.cl_image);
                break;
            case "se":
                imageView.setImageResource(R.drawable.se_image);
                break;
            case "br":
                imageView.setImageResource(R.drawable.br_image);
                break;
            case "i":
                imageView.setImageResource(R.drawable.i_image);
                break;

            // Noble Gases
            case "he":
                imageView.setImageResource(R.drawable.he_image);
                break;
            case "ne":
                imageView.setImageResource(R.drawable.ne_image);
                break;
            case "ar":
                imageView.setImageResource(R.drawable.ar_image);
                break;
            case "kr":
                imageView.setImageResource(R.drawable.kr_image);
                break;
            case "xe":
                imageView.setImageResource(R.drawable.xe_image);
                break;
            case "rn":
                imageView.setImageResource(R.drawable.rn_image);
                break;
            case "og": // Oganesson, from superheavy list
                imageView.setImageResource(R.drawable.og_image);
                break;

            // Alkali Metals
            case "li":
                imageView.setImageResource(R.drawable.li_image);
                break;
            case "na":
                imageView.setImageResource(R.drawable.na_image);
                break;
            case "k":
                imageView.setImageResource(R.drawable.k_image);
                break;
            case "rb":
                imageView.setImageResource(R.drawable.rb_image);
                break;
            case "cs":
                imageView.setImageResource(R.drawable.cs_image);
                break;
            case "fr":
                imageView.setImageResource(R.drawable.fr_image);
                break;

            // Alkaline Earth Metals
            case "be":
                imageView.setImageResource(R.drawable.be_image);
                break;
            case "mg":
                imageView.setImageResource(R.drawable.mg_image);
                break;
            case "ca":
                imageView.setImageResource(R.drawable.ca_image);
                break;
            case "sr":
                imageView.setImageResource(R.drawable.sr_image);
                break;
            case "ba":
                imageView.setImageResource(R.drawable.ba_image);
                break;
            case "ra":
                imageView.setImageResource(R.drawable.ra_image);
                break;

            // Transition Metals Period 4 (Sc to Cu)
            case "sc":
                imageView.setImageResource(R.drawable.sc_image);
                break;
            case "ti":
                imageView.setImageResource(R.drawable.ti_image);
                break;
            case "v":
                imageView.setImageResource(R.drawable.v_image);
                break;
            case "cr":
                imageView.setImageResource(R.drawable.cr_image);
                break;
            case "mn":
                imageView.setImageResource(R.drawable.mn_image);
                break;
            case "fe":
                imageView.setImageResource(R.drawable.fe_image);
                break;
            case "co":
                imageView.setImageResource(R.drawable.co_image);
                break;
            case "ni":
                imageView.setImageResource(R.drawable.ni_image);
                break;
            case "cu":
                imageView.setImageResource(R.drawable.cu_image);
                break;

            // Transition Metals Period 5 (Y to Ag)
            case "y":
                imageView.setImageResource(R.drawable.y_image);
                break;
            case "zr":
                imageView.setImageResource(R.drawable.zr_image);
                break;
            case "nb":
                imageView.setImageResource(R.drawable.nb_image);
                break;
            case "mo":
                imageView.setImageResource(R.drawable.mo_image);
                break;
            case "tc":
                imageView.setImageResource(R.drawable.tc_image);
                break;
            case "ru":
                imageView.setImageResource(R.drawable.ru_image);
                break;
            case "rh":
                imageView.setImageResource(R.drawable.rh_image);
                break;
            case "pd":
                imageView.setImageResource(R.drawable.pd_image);
                break;
            case "ag":
                imageView.setImageResource(R.drawable.ag_image);
                break;

            // Transition Metals Period 6 (Hf to Au)
            case "hf":
                imageView.setImageResource(R.drawable.hf_image);
                break;
            case "ta":
                imageView.setImageResource(R.drawable.ta_image);
                break;
            case "w":
                imageView.setImageResource(R.drawable.w_image);
                break;
            case "re":
                imageView.setImageResource(R.drawable.re_image);
                break;
            case "os":
                imageView.setImageResource(R.drawable.os_image);
                break;
            case "ir":
                imageView.setImageResource(R.drawable.ir_image);
                break;
            case "pt":
                imageView.setImageResource(R.drawable.pt_image);
                break;
            case "au":
                imageView.setImageResource(R.drawable.au_image);
                break;

            // Transition Metals Period 7 (Rf to Hs)
            case "rf":
                imageView.setImageResource(R.drawable.rf_image);
                break;
            case "db":
                imageView.setImageResource(R.drawable.db_image);
                break;
            case "sg":
                imageView.setImageResource(R.drawable.sg_image);
                break;
            case "bh":
                imageView.setImageResource(R.drawable.bh_image);
                break;
            case "hs":
                imageView.setImageResource(R.drawable.hs_image);
                break;

            // Lanthanides (La to Lu)
            case "la":
                imageView.setImageResource(R.drawable.la_image);
                break;
            case "ce":
                imageView.setImageResource(R.drawable.ce_image);
                break;
            case "pr":
                imageView.setImageResource(R.drawable.pr_image);
                break;
            case "nd":
                imageView.setImageResource(R.drawable.nd_image);
                break;
            case "pm":
                imageView.setImageResource(R.drawable.pm_image);
                break;
            case "sm":
                imageView.setImageResource(R.drawable.sm_image);
                break;
            case "eu":
                imageView.setImageResource(R.drawable.eu_image);
                break;
            case "gd":
                imageView.setImageResource(R.drawable.gd_image);
                break;
            case "tb":
                imageView.setImageResource(R.drawable.tb_image);
                break;
            case "dy":
                imageView.setImageResource(R.drawable.dy_image);
                break;
            case "ho":
                imageView.setImageResource(R.drawable.ho_image);
                break;
            case "er":
                imageView.setImageResource(R.drawable.er_image);
                break;
            case "tm":
                imageView.setImageResource(R.drawable.tm_image);
                break;
            case "yb":
                imageView.setImageResource(R.drawable.yb_image);
                break;
            case "lu":
                imageView.setImageResource(R.drawable.lu_image);
                break;

            // Actinides (Ac to Lr)
            case "ac":
                imageView.setImageResource(R.drawable.ac_image);
                break;
            case "th":
                imageView.setImageResource(R.drawable.th_image);
                break;
            case "pa":
                imageView.setImageResource(R.drawable.pa_image);
                break;
            case "u":
                imageView.setImageResource(R.drawable.u_image);
                break;
            case "np":
                imageView.setImageResource(R.drawable.np_image);
                break;
            case "pu":
                imageView.setImageResource(R.drawable.pu_image);
                break;
            case "am":
                imageView.setImageResource(R.drawable.am_image);
                break;
            case "cm":
                imageView.setImageResource(R.drawable.cm_image);
                break;
            case "bk":
                imageView.setImageResource(R.drawable.bk_image);
                break;
            case "cf":
                imageView.setImageResource(R.drawable.cf_image);
                break;
            case "es":
                imageView.setImageResource(R.drawable.es_image);
                break;
            case "fm":
                imageView.setImageResource(R.drawable.fm_image);
                break;
            case "md":
                imageView.setImageResource(R.drawable.md_image);
                break;
            case "no":
                imageView.setImageResource(R.drawable.no_image);
                break;
            case "lr":
                imageView.setImageResource(R.drawable.lr_image);
                break;

            // Post-Transition Metals (Al, Zn, Ga, Cd, In, Sn, Hg, Tl, Pb, Bi, Po, Cn)
            case "al":
                imageView.setImageResource(R.drawable.al_image);
                break;
            case "zn":
                imageView.setImageResource(R.drawable.zn_image);
                break;
            case "ga":
                imageView.setImageResource(R.drawable.ga_image);
                break;
            case "cd":
                imageView.setImageResource(R.drawable.cd_image);
                break;
            case "in":
                imageView.setImageResource(R.drawable.in_image);
                break;
            case "sn":
                imageView.setImageResource(R.drawable.sn_image);
                break;
            case "hg":
                imageView.setImageResource(R.drawable.hg_image);
                break;
            case "tl":
                imageView.setImageResource(R.drawable.tl_image);
                break;
            case "pb":
                imageView.setImageResource(R.drawable.pb_image);
                break;
            case "bi":
                imageView.setImageResource(R.drawable.bi_image);
                break;
            case "po":
                imageView.setImageResource(R.drawable.po_image);
                break;
            case "cn":
                imageView.setImageResource(R.drawable.cn_image);
                break;

            // Metalloids (B, Si, Ge, As, Sb, Te, At)
            case "b":
                imageView.setImageResource(R.drawable.b_image);
                break;
            case "si":
                imageView.setImageResource(R.drawable.si_image);
                break;
            case "ge":
                imageView.setImageResource(R.drawable.ge_image);
                break;
            case "as":
                imageView.setImageResource(R.drawable.as_image);
                break;
            case "sb":
                imageView.setImageResource(R.drawable.sb_image);
                break;
            case "te":
                imageView.setImageResource(R.drawable.te_image);
                break;
            case "at":
                imageView.setImageResource(R.drawable.at_image);
                break;

            // Superheavy Elements (Mt, Ds, Rg, Nh, Fl, Mc, Lv, Ts, Og)
            case "mt":
                imageView.setImageResource(R.drawable.mt_image);
                break;
            case "ds":
                imageView.setImageResource(R.drawable.ds_image);
                break;
            case "rg":
                imageView.setImageResource(R.drawable.rg_image);
                break;
            case "nh":
                imageView.setImageResource(R.drawable.nh_image);
                break;
            case "fl":
                imageView.setImageResource(R.drawable.fl_image);
                break;
            case "mc":
                imageView.setImageResource(R.drawable.mc_image);
                break;
            case "lv":
                imageView.setImageResource(R.drawable.lv_image);
                break;
            case "ts":
                imageView.setImageResource(R.drawable.ts_image);
                break;

            default:
                imageView.setImageResource(R.drawable.question); // Default fallback pag walang picture
                break;
        }
    }

    private void initializeElementTrivia() {
        elementTrivia = new HashMap<>();

        // Reactive Nonmetals - Fun trivia facts
        elementTrivia.put(R.id.imageButtonH, "💡 Fun Fact: Hydrogen is so light that it escapes Earth's atmosphere and is constantly being lost to space!");
        elementTrivia.put(R.id.imageButtonC, "🌟 Amazing: A single carbon atom can form up to 4 bonds, making it the backbone of all life on Earth!");
        elementTrivia.put(R.id.imageButtonN, "⚡ Cool: Lightning converts nitrogen in the air into compounds that plants can use as fertilizer!");
        elementTrivia.put(R.id.imageButtonO, "🔥 Wow: Oxygen makes things burn, but it doesn't burn itself - it's the supporter, not the fuel!");
        elementTrivia.put(R.id.imageButtoF, "⚠️ Extreme: Fluorine is so reactive it can make sand catch fire and burn through glass!");
        elementTrivia.put(R.id.imageButtonP, "💀 Creepy: White phosphorus glows in the dark and was once used to make matches that could ignite by friction!");
        elementTrivia.put(R.id.imageButtonS, "🌋 Hot: Sulfur creates the rotten egg smell and forms beautiful yellow crystals near volcanoes!");
        elementTrivia.put(R.id.imageButtonCl, "🏊 Essential: Chlorine kills bacteria in swimming pools, but too much can turn your hair green!");
        elementTrivia.put(R.id.imageButtonSe, "🌙 Lunar: Selenium's electrical conductivity changes dramatically with light exposure!");
        elementTrivia.put(R.id.imageButtonBr, "🌊 Salty: Bromine gets its name from 'bromos' meaning stench - it really smells bad!");
        elementTrivia.put(R.id.imageButtonI, "🦋 Purple: When heated, iodine skips the liquid phase and goes straight from solid to purple gas!");

        // Noble Gases - Inert but interesting
        elementTrivia.put(R.id.imageButtonHe, "🎈 Light: Helium makes balloons float and your voice squeaky because sound travels faster through it!");
        elementTrivia.put(R.id.imageButtonNe, "🌃 Bright: Neon signs glow orange-red naturally - other colors need different gases or coatings!");
        elementTrivia.put(R.id.imageButtonAr, "🔥 Safe: Argon is used in light bulbs because it won't react with the hot tungsten filament!");
        elementTrivia.put(R.id.imageButtonKr, "🦸 Super: Krypton was named after the Greek word for 'hidden' - just like Superman's home planet!");
        elementTrivia.put(R.id.imageButtonXe, "💤 Sleepy: Xenon can be used as an anesthetic and makes an incredibly bright camera flash!");
        elementTrivia.put(R.id.imageButtonRn, "☢️ Dangerous: Radon is the second leading cause of lung cancer after smoking!");

        // Alkali Metals - Explosive personalities
        elementTrivia.put(R.id.imageButtonLi, "🔋 Energetic: Your phone battery probably contains lithium - it's the secret to long-lasting power!");
        elementTrivia.put(R.id.imageButtonNa, "💥 Explosive: Sodium explodes violently in water, creating hydrogen gas and lots of heat!");
        elementTrivia.put(R.id.imageButtonK, "🍌 Healthy: Bananas are radioactive because of their potassium content - but don't worry, it's harmless!");
        elementTrivia.put(R.id.imageButtonRb, "⏰ Precise: Rubidium atomic clocks are so accurate they won't lose a second in 300 million years!");
        elementTrivia.put(R.id.imageButtonCs, "📏 Standard: One second is officially defined by cesium atoms vibrating 9,192,631,770 times!");
        elementTrivia.put(R.id.imageButtonFr, "👻 Rare: At any given moment, there are probably only 20-30 grams of francium on entire Earth!");

        // Alkaline Earth Metals
        elementTrivia.put(R.id.imageButtonBe, "💎 Precious: Beryl containing beryllium creates emeralds and aquamarines - some of the most valuable gems!");
        elementTrivia.put(R.id.imageButtonMg, "📸 Flash: Magnesium burns with such a bright white light it was used in old camera flash bulbs!");
        elementTrivia.put(R.id.imageButtonCa, "🦴 Strong: Your bones and teeth are essentially calcium carbonate warehouses!");
        elementTrivia.put(R.id.imageButtonSr, "🎆 Red: Strontium creates the brilliant red colors in fireworks and emergency flares!");
        elementTrivia.put(R.id.imageButtonBa, "🩻 Medical: You drink barium sulfate before X-rays so doctors can see your digestive system!");
        elementTrivia.put(R.id.imageButtonRa, "☢️ Glow: Radium was once used in glow-in-the-dark paint until people realized it was deadly!");

        // Transition Metals Period 4
        elementTrivia.put(R.id.imageButtonSc, "💡 Bright: Scandium is used in bright stadium lights!");
        elementTrivia.put(R.id.imageButtonTi, "🚀 Aerospace: Titanium is strong as steel but 45% lighter - perfect for spacecraft!");
        elementTrivia.put(R.id.imageButtonV, "🚴 Stiff: Vanadium strengthens steel and is used in bike frames for its stiffness!");
        elementTrivia.put(R.id.imageButtonCr, "✨ Shiny: Chromium gives stainless steel its shine and resistance to rust!");
        elementTrivia.put(R.id.imageButtonMn, "💪 Tough: Manganese makes steel tougher and is essential for bone development!");
        elementTrivia.put(R.id.imageButtonFe, "🩸 Life: Iron in your blood carries oxygen, and iron in Earth's core creates our magnetic field!");
        elementTrivia.put(R.id.imageButtonCo, "💙 Blue: Cobalt creates beautiful blue pigments in glass and paints!");
        elementTrivia.put(R.id.imageButtonNi, "💰 Coins: Nickel is used in many coins and is highly resistant to corrosion!");
        elementTrivia.put(R.id.imageButtonCu, "🔌 Conductor: Copper conducts electricity so well that most electrical wiring is made from it!");
        elementTrivia.put(R.id.imageButtonZn, "🛡️ Protective: Zinc protects steel from rusting and is vital for your immune system!");

        // Transition Metals Period 5
        elementTrivia.put(R.id.imageButtonY, "📺 Colors: Yttrium is used to make the red color in older TV screens!");
        elementTrivia.put(R.id.imageButtonZr, "💎 Cubic: Zirconium is the base for cubic zirconia, a popular diamond substitute!");
        elementTrivia.put(R.id.imageButtonNb, "🚄 Super: Niobium is used in superconducting magnets for MRI machines and high-speed trains!");
        elementTrivia.put(R.id.imageButtonMo, "🌱 Essential: Molybdenum is essential for plants and animals, helping enzymes function!");
        elementTrivia.put(R.id.imageButtonTc, "🌌 Cosmic: Technetium is the lightest element with no stable isotopes and is found in red giant stars!");
        elementTrivia.put(R.id.imageButtonRu, "🖊️ Durable: Ruthenium makes pen nibs and electrical contacts extremely durable!");
        elementTrivia.put(R.id.imageButtonRh, "🚗 Clean: Rhodium is a key component in catalytic converters, cleaning car exhaust!");
        elementTrivia.put(R.id.imageButtonPd, "💍 Jewelry: Palladium is a popular, lightweight alternative to platinum for jewelry!");
        elementTrivia.put(R.id.imageButtonAg, "🦠 Antibacterial: Silver naturally kills bacteria, which is why wealthy people used silver utensils!");
        elementTrivia.put(R.id.imageButtonCd, "🔋 Recharge: Cadmium was once used in rechargeable batteries, but now it's mostly in solar cells!");

        // Transition Metals Period 6
        elementTrivia.put(R.id.imageButtonLa, "📸 Lenses: Lanthanum is used in camera lenses for its high refractive index!");
        elementTrivia.put(R.id.imageButtonHf, "🚀 Rockets: Hafnium is used in rocket nozzles because it has an incredibly high melting point!");
        elementTrivia.put(R.id.imageButtonTa, "📱 Mini: Tantalum is used in tiny capacitors found in nearly every electronic device!");
        elementTrivia.put(R.id.imageButtonW, "💡 Filament: Tungsten has the highest melting point of all metals, making it perfect for light bulb filaments!");
        elementTrivia.put(R.id.imageButtonRe, "🛰️ Space: Rhenium is one of the densest metals and is used in superalloys for jet engines and rockets!");
        elementTrivia.put(R.id.imageButtonOs, "✒️ Hard: Osmium is the densest naturally occurring element and is used in fountain pen nibs!");
        elementTrivia.put(R.id.imageButtonIr, "🌠 Asteroid: Iridium is exceptionally corrosion-resistant and is found in meteorites!");
        elementTrivia.put(R.id.imageButtonPt, "💎 Precious: Platinum is even rarer and more valuable than gold, often used in fine jewelry!");
        elementTrivia.put(R.id.imageButtonAu, "👑 Eternal: Gold doesn't rust, tarnish, or corrode - it stays shiny forever!");
        elementTrivia.put(R.id.imageButtonHg, "🌡️ Liquid: Mercury is the only metal that is liquid at room temperature!");

        // Post-Transition Metals
        elementTrivia.put(R.id.imageButtonAl, "♻️ Recyclable: Aluminum can be recycled indefinitely without losing quality - most cans are 70% recycled!");
        elementTrivia.put(R.id.imageButtonGa, "🖐️ Melting: Gallium melts in your hand, making it a popular science trick!");
        elementTrivia.put(R.id.imageButtonIn, "💡 Screens: Indium is used to make transparent conductive coatings for touchscreens and solar panels!");
        elementTrivia.put(R.id.imageButtonSn, "🥫 Cans: Tin is used as a protective coating for steel cans, preventing rust!");
        elementTrivia.put(R.id.imageButtonTl, "🧪 Poison: Thallium is a highly toxic heavy metal once used in rat poison!");
        elementTrivia.put(R.id.imageButtonPb, "☢️ Shield: Lead is excellent at blocking radiation, but it's toxic, so its use has declined!");
        elementTrivia.put(R.id.imageButtonBi, "🔥 Fire: Bismuth has a low melting point and is used in fire sprinklers and fuses!");
        elementTrivia.put(R.id.imageButtonPo, "⚡ Static: Polonium is highly radioactive and was used to remove static electricity in textile mills!");

        // Metalloids
        elementTrivia.put(R.id.imageButtonB, "🌱 Green: Boron is essential for plant growth and is found in borax laundry detergent!");
        elementTrivia.put(R.id.imageButtonSi, "💻 Digital: Silicon chips in your computer contain transistors smaller than viruses!");
        elementTrivia.put(R.id.imageButtonGe, "💡 Early: Germanium was used in early transistors before silicon became more common!");
        elementTrivia.put(R.id.imageButtonAs, "☠️ Historic: Arsenic is famously poisonous and was historically used in pigments and medicines!");
        elementTrivia.put(R.id.imageButtonSb, "👁️‍🗨️ Ancient: Antimony was used by ancient Egyptians as an eyeliner called kohl!");
        elementTrivia.put(R.id.imageButtonTe, " thermoelectric: Tellurium can convert heat directly into electricity!");

        // Lanthanides (Rare Earth Elements)
        elementTrivia.put(R.id.imageButtonCe, "💡 Lighter: Cerium is used in self-cleaning ovens and mischmetal for lighter flints!");
        elementTrivia.put(R.id.imageButtonPr, "👓 Goggles: Praseodymium is used in welding goggles to filter out intense yellow light!");
        elementTrivia.put(R.id.imageButtonNd, "🧲 Strong: Neodymium makes incredibly strong magnets used in headphones and wind turbines!");
        elementTrivia.put(R.id.imageButtonPm, "⏱️ Glow: Promethium is radioactive and glows, used in some specialized luminous paints!");
        elementTrivia.put(R.id.imageButtonSm, "🛡️ Neutron: Samarium is a strong neutron absorber, used in nuclear reactor control rods!");
        elementTrivia.put(R.id.imageButtonEu, "🔴 Red: Europium creates the vibrant red color in TV screens and fluorescent lamps!");
        elementTrivia.put(R.id.imageButtonGd, " MRI: Gadolinium enhances MRI images, making them clearer for medical diagnosis!");
        elementTrivia.put(R.id.imageButtonTb, "💡 Green: Terbium creates the bright green color in fluorescent lamps and some lasers!");
        elementTrivia.put(R.id.imageButtonDy, "🖨️ CD: Dysprosium is used in data storage devices like CDs and hard drives!");
        elementTrivia.put(R.id.imageButtonHo, "🔬 Lasers: Holmium has the strongest magnetic moment of any naturally occurring element and is used in lasers!");
        elementTrivia.put(R.id.imageButtonEr, "🌐 Fiber: Erbium is crucial for optical fibers, amplifying signals over long distances for the internet!");
        elementTrivia.put(R.id.imageButtonTm, "💡 Portable: Thulium is used in portable X-ray devices due to its soft gamma rays!");
        elementTrivia.put(R.id.imageButtonYb, "⏰ Atomic: Ytterbium atomic clocks are incredibly precise, losing only a second every billion years!");
        elementTrivia.put(R.id.imageButtonLu, "🩺 Imaging: Lutetium is used in PET scans for medical imaging and cancer detection!");

        // Actinides (Most are radioactive)
        elementTrivia.put(R.id.imageButtonTh, "💡 Bright: Thorium is used in gas lantern mantles, giving off a bright white light!");
        elementTrivia.put(R.id.imageButtonPa, "🧪 Rare: Protactinium is one of the rarest and most expensive naturally occurring elements!");
        elementTrivia.put(R.id.imageButtonU, "⚡ Powerful: One uranium pellet the size of a fingertip contains as much energy as a ton of coal!");
        elementTrivia.put(R.id.imageButtonNp, "🔫 Weapons: Neptunium is produced in nuclear reactors and can be used in nuclear weapons!");
        elementTrivia.put(R.id.imageButtonPu, "💀 Toxic: Plutonium is so toxic that a speck smaller than a grain of sand could be lethal!");
        elementTrivia.put(R.id.imageButtonAm, " smoke: Americium is used in most household smoke detectors!");
        elementTrivia.put(R.id.imageButtonCm, "🔬 Research: Curium is highly radioactive and primarily used for scientific research!");
        elementTrivia.put(R.id.imageButtonBk, "🔬 Lab: Berkelium was the fifth transuranic element discovered and is purely synthetic!");
        elementTrivia.put(R.id.imageButtonCf, " нейтрон: Californium is a strong neutron emitter, used in cancer treatment and for starting nuclear reactors!");
        elementTrivia.put(R.id.imageButtonEs, "🌌 Cosmos: Einsteinium was discovered in the debris of the first hydrogen bomb explosion!");
        elementTrivia.put(R.id.imageButtonFm, "🔬 Synthetic: Fermium is the heaviest element that can be formed by neutron bombardment!");
        elementTrivia.put(R.id.imageButtonMd, "🔬 Discovery: Mendelevium was the first element discovered atom by atom!");
        elementTrivia.put(R.id.imageButtonNo, "🔬 Lab: Nobelium is a synthetic radioactive element named after Alfred Nobel!");
        elementTrivia.put(R.id.imageButtonLr, "🔬 Heaviest: Lawrencium is the heaviest actinide and is also purely synthetic!");

        // Superheavy Elements (Synthetic, highly unstable)
        elementTrivia.put(R.id.imageButtonRf, "🔬 Synthetic: Rutherfordium was named after Ernest Rutherford, the father of nuclear physics!");
        elementTrivia.put(R.id.imageButtonDb, "🔬 Lab: Dubnium was named after Dubna, Russia, a major nuclear research center!");
        elementTrivia.put(R.id.imageButtonSg, "🔬 Lab: Seaborgium was named after Glenn T. Seaborg, a Nobel Prize-winning chemist!");
        elementTrivia.put(R.id.imageButtonBh, "🔬 Lab: Bohrium was named after Niels Bohr, the famous physicist!");
        elementTrivia.put(R.id.imageButtonHs, "🔬 Lab: Hassium was named after the German state of Hesse, where it was first synthesized!");
        elementTrivia.put(R.id.imageButtonMt, "🔬 Lab: Meitnerium was named after Lise Meitner, a pioneering physicist!");
        elementTrivia.put(R.id.imageButtonDs, "🔬 Lab: Darmstadtium was named after Darmstadt, Germany, where it was first synthesized!");
        elementTrivia.put(R.id.imageButtonRg, "🔬 Lab: Roentgenium was named after Wilhelm Conrad Röntgen, discoverer of X-rays!");
        elementTrivia.put(R.id.imageButtonCn, "🔬 Lab: Copernicium was named after Nicolaus Copernicus, the astronomer!");
        elementTrivia.put(R.id.imageButtonNh, "🔬 Lab: Nihonium is named after 'Nihon', one of the two ways to say Japan in Japanese!");
        elementTrivia.put(R.id.imageButtonFl, "🔬 Lab: Flerovium was named after the Flerov Laboratory of Nuclear Reactions in Russia!");
        elementTrivia.put(R.id.imageButtonMc, "🔬 Lab: Moscovium is named after the Moscow region of Russia!");
        elementTrivia.put(R.id.imageButtonLv, "🔬 Lab: Livermorium is named after the Lawrence Livermore National Laboratory in the USA!");
        elementTrivia.put(R.id.imageButtonTs, "🔬 Lab: Tennessine is named after Tennessee, USA, where Oak Ridge National Laboratory is located!");
        elementTrivia.put(R.id.imageButtonOg, "🔬 Lab: Oganesson is named after Yuri Oganessian, a leading researcher in superheavy elements!");

    }

}