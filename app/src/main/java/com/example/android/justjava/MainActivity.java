package com.example.android.justjava; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.justjava.R;
import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int quantity = 0;
    String priceMessage = "";

    public void increment(View view) {
        if (quantity == 50) {
            Toast.makeText(this, "Order Could Not Be Greater Than 50", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            Toast.makeText(this, "Order Could Not Be Less Than 0", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean whippedCream = hasWhippedCream.isChecked();
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean chocolate = hasChocolate.isChecked();
        int price = calculatePrice(whippedCream, chocolate);
        String priceMessage = createOrderSmmary(price, whippedCream, chocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        // intent.putExtra(Intent.EXTRA_EMAIL, "tshevtekar@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String getName() {
        EditText name = (EditText) findViewById(R.id.name_field);
        String nameText = name.getText().toString();
        return nameText;
    }

    private String createOrderSmmary(int price, boolean whippedCream, boolean chocolate) {
        String priceMessage = "Name:" + getName();
        priceMessage += "\nAdd Whipped Cream?-" + whippedCream;
        priceMessage += "\nAdd Chocolate?-" + chocolate;
        priceMessage += "\nQantity:" + quantity;
        priceMessage += "\nTotal:" + price + "rs.";
        return priceMessage += "\nTHANK YOU!";

    }

    private int calculatePrice(Boolean whippedCream, Boolean chocolate) {
        int perCoffee = 10;
        if (whippedCream) {
            perCoffee += 2;
        }
        if (chocolate) {
            perCoffee += 3;
        }
        return quantity * perCoffee;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int noOfCoffee) {
        TextView orderSummary = (TextView) findViewById(R.id.quantity_text_view);
        orderSummary.setText("" + noOfCoffee);
    }
}
    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(String price) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(price);
//    }
//
//}