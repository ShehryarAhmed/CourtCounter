package com.example.android.coffeeorder;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int CoffeePerCup = 5;
    int CoffeeWithWhippedCream = 6;
    int CoffeeWithChocolate = 7;
    int CoffeeWithAllToppping = 8;
    int Quantity = 0;
    String Message;
    boolean WhippedCream;
    boolean Chocolate;

    public void AddCoffeecup(View view){
        ++Quantity;
        TextView textView = (TextView) findViewById(R.id.QuantityTextView);
        if(Quantity<10){
            textView.setText("0" + Quantity);
            TextView CostTextView = (TextView) findViewById(R.id.CostTextView);
            CostTextView.setText("$" + price()+".00");
        }
        else{
        textView.setText(""+Quantity);
        TextView CostTextView = (TextView) findViewById(R.id.CostTextView);
        CostTextView.setText("$"+price()+".00");
    }}
    public void DeleteCoffeecup(View view) {
        --Quantity;
        if (Quantity <= 0) {
            Quantity = 0;
        }
        TextView textView = (TextView) findViewById(R.id.QuantityTextView);
        if(Quantity<10){
        textView.setText("0" + Quantity);
        TextView CostTextView = (TextView) findViewById(R.id.CostTextView);
        CostTextView.setText("$" + price()+".00");
    }else{
        textView.setText("" + Quantity);
        TextView CostTextView = (TextView) findViewById(R.id.CostTextView);
        CostTextView.setText("$" + price()+".00");
    }}
    private void setTopping(String message){
        this.Message =  message;
    }
    private String getToppig(){
        return Message;
    }
    private int Calculation(int Quantity,boolean hasWhipped,boolean hasChocolate){
        if(!hasWhipped && !hasChocolate){
            setTopping("With Out Topping.");
            return (Quantity*CoffeePerCup);
        }
        else if(hasWhipped && !hasChocolate){
            setTopping("With Whipped Cream.");
            return (Quantity*CoffeeWithWhippedCream);
        }
        else if(!hasWhipped && hasChocolate){
            setTopping("With Chocolate.");
            return (Quantity*CoffeeWithChocolate);
        }
        else {
            setTopping("With Whipped Cream And Chocolate.");
            return (Quantity*CoffeeWithAllToppping);
        }
    }
    private int price(){
        CheckBox hasWhippedCream = (CheckBox)findViewById(R.id.checkboxForWhippedCream);
        WhippedCream = hasWhippedCream.isChecked();
        CheckBox hasChocolate = (CheckBox)findViewById(R.id.checkboxForChocolate);
        Chocolate = hasChocolate.isChecked();
        int Price = Calculation(Quantity,WhippedCream,Chocolate);
        return Price;

    }
    public void OrderSummary(View view){
        EditText editText = (EditText) findViewById(R.id.name);
        String name =  editText.getText().toString();

        TextView CostTextView = (TextView) findViewById(R.id.CostTextView);

        TextView PriceLabel = (TextView) findViewById(R.id.PriceLabel);
        PriceLabel.setText("Order Summar");

    if(name.matches("")){
        Toast toast = Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT);
        toast.show();
    }
    else {
        String OrderSummary = "Name :"+name+"\n"+getToppig()+"\nQuantity " + Quantity+"\nPrice $"+ price() +"\nThank You";
        CostTextView.setText(OrderSummary);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+"Shehryar.ahmed@nhu.edu.pk"));

        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order From "+name);
        intent.putExtra(Intent.EXTRA_TEXT,OrderSummary);
        intent = Intent.createChooser(intent,"sending Email");
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

    }

    }

}
