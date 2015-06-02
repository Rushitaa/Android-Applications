package com.example.rushitaa.mortgage_calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends ActionBarActivity {

    private static final String LOAN_AMOUNT = "LOAN_AMOUNT";
    private static final String CUSTOM_INTEREST_RATE = "CUSTOM_INTEREST_RATE";

    private double currentLoanAmount ;
    private EditText loanEditText;

    private double currentCustomRate;
    private TextView customRateTextView;

    private EditText display;


    private double term = 0;
    private double tax = 0.00;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( savedInstanceState == null )
        {
            currentLoanAmount = 0.0;
            currentCustomRate = 5.00;
        }
        else
        {
            currentLoanAmount = savedInstanceState.getDouble(LOAN_AMOUNT);
            currentCustomRate = savedInstanceState.getDouble(CUSTOM_INTEREST_RATE);
        }
        customRateTextView = (TextView)findViewById(R.id.customRateTextView);

        loanEditText = (EditText) findViewById(R.id.loanEditText);
        loanEditText.addTextChangedListener(loanEditTextWatcher);
        display = (EditText) findViewById(R.id.PaymentEditText);

        SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);

        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
        final Button button = (Button) findViewById(R.id.calculateButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                updateMonthlyPayment();
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    term = 7;
                break;
            case R.id.radioButton2:
                if (checked)
                    term = 15;
                break;
            case R.id.radioButton3:
                if(checked)
                    term = 30;
                break;
        }
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked)
                    tax = 0.001;
                else
                    tax = 0;
                break;
        }
    }

    private double formula(double loan, double rate) {
        double taxes = tax * loan;
        double c = rate / 1200.;
        double n = term * 12;
        if(loan!=0.00 && n!=0.00) {
            if (rate != 0) {
                return ((loan * (c / (1 - (Math.pow(1 + c, (-n)))))) + taxes);
            } else {
                return ((loan / n) + taxes);
            }
        }
        else
        {
            return 0.00;
        }

    }
    private void updateMonthlyPayment(){
        double monthly_payment = formula(currentLoanAmount,currentCustomRate);
        display.setText("$" + String.format("%.02f",monthly_payment ));
    }
    private void updateCustomRate()
    {
        customRateTextView.setText(String.format("%.02f", currentCustomRate) + "%");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putDouble(LOAN_AMOUNT, currentLoanAmount);
        outState.putDouble(CUSTOM_INTEREST_RATE, currentCustomRate);
    }

    private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener()
    {

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {

            currentCustomRate = seekBar.getProgress() / 100.0;

            updateCustomRate();
        }

        public void onStartTrackingTouch(SeekBar seekBar)
        {}

        public void onStopTrackingTouch(SeekBar seekBar)
        {}
    };
    private TextWatcher loanEditTextWatcher = new TextWatcher()
    {

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {   double a = 0.00;
            display.setText("$" + String.format("%.02f",a));
            try
            {
                currentLoanAmount = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e)
            {
                currentLoanAmount = 0.0;
            }


           // updateMonthlyPayment();
        }

        public void afterTextChanged(Editable s)
        {}

        public void beforeTextChanged(CharSequence s, int start, int count,int after)
        {

        }
    };



}
