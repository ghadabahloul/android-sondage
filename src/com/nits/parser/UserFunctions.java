package com.nits.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class UserFunctions {
	
	private JSONParser jsonParser;
	final static String IP ="http://10.0.2.2:81/android_login_api/";
	private static String loginURL = "http://10.0.2.2:81/android_login_api/";
	private static String registerURL = "http://10.0.2.2:81/android_login_api/";
	private static String UpdateURL = "http://10.0.2.2:81/android_login_api/";
	private static String GetUserURL ="http://10.0.2.2:81/android_login_api/";
	
	
	private static String StoreAnswerURL = "http://10.0.2.2:81/android_login_api/";
	
	private static String login_tag = "login";
	private static String register_tag = "register";
	private static String Update_tag ="update";
	private static String registerAnswer_tag = "registeranswer";
	private static String getUser_tag ="GetUser";
	
	//private static String infoUser_tag = "info";
	
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// return json
		// Log.e("JSON", json.toString());
		//Log.e("JSoooooologiiiin", json.toString());
		return json;
	}
	
	/**
	 * function make Login Request
	 * @param name
	 * @param email
	 * @param password
	 * */
	public JSONObject registerUser(String name, String Date,String Sexe,String Profession,String email, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("Date", Date));
		params.add(new BasicNameValuePair("Sexe", Sexe));
		params.add(new BasicNameValuePair("Profession", Profession));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		Log.e("JSooooooooooinscriiiii", json.toString());
		return json;
	}
	
	//Register Answers:

	public JSONObject registerAnswers(String Reponse,String id_Question, String uid){
	// Building Parameters
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair("tag", registerAnswer_tag));

	params.add(new BasicNameValuePair("Reponse", Reponse));
	params.add(new BasicNameValuePair("id_Question", id_Question));
	params.add(new BasicNameValuePair("uid", uid));


	// getting JSON Object

	JSONObject json = jsonParser.getJSONFromUrl(StoreAnswerURL,params);
	Log.e("JSooooooooooollllllllloONswerrrrrrrrrrs", json.toString());
	// return json
	return json;
	}
	
	public JSONObject UpdateUser(String name, String Date,String Sexe,String Profession,String email,String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", Update_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("Date", Date));
		params.add(new BasicNameValuePair("Sexe", Sexe));
		params.add(new BasicNameValuePair("Profession", Profession));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(UpdateURL, params);
		Log.e("JSooooooooooollllllllloONuuuuuuuupppdaaate", json.toString());
		// return json
		return json;
	}
	
	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	
	/**
	 * Function to logout user
	 * Reset Database
	 * */
	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
	public JSONObject getUserDetails(String name,String date,String Sexe,String Profession,String email){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", getUser_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("Date", date));
		params.add(new BasicNameValuePair("Sexe", Sexe));
		params.add(new BasicNameValuePair("Profession", Profession));
		params.add(new BasicNameValuePair("email", email));
		
		JSONObject json = jsonParser.getJSONFromUrl(GetUserURL,params);
		// return json
		// Log.e("JSON", json.toString());
		Log.e("JSooooooooood", json.toString());
		return json;
	}
	
	public static String getIp() {
		return IP;
	}
	
	public JSONObject userExists(String email) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("tag", "userExists"));
		params.add(new BasicNameValuePair("email", email));

		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		Log.e("JSoooooooooooooooooooooooooooooooooooooooON", json.toString());
		return json;

	}


public static boolean validEmail(String s){
	
	String Email = s;
	Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
	Matcher m = p.matcher(Email);
	boolean matchFound = m.matches();

	if(matchFound)
		return true;
	else
		return false;
	
}
public static boolean stringValid(String s) {
	
	Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
	
    return p.matcher(s).matches(); 
}







}