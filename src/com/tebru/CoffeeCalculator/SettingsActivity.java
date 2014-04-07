package com.tebru.CoffeeCalculator;

import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity {
    final public static String KEY_BREW_STRENGTH = "pref_brewStrength";
    public static final String KEY_AMOUNT_ABSORBED = "pref_amountAbsorbed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}