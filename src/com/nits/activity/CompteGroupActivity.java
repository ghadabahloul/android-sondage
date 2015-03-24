package com.nits.activity;

import android.content.Intent;
import android.os.Bundle;

public class CompteGroupActivity extends TabGroupActivity {
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
startChildActivity("compte", new Intent(this,Compte.class));
}
}