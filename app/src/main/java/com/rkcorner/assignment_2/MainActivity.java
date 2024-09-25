package com.rkcorner.assignment_2;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView tshirtImage;
    private RadioGroup colorGroup;
    private CheckBox sizeSmall, sizeMedium, sizeLarge;
    private RatingBar ratingBar;
    private SeekBar quantitySeekBar;
    private TextView resultTextView, textViewQuantity; // Added TextView for quantity
    private Switch ecoFriendlySwitch;
    private Button changeTshirtButton, submitButton;

    private int[] tshirtImages = {R.drawable.tshirt1, R.drawable.tshirt2, R.drawable.tshirt3, R.drawable.tshirt4, R.drawable.tshirt5, R.drawable.tshirt6};
    private int currentTshirtIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tshirtImage = findViewById(R.id.tshirtImage);
        colorGroup = findViewById(R.id.radioGroupColors);
        sizeSmall = findViewById(R.id.checkboxSmall);
        sizeMedium = findViewById(R.id.checkboxMedium);
        sizeLarge = findViewById(R.id.checkboxLarge);
        ratingBar = findViewById(R.id.ratingBar);
        quantitySeekBar = findViewById(R.id.seekBarQuantity);
        textViewQuantity = findViewById(R.id.textViewQuantity); // Initialized
        ecoFriendlySwitch = findViewById(R.id.switchFeature);
        changeTshirtButton = findViewById(R.id.buttonChange);
        submitButton = findViewById(R.id.buttonSubmit);
        resultTextView = findViewById(R.id.textViewResult);

        tshirtImage.setImageResource(tshirtImages[currentTshirtIndex]);

        // Set the default selected color to black
        RadioButton radioBlack = findViewById(R.id.radioBlack);
        radioBlack.setChecked(true); // Set the "Jersey" color as selected

        // SeekBar listener to update quantity TextView
        quantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewQuantity.setText("Quantity: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Animation for changing T-shirt
        changeTshirtButton.setOnClickListener(v -> {
            currentTshirtIndex = (currentTshirtIndex + 1) % tshirtImages.length;

            // Rotate animation
            RotateAnimation rotate = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(500);
            tshirtImage.startAnimation(rotate);
            tshirtImage.setImageResource(tshirtImages[currentTshirtIndex]);
        });

        // Submit button click listener
        submitButton.setOnClickListener(v -> {
            StringBuilder result = new StringBuilder();
            if (sizeSmall.isChecked()) result.append("Size: Small\n");
            if (sizeMedium.isChecked()) result.append("Size: Medium\n");
            if (sizeLarge.isChecked()) result.append("Size: Large\n");

            // Always set color to black in the result
            result.append("Color: Black\n");

            result.append("Rating: ").append(ratingBar.getRating()).append("\n");
            result.append("Quantity: ").append(quantitySeekBar.getProgress()).append("\n");

            if (ecoFriendlySwitch.isChecked()) {
                result.append("Material: Eco-Friendly\n");
            } else {
                result.append("Material: Standard\n");
            }

            resultTextView.setText(result.toString());

            // Fade-in animation for result
            AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
            fadeIn.setDuration(1000);
            resultTextView.startAnimation(fadeIn);
        });
    }
}