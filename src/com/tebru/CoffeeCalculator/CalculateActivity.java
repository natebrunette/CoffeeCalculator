package com.tebru.CoffeeCalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class CalculateActivity
 */
public class CalculateActivity extends Activity
{
    /**
     * The expected extraction percentage.  The amount of coffee grounds that will be dissolved into the coffee
     */
    final static private double EXPECTED_EXTRACTION = 0.19;

    /**
     * The amount of coffee to water ratio we want to maintain.  For each unit of coffee, there should be 17.42 units
     * of water.
     */
    final static private double RATIO = 17.42;

    /**
     * Weight of one fluid ounce of water in grams
     */
    final static private double FLUID_OUNCE_TO_GRAM = 29.5735;

    /**
     * On activity create
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the user defined estimated amount of water absorbed
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String amountAbsorbed = sharedPref.getString(SettingsActivity.KEY_AMOUNT_ABSORBED, "");
        double waterAbsorbedMultiplier = Double.parseDouble(amountAbsorbed);

        // calculate the amount of coffee in grams we're brewing
        Intent intent = getIntent();
        String amountInOunces = intent.getStringExtra(MainActivity.AMOUNT_OUNCES);
        double targetYield = Double.parseDouble(amountInOunces) * FLUID_OUNCE_TO_GRAM;

        /*
        We can use the formula yield = [water poured] - [water absorbed] + [grounds extracted] to calculate the amount
        of coffee and water we need if:
            yield: user input
            water absorbed: user defined multiplier * mass of grounds
            grounds extracted: expected extraction percentage * mass of grounds
            water poured: x
            mass of grounds: y

         The formula becomes yield = x - multiplier * y + extraction * y and we solve for x and y knowing that
         x / y = the target ratio

         Example:
            If the expected yield is 13 ounces, the expected absorption is 2.3 times the amount of coffee, the expected
            extraction is 19%, and the target ratio is 17.42:1, we can set up equations like:

            x = 13 + 2.3y + .19y
            x = 17.42y
         */

        double coffeeAmount = targetYield / (RATIO - (waterAbsorbedMultiplier - EXPECTED_EXTRACTION));
        double waterAmount = targetYield + (waterAbsorbedMultiplier * coffeeAmount) + (EXPECTED_EXTRACTION * coffeeAmount);
        double bloomAmount = waterAmount * .10;

        // get values to 2 decimals
        BigDecimal coffee = new BigDecimal(coffeeAmount).setScale(2, RoundingMode.HALF_UP);
        BigDecimal water = new BigDecimal(waterAmount).setScale(2, RoundingMode.HALF_UP);
        BigDecimal bloom = new BigDecimal(bloomAmount).setScale(2, RoundingMode.HALF_UP);

        // Create the text view
        TextView output = new TextView(this);
        output.setTextSize(20);
        output.setText("Coffee: " + coffee + "g\n" +"Bloom: " + bloom + "g\n" + "Total Water: " + water + "g");

        // Set the text view as the activity layout
        setContentView(output);
    }
}