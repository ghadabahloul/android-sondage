package com.nits.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Propos extends Activity  {

	
	Button btnRetour;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.propos);
		btnRetour = (Button) findViewById(R.id.btnRetour);
		btnRetour.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent Retour = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(Retour);
				
			}
		});
	
	
}}
