 package com.nits.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

import com.nits.parser.DatabaseHandler;
import com.nits.parser.UserFunctions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;




public class UpdateActivity extends Activity  {

	EditText txtName;
	EditText txtDate;
	Spinner SpinnerSexe;
	EditText txtProfession;
	EditText txtEmail;
	EditText txtPassword;
	EditText txtnewPassword;
	TextView Update_error;
	// String item;
	Button btnSave;


	String uid;
	private static String KEY_SUCCESS = "success";
	 private static final String KEY_UID = "uid";
	    private static final String KEY_NAME = "name";
	    private static final String KEY_DATE = "date";
	    private static final String KEY_SEXE = "sexe";
	    private static final String KEY_PROFESSION = "profession";
	    private static final String KEY_EMAIL= "email";
	    private static final String KEY_CREATED_AT = "created_at";
	    private static final String KEY_UPDETED_AT = "updated_at";
	 
	    
	// Progress Dialog
	// private ProgressDialog pDialog;

	// JSON parser class
	//JSONParser jsonParser = new JSONParser();

	// single product url
	//   private static final String url_User_detials = "http://10.0.2.2:81/android_login_api/include/user_info.php";

	// url to update product
	//   private static final String url_update_User = "http://10.0.2.2:81/android_login_api/include/update.php";

	// JSON Response node names
	//private static String KEY_SUCCESS = "success";
	//private static String KEY_ERROR = "error";
	//private static final String KEY_user = "user";

	//private static String KEY_UID = "uid";
	//private static String KEY_NAME = "name";
	//private static String KEY_Date = "Date";
	//private static String KEY_Sexe = "sexe";
	//private static String KEY_Profession = "Profession";
	//private static String KEY_EMAIL = "email";
	//private static String KEY_Password = "Password";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.updateprofil, null);
		this.setContentView(viewToLoad); 
		
		
	

		txtName = (EditText) findViewById(R.id.txtName);
		
		txtDate = (EditText) findViewById(R.id.txtDate);
		txtProfession = (EditText) findViewById(R.id.txtProfession);

		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		Update_error=(TextView) findViewById(R.id.Update_error);
		btnSave = (Button) findViewById(R.id.btnSave);
		txtnewPassword = (EditText) findViewById(R.id.txtnewPassword);

		SpinnerSexe = (Spinner) findViewById(R.id.SpinnerSexe);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.Sexe_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		SpinnerSexe.setAdapter(adapter);
		SpinnerSexe.setSelection(0);

		
		// get current user info from database
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		HashMap<String, String> user = db.getUserDetails();
		
		final String uid = user.get("uid");
		Log.i("uuuuuuuuuuuuuuuuuuuuiiidddddd",uid);
		UserFunctions uf = new UserFunctions();
		JSONObject json = uf.userExists(uid);
		// String tempPassString = null;
		 
		
		 try {
             //get current user info that is in database
             JSONObject j_user = json.getJSONObject("user");
             txtName.setText(j_user.getString("name"));
             Log.i("naaaaaaaaaaaaame",j_user.getString("name"));
             txtDate.setText(j_user.getString("Date"));
             txtProfession.setText(j_user.getString("Profession"));
             txtEmail.setText(j_user.getString("email"));
             if(j_user.getString("Sexe").compareTo("femme") == 0)
             {
                     SpinnerSexe.setSelection(1);
             }
             else
             {
                     SpinnerSexe.setSelection(0);
             }
             
           //  tempPassString = j_user.getString("password");
		 } catch (JSONException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
		
	}
		// final String CurrentPassword = tempPassString; 
		 btnSave.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					
					String name = txtName.getText().toString();	
					Log.i("nnnnnnnnnnnnnnnnn",txtName.getText().toString());
					String Date = txtDate.getText().toString();	
					String Profession = txtProfession.getText().toString();
					String Sexe = SpinnerSexe.getSelectedItem().toString();
					String email = txtEmail.getText().toString();
					String Password = txtPassword.getText().toString();
					String NewPassword = txtnewPassword.getText().toString();
					
					
					
					

					 // No Name
                  if (name.compareTo("") == 0){
                          Update_error.setText("entrer votre pseudo.");
                  }
					
                //No Email
                    else if (email.compareTo("") == 0){
                            Update_error.setText("Entrer votre Email.");
                   }
                    else if (!UserFunctions.validEmail(email)){
    					Update_error.setText("Votre Email n'est pas valide.");
    				}
                    //No Date
                   else if (Date.compareTo("") == 0){
                            Update_error.setText("Please enter your full Date.");
                    }
                            
                    //No profession
                    else if (Profession.compareTo("") == 0){
                            Update_error.setText("Please enter your profession .");
                   }
                    else if ((Password.compareTo("") == 0 && (NewPassword.compareTo("") != 0 ))// <<--- old password is empty
    												// while others are not
    						|| (NewPassword.compareTo("") == 0 && (Password.compareTo("") != 0 )))// <<--- pass is empty
    														// while others are not
    						
    				{
                    	Update_error.setText(" Entrer votre ancien/nouveau password .");
    				}
                  
                  
                  
                   // else if (NewPassword.compareTo("") == 0) {
    				//	Update_error.setText("Entrer votre nouveau mot de passe.");
    				//} 
                    
                    
					// UserFunctions userFunction = new UserFunctions();
                     
                     //   if(OldPassword.compareTo("") == 0 && NewPassword.compareTo("") == 0 )//<<-- case where user does not want to change password
                       // {
                              //  NewPassword = CurrentPassword;
                     //   }
					
                      //this sends the update info
                      //  JSONObject json = userFunction.UpdateUser(Name, Date, Sexe, profession, email, password);
                         //Log.d("hhhhhhhhhhhhhhh",userFunction.UpdateUser(Name, Date,Sexe, profession, email, NewPassword));
                       
					
                   else
                    	
                    	
                    {
                    	
                	   UserFunctions userFunction = new UserFunctions();
                	  JSONObject json = userFunction.UpdateUser(name, Date, Sexe, Profession, email, NewPassword);
                	   
                	  try {
      					if (json.getString("success") != null) {
      						
      						Update_error.setText("");
      						String res = json.getString("success");

      						if (Integer.parseInt(res) == 0) {
      							Update_error.setText("Error, Unable to update");
      						}
      						else {
      							
      							//DatabaseHandler db = new DatabaseHandler(getApplicationContext());
    						//	JSONObject json_user = json.getJSONObject("user");
    							
    							// Clear all previous data in database
    							
    						//	db.UpdateUser(json_user.getString(KEY_NAME),json_user.getString(KEY_DATE),json_user.getString(KEY_SEXE),json_user.getString(KEY_PROFESSION), json_user.getString(KEY_EMAIL), json.getString(KEY_UID));						
    							
      							
      							Intent iii = new Intent(getParent(),
      									ProfilActivity.class);
      									TabGroupActivity parentActivity = (TabGroupActivity)getParent();
      					                parentActivity.startChildActivity("profil", iii); 
      					              

      						}
      					}
      				} catch (JSONException e) {
      					e.printStackTrace();
      				}
      			}

				}


      		});
		 
		 }
					 
                            
                    
					

                  //  else if((OldPassword.compareTo("") == 0 && (NewPassword.compareTo("") != 0 ))//<<--- old password is empty while others are not
                          //          || (NewPassword.compareTo("") == 0 && (OldPassword.compareTo("") != 0 ))//<<--- pass is empty while others are not
                          //          || (  (OldPassword.compareTo("") != 0 || NewPassword.compareTo("") != 0)))//<<--- rpass is empty while others are not
                  //  {
                   //         Update_error.setText("Please enter your old and new password.");
					
                   // }
                    
                 //   else if(OldPassword.compareTo("") != 0 && OldPassword.compareTo(CurrentPassword) == 0)//<<---old password is incorrect
                  //  {
                           // Update_error.setText("Incorrect password.");
                   // }
                    
                 //   else{
                       
                        
                        
                        

                    
				

	
	
	}
		
/*		
		
		
		
		btnSave.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				String name = txtName.getText().toString();
				String Date = txtDate.getText().toString();
				String Profession = txtProfession.getText().toString();
				String Sexe = SpinnerSexe.getSelectedItem().toString();

				String email = txtEmail.getText().toString();
				String password = txtPassword.getText().toString();



				UserFunctions userFunction = new UserFunctions();

				JSONObject json = userFunction.UpdateUser(name, Date,Sexe, Profession, email,password);
				try {
					if (json.getString("success") != null) {

						Update_error.setText("");
						String res = json.getString("success");

						if (Integer.parseInt(res) == 0) {
							Update_error.setText("Error, Unable to update");
						}
						else {


						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}




		});
		
		

	}}
// save button
/*    btnSave = (Button) findViewById(R.id.btnSave);


        // getting user details from intent
        Intent i = getIntent();

        // getting user id (uid) from intent
        uid = i.getStringExtra(KEY_UID);

        // Getting complete user details in background thread
        new GetUserDetails().execute();

        // save button click event
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // starting background task to update product
                new SaveUserDetails().execute();
            }
        });



    }

    /**
 * Background Async Task to Get complete product details
 * */
// class GetUserDetails extends AsyncTask<String, String, String> {

/**
 * Before starting background thread Show Progress Dialog
 * */
//       @Override
/*       protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(UpdateProfil.this);
            pDialog.setMessage("Loading user details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
 * Getting user details in background thread
 * */
/*   protected String doInBackground(String... params) {

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("uid", uid));

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
                            txtName = (EditText) findViewById(R.id.txtName);
                            txtDate = (EditText) findViewById(R.id.txtDate);
                            txtProfession = (EditText) findViewById(R.id.txtProfession);
                            txtEmail = (EditText) findViewById(R.id.txtEmail);
                            txtPassword = (EditText) findViewById(R.id.txtPassword);


                            // display user data in EditText
                            txtName.setText(user.getString(KEY_NAME));
                            txtDate.setText(user.getString(KEY_Date));
                            txtProfession.setText(user.getString(KEY_Profession));
                            txtEmail.setText(user.getString(KEY_EMAIL));
                            txtPassword.setText(user.getString(KEY_Password));

                        }else{
                            // product with uid not found
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }

        /**
 * After completing background task Dismiss the progress dialog
 * **/
/*     protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

    /**
 * Background Async Task to  Save product Details
 * */
/* class SaveUserDetails extends AsyncTask<String, String, String> {

        /**
 * Before starting background thread Show Progress Dialog
 * */
// @Override
/*    protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(UpdateProfil.this);
            pDialog.setMessage("Saving user ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
 * Saving product
 * */
/*   protected String doInBackground(String... args) {

            // getting updated data from EditTexts
            String name = txtName.getText().toString();
            String Date = txtDate.getText().toString();
            String profession = txtProfession.getText().toString();
            String Email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(KEY_UID, uid));
            params.add(new BasicNameValuePair(KEY_NAME, name));
            params.add(new BasicNameValuePair(KEY_Date, Date));
            params.add(new BasicNameValuePair(KEY_Profession, profession));

            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_User,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(KEY_SUCCESS);

                if (success == 1) {
                    // successfully updated
                    Intent i = getIntent();
                    // send result code 100 to notify about product update
                    setResult(100, i);
                    finish();
                } else {
                    // failed to update product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
 * After completing background task Dismiss the progress dialog
 * **/
/*   protected void onPostExecute(String file_url) {
            // dismiss the dialog once product updated
            pDialog.dismiss();
        }
    }




}




























   /* EditText txtName;
    EditText txtDate;
    //Spinner SpinnerSexe;
    EditText txtProfession;
    EditText txtEmail;
    EditText txtPassword;
    TextView UpdateErrorMsg;
   // String item;
    Button btnSave;



 // JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_Date = "Date";
	//private static String KEY_Sexe = "sexe";
	private static String KEY_Profession = "Profession";
	private static String KEY_EMAIL = "email";
	private static String KEY_Password = "Password";
	private static String KEY_UPDATE_AT = "update_at";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateprofil);

		txtName = (EditText) findViewById(R.id.txtName);
		txtDate = (EditText) findViewById(R.id.txtDate);
		txtProfession = (EditText) findViewById(R.id.txtProfession);


		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);

		btnSave = (Button) findViewById(R.id.btnLinkToLoginScreen);
		UpdateErrorMsg = (TextView) findViewById(R.id.Update_error);}}

//Spinner spinner = (Spinner) findViewById(R.id.SpinnerSexe);

		// Spinner click listener
	      // spinner.setOnItemSelectedListener(this);
			// Spinner Drop down elements
	       // List categories = new ArrayList();
	       // categories.add("Homme");
	      //  categories.add("Femme");
	     // Creating adapter for spinner
	        //ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

	        // Drop down layout style - list view with radio button
	        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	        // attaching data adapter to spinner
	        //spinner.setAdapter(dataAdapter);



	     // Save Button Click event
	     /*   btnSave.setOnClickListener(new View.OnClickListener() {			
				public void onClick(View view) {

					String name = txtName.getText().toString();
					String Date = txtDate.getText().toString();
					String Profession = txtProfession.getText().toString();
					//String Sexe =item.toString();

					String email = txtEmail.getText().toString();
					String password = txtPassword.getText().toString();
					UserFunctions userFunction = new UserFunctions();
					JSONObject json = userFunction.UpdateUser(name,Date,/*Sexe*//*Profession, email, password);

					// check for login response
					/*try {
						if (json.getString(KEY_SUCCESS) != null) {
							UpdateErrorMsg.setText("");
							String res = json.getString(KEY_SUCCESS); 
							if(Integer.parseInt(res) == 1){
								// user successfully Updated
								// Store user details in SQLite Database
								DatabaseHandler db = new DatabaseHandler(getApplicationContext());
								JSONObject json_user = json.getJSONObject("user");

								// Clear all previous data in database
								userFunction.logoutUser(getApplicationContext());
								db.UpdateUser(json_user.getString(KEY_NAME),json_user.getString(KEY_Date),/*json_user.getString(KEY_Sexe)*//*json_user.getString(KEY_Profession), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_UPDATE_AT));	*/					
// Launch main Screen

/*					Intent profil = new Intent(getApplicationContext(), Profil.class);
								// Close all views before launching main
								profil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(profil);
								// Close Registration Screen
								finish();
							}else{
								// Error in registration
								UpdateErrorMsg.setText("Error occured in Update Profile");
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

		}}/*
	        @Override
	    	public void onItemSelected(AdapterView parent, View view, int position, long id) {
	    		// TODO Auto-generated method stub
	    		//On selecting a spinner item

	            String item = parent.getItemAtPosition(position).toString();

	            // Showing selected spinner item
	            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

	    	}

	    	@Override
	    	public void onNothingSelected(AdapterView<?> arg0) {
	    		// TODO Auto-generated method stub

	    	}} */