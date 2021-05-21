/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


      CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.notify_me_checkbox) ;
        boolean addWhippedCream = hasWhippedCream.isChecked();

        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
       boolean addChocolate = hasChocolate.isChecked();

     EditText text = (EditText) findViewById(R.id.album_description_view);
     String str = text.getText().toString();

       int price = calculatePrice(addWhippedCream, addChocolate);
       String priceMessage = createOrderSummary(price,addWhippedCream,addChocolate,str);




        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for " + str);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//        pata nhi kyu likh diya hai
            startActivity(intent);
        }




     private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String str){
        String priceMessage = "Name: " + str;
        priceMessage += "\nadd whipped cream? " + addWhippedCream;
        priceMessage += "\nadd Chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity - " +  quantity;
         priceMessage +=  "\nTotal: $  " + price  ;
         priceMessage = priceMessage + "\nThank You!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice ;
        basePrice = 5 ;

        if (addWhippedCream ){
           basePrice = basePrice + 1;
        }
        if (addChocolate ){
            basePrice = basePrice + 2;
        }
        return basePrice * quantity ;
    }


    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity > 100){
            quantity = 100;
            Toast.makeText(this,"You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }
    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity <= 0){
            quantity = 1;
            Toast.makeText(this,"You cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView ordersummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        ordersummaryTextView.setText(message);
//    }



}