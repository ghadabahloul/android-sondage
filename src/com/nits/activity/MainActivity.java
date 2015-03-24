package com.nits.activity;


import android.app.TabActivity;
import com.nits.activity.*;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity  {
	TabHost mTabHost;
	
	
	  public static final int ADD_MENU = Menu.FIRST+1;
	  public static final int RESET_MENU = Menu.FIRST+2;
	  //public static final int CAP_MENU = Menu.FIRST+3;
	  //public static final int REMOVE_MENU = Menu.FIRST+4;
	  
	  
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu
	      .add(Menu.NONE, ADD_MENU, Menu.NONE, "Actualiser")
	     
	      .setIcon(R.drawable.refresh);
	    
	    menu
	      .add(Menu.NONE, RESET_MENU, Menu.NONE, "à propos")
	      .setIcon(R.drawable.info);
	    
	    return(super.onCreateOptionsMenu(menu));
	    
	   
	    
	  }
	
	/*  @Override
	  public void onCreateContextMenu(ContextMenu conMenu, View conView,
	                                    ContextMenu.ContextMenuInfo conMenuInfo) {
	    conMenu.add(Menu.NONE, CAP_MENU, Menu.NONE, "Refresh");
	    conMenu.add(Menu.NONE, REMOVE_MENU, Menu.NONE, "About");
	  }*/
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case ADD_MENU:
	    	  
	        return(true);
	      case RESET_MENU:
	    	  Intent m = new Intent(getApplicationContext(),
	  				Propos.class);
	  		startActivity(m);
	        return(true);
	    }
	    return(super.onOptionsItemSelected(item));
	  }
	  
	  
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mTabHost=getTabHost();
	
		
		
		TabSpec SondageSpec =mTabHost.newTabSpec("Sondages"); 
		SondageSpec.setIndicator("Sondages", getResources().getDrawable(R.drawable.sondageicon));
		Intent FirstIntent = new Intent(this,SondageGroupActivity.class);
		SondageSpec.setContent(FirstIntent);
		
		TabSpec HistoriqueSpec =mTabHost.newTabSpec("Historique"); 
	
		HistoriqueSpec.setIndicator("Historique",  getResources().getDrawable(R.drawable.historiqueicon));
		Intent SecondIntent = new Intent(this,HistoriqueGroupActivity.class);
		HistoriqueSpec.setContent(SecondIntent);
		
		
		
		TabSpec CompteSpec =mTabHost.newTabSpec("Compte"); 
		CompteSpec.setIndicator("Compte",  getResources().getDrawable(R.drawable.compteicon));
		Intent thirdIntent = new Intent(this,CompteGroupActivity.class);
		CompteSpec.setContent(thirdIntent);
		

		mTabHost.addTab(SondageSpec);
		mTabHost.addTab(HistoriqueSpec);
		mTabHost.addTab(CompteSpec);
		
		
		
		
		
		
		for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++){
            mTabHost.getTabWidget().getChildAt(i).getLayoutParams().width = 64;
       }



       //provide a method/function for setting height
      // set the Height of tab 
       for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++){
        mTabHost.getTabWidget().getChildAt(i).getLayoutParams().height = 60;
      }

       // set the background color of tab (#50000000-transparent,#7392B5) 
        for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++){
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#50000000")); 
        }
		
		
		mTabHost.setCurrentTab(0);
		
		
	}

	

}
