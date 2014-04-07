package com.tebru.CoffeeCalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
