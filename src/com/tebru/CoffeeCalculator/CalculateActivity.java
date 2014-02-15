package com.tebru.CoffeeCalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Class CalculateActivity
 */
public class CalculateActivity extends Activity {
    /**
     * Amount of coffee needed per ounce of water
     */
    final static private float GRAM_COFFEE_PER_OUNCE = 1.667f;

    /**
     * Weight of one ounce of water in grams
     */
    final static private float WEIGHT_WATER_GRAM_PER_OUNCE = 28.41f;

    /**
     * On activty create
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String amountInOunces = intent.getStringExtra(MainActivity.AMOUNT_OUNCES);

        // run calculations on ounces provided
        float ounces = Float.parseFloat(amountInOunces);
        float coffeeAmount = ounces * GRAM_COFFEE_PER_OUNCE;
        float waterAmount = ounces * WEIGHT_WATER_GRAM_PER_OUNCE;

        // Create the text view
        TextView output = new TextView(this);
        output.setTextSize(20);
        output.setText("Coffee: " + Math.round(coffeeAmount) + "g\n" + "Water: " + Math.round(waterAmount) + "g");

        // Set the text view as the activity layout
        setContentView(output);
    }
}