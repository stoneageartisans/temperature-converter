package com.stoneageartisans.temperatureconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class TemperatureConverter extends Activity {
	
	
	// Variables
	private EditText edittext_celsius;
	private EditText edittext_fahrenheit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.layout_main);
		initialize();
		
	}
	
	@Override
	public void onResume() {
		
	    super.onResume();
	    
	}
	
	@Override
	public void onPause() {
		
	    super.onPause();
	    
	}
	
	@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    	
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.putString( "celsius", edittext_celsius.getText().toString() );
    	savedInstanceState.putString( "fahrenheit", edittext_fahrenheit.getText().toString() );
    	
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	
    	super.onRestoreInstanceState(savedInstanceState);
    	edittext_celsius.setText( savedInstanceState.getString("celsius") );
    	edittext_fahrenheit.setText( savedInstanceState.getString("fahrenheit") );
    	
    }
	
	private void initialize() {
		        
        int font_size = 20;
        
        // Set title font size
        ( (TextView) this.findViewById(R.id.title) ).setTextSize(font_size);
        
        // Set label font size
        ( (TextView) this.findViewById(R.id.label_celsius) ).setTextSize(font_size);
        ( (TextView) this.findViewById(R.id.label_fahrenheit) ).setTextSize(font_size);
        
        // Set up input/output fields
        edittext_celsius = (EditText) this.findViewById(R.id.celsius);
        edittext_celsius.setTextSize(font_size);
        
        edittext_fahrenheit = (EditText) this.findViewById(R.id.fahrenheit);
        edittext_fahrenheit.setTextSize(font_size);
        
        Button button_convert = (Button) this.findViewById(R.id.convert);
        button_convert.setTextSize(font_size);
        button_convert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if( edittext_celsius.hasFocus() ) {
					getFahrenheit();
				} else if ( edittext_fahrenheit.hasFocus() ) {
					getCelsius();
				}
			}    		
    	});
        
        edittext_celsius.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,	KeyEvent event) {				
				getFahrenheit();
				return false;
			}
        });
        
        edittext_celsius.setOnFocusChangeListener(new OnFocusChangeListener() {			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) {
					edittext_fahrenheit.setText("");
				}
			}
		});
        
        edittext_fahrenheit.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,	KeyEvent event) {				
				getCelsius();
				return false;
			}
        });
        
        edittext_fahrenheit.setOnFocusChangeListener(new OnFocusChangeListener() {			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) {
					edittext_celsius.setText("");
				}
			}
		});
                
    }
	
	private void getCelsius() {
    	
    	try {
    		BigDecimal result = new BigDecimal( ( ( Float.parseFloat( edittext_fahrenheit.getText().toString() ) - 32 ) * 5 ) / 9 ).setScale(1, RoundingMode.HALF_UP);
    		edittext_celsius.setText( result.toString() );
    	} catch(NumberFormatException ex) {
    		edittext_celsius.setText("");
		}
    	
    }
	
	private void getFahrenheit() {
    	
    	try {
    		BigDecimal result = new BigDecimal( ( ( Float.parseFloat( edittext_celsius.getText().toString() ) * 9) / 5 ) + 32 ).setScale(1, RoundingMode.HALF_UP);
    		edittext_fahrenheit.setText( result.toString() );
    	} catch(NumberFormatException ex) {
    		edittext_fahrenheit.setText("");
    	}
    	
    }
	
}
