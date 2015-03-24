package com.nits.activity;

import com.nits.parser.UserFunctions
;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.nits.parser.*;
public class SondageSingleItem  extends Activity {
	UserFunctions userFunctions;
	// JSON node keys
	private static final String TAG_ID = "id_sondage";
	private static final String TAG_Titre = "titre_Sondage";
	private static final String TAG_Description = "description_Sondage";
	private static final String TAG_Auteur = "auteur_Sondage";
	private static final String TAG_Q = "nb_Questions";
	private static final String TAG_POINTS = "points";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        userFunctions = new UserFunctions();
        // getting intent data
        Intent in = getIntent();
        int pos=in.getExtras().getInt("position");
        Toast.makeText(this, "pos"+pos, 5000).show();
        
        
  String idS= SondageListe.sondageList.get(pos).get("id_sondage");
        
        TextView lbltitre = (TextView) findViewById(R.id.titre_label);
        TextView lbldescription = (TextView) findViewById(R.id.description_label);
        TextView lblpoints = (TextView) findViewById(R.id.points_label);
          

        lbltitre.setText(SondageListe.sondageList.get(pos).get("titre_Sondage"));
        lbldescription.setText(SondageListe.sondageList.get(pos).get("description_Sondage"));
        lblpoints.setText(SondageListe.sondageList.get(pos).get("points"));
       
        
    
        
        addListenerOnButton(idS);
	}
        public void addListenerOnButton(final String idS) {   		 
    		final Context context = this;
    		((Button)findViewById(R.id.Participate_btn)).setOnClickListener(new OnClickListener(){
    			@Override
    			public void onClick(View arg0) {
    			//if(userFunctions.isUserLoggedIn(context)== true){
    				
    				if (LoginActivity.connectBool== true) {
    					
    					
    					Intent intent = new Intent(getParent(), SondageActivity.class);
	    			    intent.putExtra("sondageID",idS); 
	    			    intent.putExtra("counteri",-1);
	    			    
	    			    
	    			    TabGroupActivity parentActivity = (TabGroupActivity)getParent();
	                    parentActivity.startChildActivity("aaaa", intent); 
	                 
    					
    				}else{
    				
    				AlertDialog.Builder alertDialog = new AlertDialog.Builder(getParent());
    				 
    		        // Setting Dialog Title
    		        alertDialog.setTitle("Attention");
    		 
    		        // Setting Dialog Message
    		        alertDialog.setMessage("voulez vous participer en tant qu’anonyme ?");
    		 
    		        // Setting Icon to Dialog
    		     //   alertDialog.setIcon(R.drawable.delete);
    		 
    		        // Setting Positive "Yes" Button
    		        alertDialog.setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog,int which) {
    		 
    		         
    		            	

    		Intent intent = new Intent(getParent(), SondageActivity.class);
    		    			    intent.putExtra("sondageID",idS); 
    		    			    intent.putExtra("counteri",-1);
    		    			    
    		    			    
    		    			    TabGroupActivity parentActivity = (TabGroupActivity)getParent();
    		                    parentActivity.startChildActivity("aaaa", intent); 
    		                 
    		            	
    		            	
    		            }
    		        });
    		 
    		        // Setting Negative  Button
    		        alertDialog.setNegativeButton("Se connecter", new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog, int which) {
    		            
    		            	
    		            	Intent intent = new Intent(getParent(), LoginActivity.class);
    		    			    intent.putExtra("sondageID",idS); 
    		    			    intent.putExtra("counteri",-1);
    		    			    
    		    			    
    		    			    TabGroupActivity parentActivity = (TabGroupActivity)getParent();
    		                    parentActivity.startChildActivity("aaaa", intent); 
    		                 
    		            	
    		            }
    		            	
    		            
    		        });
    		            
    		        // Showing Alert Message
    		        alertDialog.show();
          
    			}
    			
    			
    			
    			}    			
    			
    		});
     
    	
        
        
    		
    
}}
