package com.nits.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class ReponseSucces extends Activity  {
	Button btnRetour;
	Button btnprofil;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (LoginActivity.connectBool== true)
		{
		View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.succeesconnc, null);
		this.setContentView(viewToLoad); 
		
		btnprofil = (Button) findViewById(R.id.btnProfilee);
		
		btnprofil.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View view) {
				
				Intent i = new Intent(getParent(),
						HistoriqueGroupActivity.class);
				TabGroupActivity parentActivity = (TabGroupActivity)getParent();
                parentActivity.startChildActivity("retour", i); 
              
			}
			 
				
			
			
		
			
		});	
		
}else{
	
	
	View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.reponse_succes, null);
	this.setContentView(viewToLoad); 
	
	btnRetour = (Button) findViewById(R.id.btnRetour);
	
	btnRetour.setOnClickListener(new View.OnClickListener() {			
		public void onClick(View view) {
			
			Intent i = new Intent(getParent(),
					SondageListe.class);
			TabGroupActivity parentActivity = (TabGroupActivity)getParent();
            parentActivity.startChildActivity("retour", i); 
          
		}
		 
			
		
		
	
		
	});	
	
	
}
		}}
