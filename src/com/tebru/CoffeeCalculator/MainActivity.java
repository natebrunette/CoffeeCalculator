package com.tebru.CoffeeCalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Class MainActivity
 *
 * @author Nate Brunette
 */
public class MainActivity extends Activity
{
    /**
     * Intent extra identifier for amount of ounces string
     */
    final static public String AMOUNT_OUNCES = "com.tebru.CoffeeCalculator.AMOUNT_OUNCES";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Send data to calculate activity
     *
     * @param view
     */
    public void calculate(View view) {
        Intent intent = new Intent(this, CalculateActivity.class);
        EditText amountOfCoffee = (EditText) findViewById(R.id.amount_of_coffee);
        String amount = amountOfCoffee.getText().toString();

        intent.putExtra(AMOUNT_OUNCES, amount);
        startActivity(intent);
    }
}
