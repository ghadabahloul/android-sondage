package com.nits.activity;

import android.content.Intent;
import android.os.Bundle;

public class HistoriqueGroupActivity extends TabGroupActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	startChildActivity("hist", new Intent(this,Historique.class));
	}
	}