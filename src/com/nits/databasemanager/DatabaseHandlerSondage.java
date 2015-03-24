package com.nits.databasemanager;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandlerSondage extends SQLiteOpenHelper {



// All Static variables
// Database Version
private static final int DATABASE_VERSION = 1;

// Database Name
private static final String DATABASE_NAME = "Sondage_bd";

// Login table name
private static final String TABLE_Sondage = "Sondage";

// Login Table Columns names
private static final String KEY_ID = "id_sondage";
private static final String KEY_Titre = "titre_Sondage";
private static final String KEY_Description = "description_Sondage";
private static final String KEY_Auteur = "auteur_Sondage";
private static final String KEY_Q = "nb_Questions";
private static final String KEY_POINTS = "points";
private static final String KEY_IDQ ="id_Q";
public DatabaseHandlerSondage(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

// Creating Tables
@Override
public void onCreate(SQLiteDatabase db) {
	String CREATE_SONDAGE_TABLE = "CREATE TABLE " + TABLE_Sondage + "("
			+ KEY_ID + " INTEGER PRIMARY KEY," 
			+ KEY_Titre + " TEXT,"
			+ KEY_Description + " TEXT,"
			+ KEY_Auteur + " TEXT,"
			+ KEY_Q + " TEXT,"
			+ KEY_POINTS + " INTEGER "
			+ KEY_IDQ + " INTEGER FOREIGN KEY REFERENCES TABLE_QUESTION (ID_Question),"+ ")";
	db.execSQL(CREATE_SONDAGE_TABLE);
	
	
}

// Upgrading database
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// Drop older table if existed
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_Sondage);

	// Create tables again
	onCreate(db);
}


/**
 * Getting user data from database
 * */
public HashMap<String, String> getSondagesDetails(){
	HashMap<String,String> Sondage = new HashMap<String,String>();
	String selectQuery = "SELECT  * FROM " + TABLE_Sondage;
	 
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
    // Move to first row
    cursor.moveToFirst();
    if(cursor.getCount() > 0){
    	Sondage.put("titre", cursor.getString(1));
    	Sondage.put("Disc", cursor.getString(2));
    	Sondage.put("auteur", cursor.getString(3));
    	Sondage.put("nbQ", cursor.getString(4));
    	Sondage.put("pts", cursor.getString(5));
    	
    	
    	
    }
    cursor.close();
    db.close();
	// return user
	return Sondage ;
}

/**
 * 
 * return true if rows are there in table
 * */
public int getRowCount() {
	String countQuery = "SELECT  * FROM " + TABLE_Sondage;
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.rawQuery(countQuery, null);
	int rowCount = cursor.getCount();
	db.close();
	cursor.close();
	
	// return row count
	return rowCount;
}

/**
 * Re crate database
 * Delete all tables and create them again
 * */
public void resetTables(){
	SQLiteDatabase db = this.getWritableDatabase();
	// Delete All Rows
	db.delete(TABLE_Sondage, null, null);
	db.close();
}

}
