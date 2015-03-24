package com.nits.activity;

import android.content.Intent;
import android.os.Bundle;

public class SondageGroupActivity extends TabGroupActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	startChildActivity("sondage", new Intent(this,Sondage_Type.class));
	}
	

	
	
	}