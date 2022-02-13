package com.example.tipcalculatorv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

// In version 1, we complete the GUI, adding two labels and two TextViews to display the
// tip amount and total amount and we added one button
// Version 2, we wil add colors, center some text, an add a few style elements
// Version 3, add click event for calculations
// Version 4, the totals are updated after any key is pressed

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f, 100f);
        setContentView(R.layout.activity_main);

        billEditText = findViewById(R.id.et_Bill_Amount);
        tipEditText = findViewById(R.id.et_Enter_Tip);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
    }

    //Called when the user clicks on the Calculate Button
    public void calculate ()
    {

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.tv_Tip_Total);
        TextView totalTextView = findViewById(R.id.tv_Bill_Total);


        try {


            //convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            //update the Model
           tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);

            //Ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            //Update the view with formatted tip and total amounts
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));

            }

        catch (NumberFormatException nfe)
        {
            // alert message
        }

    }

    private class TextChangeHandler implements TextWatcher
    {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            calculate();
        }
    }
}