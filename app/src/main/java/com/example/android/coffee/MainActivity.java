package com.example.android.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int i=0;
    public void increment(View view) {
        if(i<100){
            i=i+1;
        }
        display(i);
    }
    public void decrement(View view) {

        if(i>0){
            i=i-1;
        }
        display(i);
    }
    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        CheckBox cream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox choco = (CheckBox) findViewById(R.id.chocolate);
        boolean status_cream = cream.isChecked();
        boolean status_choco = choco.isChecked();
        String cname = name.getText().toString();
        int price = 0;
        if(cream.isChecked()){
            price = price + 1;
        }
        if(choco.isChecked()){
            price = price + 2;
        }
        price = (5+price)*i;
        String priceMessage = createOrderSummary(price, status_cream, status_choco, cname);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "order for"+ cname);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */


    private String createOrderSummary(int number, boolean status_w, boolean status_c, String cname){
        String priceMessage = "Name: "+ cname;
        priceMessage = priceMessage + "\nhas whipped cream: "+ status_w;
        priceMessage = priceMessage + "\nhas chocolate: "+ status_c;
        priceMessage = priceMessage + "\nQuantity: " + i;
        priceMessage = priceMessage + "\nTotal: $" + number;
        priceMessage = priceMessage + "\nThankyou!";
        return priceMessage;
    }

    private void displayMessage(String msg) {
        TextView quantityTextView = (TextView) findViewById(R.id.order_summary_text_view);
        quantityTextView.setText(msg);
    }
}

