package com.yahoo.bshivani.tipcalculator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity {
	public EditText etAmount;
	public Button	btn10Perct;
	public Button	btn15Perct;
	public Button	btn20Perct;
	public Button	btnCustomPerct;
	public EditText etCustomTip;
	public EditText etSplitNum; 
	public TextView tvSplitLabel;
	public TextView tvSplitPerPersonLabel;
	public TextView tvAmoutPerPerson;
	public TextView tvTipIsLabel;
	public TextView tvTipAmount;
	public int		iLastSelectedTipPerct = 0;
	public static 	final	int 	BTN_10_PERCT = 1;
	public static 	final	int 	BTN_15_PERCT = 2;
	public static 	final	int 	BTN_20_PERCT = 3;
	public static 	final	int 	BTN_CUSTOM_PERCT = 4;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        // Various views on Main activity Layout
        etAmount = (EditText) findViewById(R.id.etAmount);
        btn10Perct = (Button) findViewById(R.id.btn10Perct);
        btn15Perct = (Button) findViewById(R.id.btn15Perct);
        btn20Perct = (Button) findViewById(R.id.btn20Perct);
        btnCustomPerct = (Button) findViewById(R.id.btnCustomPerct);
        etCustomTip = (EditText) findViewById(R.id.etCustomTip);
        etSplitNum = (EditText) findViewById(R.id.etSplitNum);
        tvSplitLabel = (TextView) findViewById(R.id.tvSplitLabel);
        tvSplitPerPersonLabel = (TextView) findViewById(R.id.tvSplitPerPersonLabel);
        tvAmoutPerPerson = (TextView) findViewById(R.id.tvAmoutPerPerson);
        tvTipIsLabel = (TextView) findViewById(R.id.tvTipIsLabel);
        tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
        // Initialize few controls
        etSplitNum.setText("1");
        tvAmoutPerPerson.setText("");
        setVisibilityForTipSplitControls (false);
        
        // TextChangesListener on etAmount field
        etAmount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				System.out.println("afterTextChanged : s - " + s.toString());

				// Tip calculated only if previously calculated. 
				// -- Should not update tip very first time
				// -- Should not update if text is null
				if ((iLastSelectedTipPerct == 0) || (s.toString() == null) || (s.toString().trim().compareTo("") == 0)) {
					setVisibilityForTipSplitControls (false);
					return;
				}
				// Calculate 10% Tip and set calculate tip in view
				int calculatedTip = calculateTipAmount (s.toString(), iLastSelectedTipPerct);
				setTipAmount (calculatedTip);
			}
		});

        // TextChangesListener on etAmount field
        etSplitNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				System.out.println("afterTextChanged : s - " + s.toString());

				// Tip calculated only if previously calculated. 
				// -- Should not update tip very first time
				// -- Should not update if text is null
				if ((iLastSelectedTipPerct == 0) || (s.toString() == null) || (s.toString().trim().compareTo("") == 0)) {
					setVisibilityForTipSplitControls (false);
					return;
				}
				// Calculate 10% Tip and set calculate tip in view
				int calculatedTip = calculateTipAmount (etAmount.getText().toString(), iLastSelectedTipPerct);
				setTipAmount (calculatedTip);
			}
		});
    }

    // Click on 10% button to calculate 10% tip of input amount  
    public void OnClick10Perct (View v)
    {
    	// Validation on Amount String
    	if (validateData () == false) 
    	{
    		setVisibilityForTipSplitControls (false);
    		return;
    	}
    	// Calculate 10% Tip and set calculate tip in view
    	int calculatedTip = calculateTipAmount (etAmount.getText().toString(), 10);
    	setTipAmount (calculatedTip);
     	setButtonPressed(BTN_10_PERCT);
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
    	setButtonPressed(BTN_15_PERCT);
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
    	setButtonPressed(BTN_20_PERCT);
    }
    
    // Click on custom percentage button to calculate custom % tip of input amount  
    public void OnClickCustomPerct (View v)
    {
    	// Validation on Amount String
    	if (validateData () == false)  {
    		return;
    	}
    	String etCustomTipStr = etCustomTip.getText().toString();
    	// Validate Custom Tip
    	if ((etCustomTipStr.compareTo("") == 0) || (etCustomTipStr.matches("[0]+") == true))
    	{
			Toast.makeText(getBaseContext(), R.string.err_please_custom_tip, -1).show();
    		return;
		}

    	int iCustomTip = Integer.parseInt(etCustomTip.getText().toString());
    	// Calculate custom percent Tip and set calculate tip in view
    	int calculatedTip = calculateTipAmount (etAmount.getText().toString(), iCustomTip);
    	setTipAmount (calculatedTip);
    	setButtonPressed(BTN_CUSTOM_PERCT);
    }

    // Calculate Tip as per input tip percentage 
    private int calculateTipAmount (String amountStr, int tipPerct)
    {
    	iLastSelectedTipPerct  = tipPerct;
    	int intAmount = Integer.parseInt(amountStr);	
    	int tipAmount = (intAmount * tipPerct) / 100; 
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

    // Sets calculated Tip amount and set split amount per person 
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

    // Enable/Disable Total Tips and Split Per person controls
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

    // Changes text color of selected button
    private void setButtonPressed(int btnType)
    {
    	btn10Perct.setTextColor(Color.parseColor("#000000"));
    	btn15Perct.setTextColor(Color.parseColor("#000000"));
    	btn20Perct.setTextColor(Color.parseColor("#000000"));
    	btnCustomPerct.setTextColor(Color.parseColor("#000000"));
    	
    	if (btnType == BTN_10_PERCT)
    		btn10Perct.setTextColor(Color.parseColor("#FF0000"));
    	else if (btnType == BTN_15_PERCT)
    		btn15Perct.setTextColor(Color.parseColor("#FF0000"));
    	if (btnType == BTN_20_PERCT)
    		btn20Perct.setTextColor(Color.parseColor("#FF0000"));
    	if (btnType == BTN_CUSTOM_PERCT)
    		btnCustomPerct.setTextColor(Color.parseColor("#FF0000"));
    }
}

