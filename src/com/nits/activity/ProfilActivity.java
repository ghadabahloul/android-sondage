package com.nits.activity;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.nits.parser.*;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends Activity{
	
	private static final int PICK_FROM_GALLERY = 2;
	private static final int PICK_FROM_CAMERA = 1;
	ImageView imageProfile;
	
	TextView txtName;
	TextView txtEmail;
	TextView txtDate;
	TextView txtSexe;
	TextView txtProfession;
	TextView txtcreated;
	Button btnUpdate ;
	 private static final String KEY_ID = "uid";
	    private static final String KEY_NAME = "name";
	    private static final String KEY_DATE = "date";
	    private static final String KEY_SEXE = "sexe";
	    private static final String KEY_PROFESSION = "profession";
	    private static final String KEY_EMAIL= "email";
	    private static final String KEY_CREATED_AT = "created_at";
	 
	    
	    
	    
	protected  void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.profil, null);
	    this.setContentView(viewToLoad); 
		
		Log.d("LOG", "Post PROFILE 0" );
		  DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		  //Get user infomation from db to hashmap:
		  HashMap<String, String> userDetailsHash = db.getUserDetails();
		 
		  final String uid = userDetailsHash.get("uid");
		 
		  UserFunctions uf = new UserFunctions();
			JSONObject json = uf.userExists(uid);
			
			
			 
			 
		  imageProfile = (ImageView) findViewById(R.id.imageProfile);
		    txtName = (TextView) findViewById(R.id.txtname);
		    txtDate = (TextView) findViewById(R.id.txtDate);
		    txtSexe = (TextView) findViewById(R.id.txtSexe);
			txtProfession = (TextView) findViewById(R.id.txtProfession);
			txtEmail = (TextView) findViewById(R.id.txtEmail);
			txtcreated= (TextView) findViewById(R.id.txtcreated);
			
			
		
		
		
		try {
            //get current user info that is in database
            JSONObject j_user = json.getJSONObject("user");
   
		Log.i("User Profile",j_user.toString());
           
 

		txtName.setText(" pseudo: " + userDetailsHash.get(KEY_NAME));
		txtSexe.setText("Date de naissance: " + userDetailsHash.get(KEY_SEXE));
		txtDate.setText("Sexe: " + j_user.getString("Sexe"));
		txtEmail.setText(" Email: " + j_user.getString("email"));
		txtProfession.setText("Profession: " + j_user.getString("Profession"));
		txtcreated.setText("inscrit le: " + userDetailsHash.get(KEY_CREATED_AT));
		
		 } catch (JSONException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
		 }

		btnUpdate = (Button) findViewById(R.id.btnUpdate);
	       btnUpdate.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					
					Intent i = new Intent(getParent(),
					UpdateActivity.class);
					TabGroupActivity parentActivity = (TabGroupActivity)getParent();
	                parentActivity.startChildActivity("update", i); 
	                
	              
					//startActivity(i);
					//finish();
					
				}
			});
	
		
		imageProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
				
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getParent());
		 
        // Setting Dialog Title
        alertDialog.setTitle("Photo Profil");
 
        // Setting Dialog Message
        alertDialog.setMessage("Changer votre photo de profil");
 
        // Setting Icon to Dialog
     //   alertDialog.setIcon(R.drawable.delete);
 
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("From galery", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
 
         	Intent intent = new Intent();
            	// call android default gallery
            	intent.setType("image/*");
            	intent.setAction(Intent.ACTION_GET_CONTENT);
            	// ******** code for crop image
            	intent.putExtra("crop", "true");
            	intent.putExtra("aspectX", 0);
            	intent.putExtra("aspectY", 0);
            	intent.putExtra("outputX", 200);
            	intent.putExtra("outputY", 150);

            	try {

            	intent.putExtra("return-data", true);
            	startActivityForResult(Intent.createChooser(intent,
            	"Complete action using"), PICK_FROM_GALLERY);
            	
              
            	} catch (ActivityNotFoundException e) {
            	// Do nothing for now
            	}
            	
            	
            }
        });
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("From camera", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            
            	
            	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            	intent.putExtra(MediaStore.EXTRA_OUTPUT,
            	MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
            	// ******** code for crop image
            	intent.putExtra("crop", "true");
            	intent.putExtra("aspectX", 0);
            	intent.putExtra("aspectY", 0);
            	intent.putExtra("outputX", 200);
            	intent.putExtra("outputY", 150);

            	try {

            	intent.putExtra("return-data", true);
            	startActivityForResult(intent, PICK_FROM_CAMERA);

            	} catch (ActivityNotFoundException e) {
            	// Do nothing for now
            	}
            	}	
            	
            	
            
        });
            
        // Showing Alert Message
        alertDialog.show();
	
             }
		});
		 }
		
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        	if (requestCode == PICK_FROM_CAMERA) {
        	Bundle extras = data.getExtras();
        	if (extras != null) {
        	Bitmap photo = extras.getParcelable("data");
        	imageProfile.setImageBitmap(photo);

        	}
		
        	}
        	
        
        
			
		
	
        }
        @Override
        public void onBackPressed() {
            super.onBackPressed();

             //Do the Logics Here 
        }}
	


	/*// url to make request
	//private static String url = "http://10.0.2.2:81/sondageJson";
	private static String url = "http://10.0.2.2:81/sondage/infouser.php";

	// contacts JSONArray
	JSONArray userr = null ;
	DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	HashMap<String, String> user = db.getUserDetails();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		
		
	*/
	
	/*TextView txtName;
	TextView txtEmail;
	TextView txtDate;
	TextView txtProfession;
	Button btnUpdate;
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();
	
	// Profile json object
	JSONObject profile;
	
	// Profile JSON url
	private static final String PROFILE_URL = "http://10.0.2.2:81/android_login_api/";
	
	// ALL JSON node names
	private static final String TAG_PROFILE = "profile";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_DATE = "Date";
	private static final String TAG_PROFESSION = "Profession";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		
		txtName = (TextView) findViewById(R.id.txtname);
		txtEmail = (TextView) findViewById(R.id.txtEmail);
		txtDate = (TextView) findViewById(R.id.txtDate);
		txtProfession = (TextView) findViewById(R.id.txtProfession);
		
        // Loading Profile in Background Thread
        new LoadProfile().execute();
	}

	/**
	 * Background Async Task to Load profile by making HTTP Request
	 * */
/*	class LoadProfile extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
	//	@Override
		/* protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Profil.this);
			pDialog.setMessage("Loading profile ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Profile JSON
		 * */
	/*	protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(PROFILE_URL, "GET",
					params);

			// Check your log cat for JSON reponse
			Log.d("Profile JSON: ", json.toString());

			try {
				// profile json object
				profile = json.getJSONObject(TAG_PROFILE);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
	/*	protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					// Storing each json item in variable
	/*			try {
						String id = profile.getString(TAG_ID);
						String name = profile.getString(TAG_NAME);
						String email = profile.getString(TAG_EMAIL);
						String mobile = profile.getString(TAG_DATE);
						String address = profile.getString(TAG_PROFESSION);
						
						// displaying all data in textview
						txtName.setText(name);
						txtEmail.setText("Email: " + email);
						Log.i("emaaaaaaaaaaaaaaaaaaail",email);
						txtDate.setText("Mobile: " + mobile);
						txtProfession.setText("Add: " + address);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			 btnUpdate = (Button) findViewById(R.id.btnUpdate);
		       btnUpdate.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						
						Intent i = new Intent(getApplicationContext(),
						UpdateProfil.class);
						
						startActivity(i);
						finish();
						
					}
				});
		}

	}


	
	/*private static String url = "http://10.0.2.2:81/sondage/userInfo.php?userId=1";
	// Creating JSON Parser instance
	JSONParser jParser = new JSONParser();

	// getting JSON string from URL
	JSONObject json = jParser.getJSONFromUrl(url);
	// Users JSONArray
	JSONArray users = null ;
	TextView txtName ;
	TextView txtDate ;
	TextView txtProfession ;
	TextView txtEmail ;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		
		 txtName = (EditText) findViewById(R.id.txtName);
	     txtDate = (EditText) findViewById(R.id.txtDate);
	     txtProfession = (EditText) findViewById(R.id.txtProfession);
	     txtEmail = (EditText) findViewById(R.id.txtEmail);
	    
		
	try {
		// Getting Array of Contacts
		users = json.getJSONArray("users");
		
		// looping through All Contacts
		for(int i = 0; i < users.length(); i++){
			JSONObject c = users.getJSONObject(i);
			
			// Displaying each json item in variable
			txtName.setText( (c.getString("name")).toString());
			Log.i("uiiiid", (c.getString("name")).toString());
			
	
}
} catch (JSONException e) {
	e.printStackTrace();
}}}
	   
	
	
	
	*/
	
	
	
	
	
	
	
	
/*	
	
	String uid;
	
	TextView txtName ;
	TextView txtDate ;
	TextView txtProfession ;
	TextView txtEmail ;
	
    Dialog pDialog;
		
	private static String KEY_SUCCESS = "success";
	private static final String KEY_user = "user";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	//private static String KEY_Sexe = "Sexe";
	private static String KEY_Date = "Date";
	private static String KEY_Profession = "Profession";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	
	Button btnUpdate;
	// JSON parser class
    JSONParser jsonParser = new JSONParser();
 
    // single product url
    private static final String url_User_detials = "http://10.0.2.2:81/sondage/userInfo.php?userId=1";
 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		
		// getting user details from intent
        Intent i = getIntent();
 
        // getting user id (uid) from intent
        uid = i.getStringExtra(KEY_UID);
 
        // Getting complete user details in background thread
       new      GetUserDetails().onPreExecute();
      
       
       
       btnUpdate = (Button) findViewById(R.id.btnUpdate);
       btnUpdate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				Intent i = new Intent(getApplicationContext(),
				UpdateProfil.class);
				i.putExtra("UserId", i.getStringExtra(KEY_UID));
				startActivity(i);
				finish();
				
			}
		});
          
	}
class GetUserDetails extends AsyncTask<String, String, String> {
			  
	        //**
	        // * Before starting background thread Show Progress Dialog
	         
	   //     @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Profil.this);
	            ((AlertDialog) pDialog).setMessage("Loading user details. Please wait...");
	            ((ProgressDialog) pDialog).setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 
	           // * Getting user details in background thread
	        
	       protected String doInBackground(String... params) {
	 
	            // updating UI from Background Thread
	            runOnUiThread(new Runnable() {
	                public void run() {
	                    // Check for success tag
	                    int success;
	                    try {
	                        // Building Parameters
	                        List<NameValuePair> params = new ArrayList<NameValuePair>();
	                        params.add(new BasicNameValuePair("idUser", uid));
	 
	                        // getting user details by making HTTP request
	                        // Note that user details url will use GET request
	                        JSONObject json = jsonParser.makeHttpRequest(
	                                url_User_detials, "GET", params);
	 
	                        // check your log for json response
	                        Log.d("Single user Details", json.toString());
	 
	                        // json success tag
	                        success = json.getInt(KEY_SUCCESS);
	                        if (success == 1) {
	                            // successfully received user details
	                            JSONArray userObj = json
	                                    .getJSONArray(KEY_user); // JSON Array
	 
	                            // get first user object from JSON Array
	                            JSONObject user = userObj.getJSONObject(0);
	 
	                            // user with this user found
	                            // Edit Text
	                            txtName = (TextView) findViewById(R.id.txtName);
	                            txtDate = (TextView) findViewById(R.id.txtDate);
	                            txtProfession = (TextView) findViewById(R.id.txtProfession);
	                            txtEmail = (TextView) findViewById(R.id.txtEmail);
	                           
	                            
	 
	                            // display user data in EditText
	                            txtName.setText(user.getString(KEY_NAME));
	                            Log.i("naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaame",user.getString(KEY_NAME));
	                            txtDate.setText(user.getString(KEY_Date));
	                            txtProfession.setText(user.getString(KEY_Profession));
	                            txtEmail.setText(user.getString(KEY_EMAIL));
	                            
	 
	                        }
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                    }
	                }
	            });
	 
	            return null;
	        }
	 
	        //** * After completing background task Dismiss the progress dialog
	         
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog once got all details
	            pDialog.dismiss();
	        }
	 }		
	
		
		 
		   // String name = txtName.getText().toString();
			//String Date = txtDate.getText().toString();
			//String Profession = txtProfession.getText().toString();
			//String Sexe =item.toString();
			 
			//String email = txtEmail.getText().toString();
		
*/
