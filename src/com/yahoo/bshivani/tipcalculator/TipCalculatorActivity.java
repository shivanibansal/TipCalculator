package com.yahoo.bshivani.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity {
	public EditText etAmount;
	public EditText etCustomTip;
	public EditText etSplitNum; 
	public TextView tvSplitLabel;
	public TextView tvSplitPerPersonLabel;
	public TextView tvAmoutPerPerson;
	public TextView tvTipIsLabel;
	public TextView tvTipAmount;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        etAmount = (EditText) findViewById(R.id.etAmount);
        etCustomTip = (EditText) findViewById(R.id.etCustomTip);
        etSplitNum = (EditText) findViewById(R.id.etSplitNum);
        tvSplitLabel = (TextView) findViewById(R.id.tvSplitLabel);
        tvSplitPerPersonLabel = (TextView) findViewById(R.id.tvSplitPerPersonLabel);
        tvAmoutPerPerson = (TextView) findViewById(R.id.tvAmoutPerPerson);
        tvTipIsLabel = (TextView) findViewById(R.id.tvTipIsLabel);
        tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);

        etSplitNum.setText("1");
        tvAmoutPerPerson.setText("");
        setVisibilityForTipSplitControls (false);
    }


    // Click on 10% button to calculate 10% tip of input amount  
    public void OnClick10Perct (View v)
    {
    	// Validation on Amount String
    	if (validateData () == false) 
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
    	if (validateData () == false) 
    	{
    		setVisibilityForTipSplitControls (false);
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
    	if (validateData () == false) 
    	{
    		setVisibilityForTipSplitControls (false);
    		return;
    	}

    	// Calculate 20% Tip and set calculate tip in view
    	int calculatedTip = calculateTipAmount (etAmount.getText().toString(), 20);
    	setTipAmount (calculatedTip);
    }
    
    // Click on custom percentage button to calculate custom % tip of input amount  
    public void OnClickCustomPerct (View v)
    {
    	// Validation on Amount String
    	if (validateData () == false) 
    	{
    		setVisibilityForTipSplitControls (false);
    		return;
    	}
    	String etCustomTipStr = etCustomTip.getText().toString();
    	// Validate Custom Tip
    	if ((etCustomTipStr.compareTo("") == 0) || (etCustomTipStr.matches("[0]+") == true))
    	{
			Toast.makeText(getBaseContext(), R.string.err_please_custom_tip, -1).show();
			setVisibilityForTipSplitControls (false);
    		return;
		}
	
    	int iCustomTip = Integer.parseInt(etCustomTip.getText().toString());
    	// Calculate custom percent Tip and set calculate tip in view
    	int calculatedTip = calculateTipAmount (etAmount.getText().toString(), iCustomTip);
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
    private boolean validateData ()
    {
    	/* Validation of Input amount */
    	{
    		String etAmountStr = etAmount.getText().toString().trim();
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
    	
    		/* Validation of nuMSplit */
    		String etSplitNumStr = etSplitNum.getText().toString().trim();
    		if ((etSplitNumStr.compareTo("") == 0) || (etSplitNumStr.matches("[0]+") == true))
    		{
    			etSplitNum.setText("1");
    		}
    		else if (etSplitNumStr.matches("[0-9]+") == false)
    		{
    			Toast.makeText(getBaseContext(), R.string.err_invalid_input, -1).show();
    			return false;
    		}
    	}
    	return true;
    }


/* TODO HAVE TO PUT LISTENER ON ENTERED AMOUNT and NUM SPLIT */
    private void setTipAmount (int calculateTipAmount)
    {
    	// Set calculated Tip
    	String tipAmountStr = "$" + calculateTipAmount;
    	tvTipAmount.setText(tipAmountStr);
    	
    	int numSplit = Integer.parseInt(etSplitNum.getText().toString());
    	
    	String tvAmoutPerPersonStr = "";
    	if (numSplit == 1)
    	{
    		tvAmoutPerPersonStr = "$" + calculateTipAmount;
    	} else {
    		tvAmoutPerPersonStr = "$" + (calculateTipAmount/numSplit);
    	}
    	tvAmoutPerPerson.setText(tvAmoutPerPersonStr);

    	// Enable Tip labels
    	setVisibilityForTipSplitControls (true);
    }


    /* Enable / Disable Total Tips and Split Per person controls */
    private void setVisibilityForTipSplitControls (boolean setToVisibleState){
    	if (setToVisibleState == true)
    	{
    		tvTipIsLabel.setVisibility(TextView.VISIBLE);
            tvTipAmount.setVisibility(TextView.VISIBLE);
            tvSplitPerPersonLabel.setVisibility(TextView.VISIBLE);
            tvAmoutPerPerson.setVisibility(TextView.VISIBLE);
    	} else {
    		tvTipIsLabel.setVisibility(TextView.INVISIBLE);
            tvTipAmount.setVisibility(TextView.INVISIBLE);
            tvSplitPerPersonLabel.setVisibility(TextView.INVISIBLE);
            tvAmoutPerPerson.setVisibility(TextView.INVISIBLE);    		
    	}
    }
}

