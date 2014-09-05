package com.yahoo.bshivani.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity {
	public EditText etAmount;
	public TextView tvTipAmount;
	public TextView tvTipIsLabel;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        etAmount = (EditText) findViewById(R.id.etAmount);
        tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
        tvTipIsLabel = (TextView) findViewById(R.id.tvTipIsLabel);
        tvTipIsLabel.setVisibility(TextView.INVISIBLE);
        tvTipAmount.setVisibility(TextView.INVISIBLE); 
    }
    

    // Click on 10% button to calculate 10% tip of input amount  
    public void OnClick10Perct (View v)
    {
    	// Validation on Amount String
    	if (validateAmount (etAmount.getText().toString()) == false) 
    	{
    		tvTipIsLabel.setVisibility(TextView.INVISIBLE);
            tvTipAmount.setVisibility(TextView.INVISIBLE); 
    		return;
    	}

    	// Calculate 10% Tip and set calculate tip in view
    	int calculatedTip = calculateTipAmount (etAmount.getText().toString(), 10);
    	setTipAmount (calculatedTip);
    }
    

    // Click on 15% button to calculate 15% tip of input amount  
    public void OnClick15Perct (View v)
    {
    	// Validation on Amount String
    	if (validateAmount (etAmount.getText().toString()) == false) 
    	{
    		tvTipIsLabel.setVisibility(TextView.INVISIBLE);
            tvTipAmount.setVisibility(TextView.INVISIBLE); 
    		return;
    	}    	
    	
    	// Calculate 15% Tip and set calculate tip in view
    	int calculatedTip = calculateTipAmount (etAmount.getText().toString(), 15);
    	setTipAmount (calculatedTip);
    }
    
    // Click on 20% button to calculate 20% tip of input amount  
    public void OnClick20Perct (View v)
    {
    	// Validation on Amount String
    	if (validateAmount (etAmount.getText().toString()) == false) 
    	{
    		tvTipIsLabel.setVisibility(TextView.INVISIBLE);
            tvTipAmount.setVisibility(TextView.INVISIBLE); 
    		return;
    	}

    	// Calculate 20% Tip and set calculate tip in view
    	int calculatedTip = calculateTipAmount (etAmount.getText().toString(), 20);
    	setTipAmount (calculatedTip);
    }

    // Calculate Tip as per input tip percentage 
    private int calculateTipAmount (String amountStr, int tipPerct)
    {
    	int intAmount = Integer.parseInt(amountStr);
    	
    	int tipAmount = (intAmount * tipPerct) / 100;		// MAY NEED TO TAKE CARE OF FLOATING POINT OUTPUT 
    	return tipAmount;
    }
    
	// Returns true if Amount is validated 
    private boolean validateAmount (String etAmountStr)
    {
    	etAmountStr = etAmountStr.trim();
    	if (etAmountStr.compareTo("") == 0)
    	{
    		Toast.makeText(getBaseContext(), R.string.err_please_enter_amount, -1).show();
    		return false;
    	}
    	else if (etAmountStr.matches("[0-9]+") == false)
    	{
    		Toast.makeText(getBaseContext(), R.string.err_invalid_input, -1).show();
    		return false;
    	}
    	else if  (etAmountStr.matches("[0]+") == true)
    	{
    		Toast.makeText(getBaseContext(), R.string.err_enter_greater_than_zero, -1).show();
    		return false;
    	}
    	return true;
    }

    private void setTipAmount (int calculateTipAmount)
    {
    	// Set calculated Tip
    	String tipAmountStr = "$" + calculateTipAmount;
    	tvTipAmount.setText(tipAmountStr);

    	// Enable Tip labels
    	tvTipIsLabel.setVisibility(TextView.VISIBLE);
    	tvTipAmount.setVisibility(TextView.VISIBLE);
    }
}
