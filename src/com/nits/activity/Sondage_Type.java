package com.nits.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Sondage_Type extends Activity {
	Button btncontinuer;
	//Spinner Spinnertype;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.sondage_type, null);
		this.setContentView(viewToLoad); 
		
		 // Font path
        String fontPath = "Face Your Fears.ttf";
 
        // text view label
        TextView txt = (TextView) findViewById(R.id.textSondage);
        TextView txt2 = (TextView) findViewById(R.id.textSondage2);
        
 
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
 
        // Applying font
        txt.setTypeface(tf);
        txt2.setTypeface(tf);
        
		
		btncontinuer =(Button)findViewById(R.id.btncontinuer);
		// Spinnertype = (Spinner) findViewById(R.id.spinnertype);
		 
		// ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.Sondage_array, android.R.layout.simple_spinner_item);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 
		 
		// Spinnertype.setAdapter(adapter);
		// Spinnertype.setSelection(0);
			
		 btncontinuer.setOnClickListener(new View.OnClickListener() {			
				public void onClick(View view) {
			
					Intent i = new Intent(getParent(),
							SondageListe.class);
					//startActivity(i);
					TabGroupActivity parentActivity = (TabGroupActivity)getParent();
	                parentActivity.startChildActivity("sondageGeneral", i); 
				}
					
					
					
					
				/*	if (Spinnertype.getSelectedItem().equals("General")){
						
						Intent i = new Intent(getParent(),
								AndroidJSONParsingActivity.class);
						TabGroupActivity parentActivity = (TabGroupActivity)getParent();
		                parentActivity.startChildActivity("sondageGeneral", i); 
		              
					}
					
					else
					{
						
						Intent i = new Intent(getParent(),
								Sondage_spec.class);
						TabGroupActivity parentActivity = (TabGroupActivity)getParent();
		                parentActivity.startChildActivity("sondageSpec", i); 
		              	
						
					}
					
					
					
				}
		 });
		*/
		
		 });	}
	
	
	
}
