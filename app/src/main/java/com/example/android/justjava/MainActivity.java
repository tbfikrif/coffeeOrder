package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, getNameText()));
    }

    private String getNameText() {
        EditText nameEditText = findViewById(R.id.name_edittext);
        return nameEditText.getText().toString();
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * @param addWhippedCream
     * @param addChocolate
     * @return
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream)
            basePrice += 1;

        if (addChocolate)
            basePrice += 2;

        return quantity * basePrice;
    }

    /**
     * Create summary of the order
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {
        String summary = "Name: " + name;
        summary += "\nAdd whipped cream? " + addWhippedCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\nQuantity: " + quantity + "\nTotal: $" + price;
        summary += "\nThank you!";
        return summary;
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 Coffee", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 Coffee", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity -= 1;
        displayQuantity(quantity);
    }
}
