package com.nits.activity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.nits.parser.JSONParser;
import com.nits.parser.UserFunctions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SondageActivity extends Activity implements OnClickListener{

	
	
	static String path= "http://10.0.2.2:81/" ;
	
	//QUESTIONS
	private static String urlQ = path + "sondage/getquestionbyidJson.php?idsondage=" ;
	JSONArray Qsondage = null ;
	public static ArrayList<String> QuestionsList = new ArrayList<String>();

	//SUGGESTIONS
	private static String urlS = path + "sondage/getquggestionsbyidJson.php?idquestion=";
	JSONArray suggestions = null;
	public static ArrayList<String> SuggestionsList = new ArrayList<String>();
	
	//ids list
	public static ArrayList<String> QuestionsIdList = new ArrayList<String>();
	
	
	//Answers
	//public static ArrayList<String> AnswersList = new ArrayList<String>();
	
	//arraylist idQuestion [arraylist Reponse(s) idq], [arraylist Reponse(s) idq] 
	
	public static ArrayList<ArrayList<String>> AnswersList = new ArrayList<ArrayList<String>>();
	private List<NameValuePair> Answers = new ArrayList<NameValuePair>();
	
	Button mybtn;
    TextView txtViewQuestion;
   
	
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sondage_activity_layout);
		
		LinearLayout llSondage = (LinearLayout)findViewById(R.id.ll2);
		
		// getting intent data
		 Bundle b = getIntent().getExtras();
		 String sondageID=b.getString("sondageID");
		 int counteri=b.getInt("counteri");
		 String titreS=b.getString("titreS");
		 counteri++;
		 
		 ((TextView)findViewById(R.id.titreSondage)).setText(titreS);
		//Intent in = getIntent();
		
		 /*
		 String sondageID=in.getExtras().getString("sondageID");
		int counteri=in.getExtras().getInt("counteri");
		counteri++;
		*/
		Toast.makeText(this, "sondageID"+sondageID, 5000).show();
		Toast.makeText(this, "counteri"+counteri, 5000).show();
	

		//______//
		
		
		int id=0;
		boolean btnClicked= true;
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		urlQ= urlQ+sondageID; 
		JSONObject json = jParser.getJSONFromUrl(urlQ);

		
		//String[] idquestions = new String[Qsondage.length()];
		try{
			// Getting Array of Questions
			Qsondage = json.getJSONArray("questions");
			Log.i("QsondageQsondageQsondageQsondage",Qsondage.toString());

			for(int i = 0; i < Qsondage.length(); i++){
				
				urlS= "http://10.0.2.2:81/sondage/getquggestionsbyidJson.php?idquestion=";
				
				JSONObject c = Qsondage.getJSONObject(i);
				// Storing each json item in variable
				String idQ = c.getString("id_Question");
			
				Log.i("idquestion de i",idQ);
				
				
				String Question = c.getString("Question");
				String TypeQ = c.getString("type_question");

				Log.i("QQQQQuestion",Question);
				Log.i("TypeQ i ",TypeQ);
				
				
				 // add Question i
		        TextView tv = new TextView(this);
		        tv.setText(Question);
		        tv.setTextColor(Color.WHITE);
		        tv.setTextSize(16);
		        llSondage.addView(tv);

				//////////////////////////////////////////////////////////////////////////////////////////////   			 

				JSONParser jParserS = new JSONParser();
				urlS= urlS+idQ; 
				Log.i("urlSurlSurlSurlSurlSurlSurlSurl",urlS);
				
				JSONObject jsons = jParserS.getJSONFromUrl(urlS);
				suggestions = jsons.getJSONArray("suggestions");

				Log.i("Suggggggestion",suggestions.toString());

				 
				  
				// looping through All Suggestions 
			

				for(int j = 0; j < suggestions.length(); j++){
					
					id++;
					
					// getting JSON string from new URL of Suggestion
					JSONObject s = suggestions.getJSONObject(j);
					Log.i("SuggggggestionJSONOBJ",s.toString());

					// Storing each json item in variable
					//String idS = s.getString("id_Suggestion");
					String  Suggestion = s.getString("Suggestion");
					
				
					Log.i("Suggggggestion",Suggestion);
					// adding  to ArrayList
					SuggestionsList.add(Suggestion);

					
					
					//add Quggestions By Question Type 
					

					  if (TypeQ.equals("Radio"))
					  {
		
								  //add Suggestions radio buttons  
						  		
								  RadioButton r  = new RadioButton(this);
								  r.setText(Suggestion);
								  r.setId(id);
								  Log.w("iiiiiiiiiiiiiiiiiiiiid:", "View Id: " +id);
								  
								  	RadioGroup rg= new RadioGroup(this); //create the RadioGroup
							  		rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
							  		
								  //the RadioButtons are added to the radioGroup instead of the layout
								  rg.addView(r); 
								  // add the whole RadioGroup to the layout
								  llSondage.addView(rg);
		
								  Log.i("idChekkked", String.valueOf(rg.getCheckedRadioButtonId())); 
		
								  //AnswersList.set(i, String.valueOf(rg.getCheckedRadioButtonId())  );
								  //Log.i("messagAnswerList", AnswersList.toString());
						}
					  
					  else if (TypeQ.equals("Check")) {
						  
						 
					            CheckBox cb = new CheckBox(this);
					            cb.setText(Suggestion);
					            cb.setId(id);
					            
					            llSondage.addView(cb);
						  
					  }
					 }
						  
					  
				
				  if (TypeQ.equals("Text")){
					  
					  // add edit text
				        EditText et = new EditText(this);
				        
				        et.setHint("your Answer"); 
				        et.setMinLines(1);
				        et.setMaxLines(3);
				        llSondage.addView(et);
				 
				  } 
				
				// adding  to ArrayList
				QuestionsList.add(Question);
				QuestionsIdList.add(idQ);
				
				
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace(); }
		
		
		  // add Send Now button 
        Button sendN = new Button(this);
        sendN.setText("Send Now");
        sendN.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bluee));
       sendN.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        sendN.setId(9000);
       sendN.setOnClickListener(this);
        llSondage.addView(sendN);
        
        
        // add Cancel button 
        Button sendC = new Button(this);
        sendC.setText("Cancel");
        sendC.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bluee));
        sendC.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        sendC.setId(9002);
        sendC.setOnClickListener(this);
        llSondage.addView(sendC);
        
        
        
      
	}

	
	 private void storeAnswer(int question, String answer) {
	        Log.w("ANDROID DYNAMIC VIEWS:", "Question: " + String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer) );
	        
	        Toast toast = Toast.makeText(this, String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer), Toast.LENGTH_LONG);
	        toast.setGravity(Gravity.TOP, 25, 400);
	        toast.show();
	        UserFunctions userFunction = new UserFunctions();
	      

            	JSONObject json = userFunction.registerAnswers(answer, "2","1");

	    }
	 
	 
	 
	private void loopQuestions(ViewGroup parent) {
		int nbQ= QuestionsIdList.size();
		int c=-1; 
		
        for(int i = 0; i < parent.getChildCount(); i++) {
            c++;
        	View child = parent.getChildAt(i);
            if(child instanceof RadioGroup ) {
                //Support for RadioGroups
                RadioGroup radio = (RadioGroup)child;
                
                //storeAnswer(radio.getId(), radio.getCheckedRadioButtonId());
                storeAnswer(radio.getId(), "jamais");
                //ajouter a la base avec id, value de la reponse ..
                
               
            }
            else if(child instanceof CheckBox) {
                //Support for Checkboxes
                CheckBox cb = (CheckBox)child;
                int answer = cb.isChecked() ? 1 : 0;
                storeAnswer(cb.getId(), (cb.getText()).toString());
                if (cb.isChecked()){ Log.w("THE CHECKBOX VALUE:", "cb: " + cb.getText());   };
                
            }
            else if(child instanceof EditText) {
                //Support for EditText
                EditText et = (EditText)child;
                storeAnswer(et.getId(), (et.getText()).toString()) ;
                Log.w("ANDROID DYNAMIC VIEWS:", "EdiText: " + et.getText());
            }
           
            else {
                //Support for other controls
            }
 
            if(child instanceof ViewGroup) {
                //Nested Q&A
                ViewGroup group = (ViewGroup)child;
                loopQuestions(group);
            }
        }
    }
	

	
	public void onClick(View v) {
	        Toast toast;
	     
	        Log.w("ANDROID DYNAMIC VIEWS:", "View Id: " + v.getId());
	       
	        switch (v.getId()) {
	        //case of send button 
	        case 9000:
	           // toast = Toast.makeText(this, "send now ", Toast.LENGTH_LONG);
	           // toast.setGravity(Gravity.TOP, 25, 400);
	           // toast.show();
	            LinearLayout root = (LinearLayout) findViewById(R.id.mainLayout); //or whatever your root control is
	            
	            loopQuestions(root); 
	            
	            if (LoginActivity.connectBool== true)// user est connecté ,les reps seront ajouté avec son id
            	{
            		 
            	
            	
            	
				Intent i = new Intent(getParent(),
						ReponseSucces.class);
				TabGroupActivity parentActivity = (TabGroupActivity)getParent();
                parentActivity.startChildActivity("rep", i); 
                
                
               
                
            	}
            	
            	// else l'utilisateur est anonyme et les reps seront ajouté avec un id null
            	else{
            		
            		
            	
					
					Intent ii = new Intent(getParent(),
							ReponseSucces.class);
					TabGroupActivity parentActivity = (TabGroupActivity)getParent();
	                parentActivity.startChildActivity("rep", ii); 
            		
     
            	}
	          
	         //   	Log.i("stooooring answeeeeeeeeerssssss",QuestionsIdList.get(c) );
					
	            
	        
	          //  loopQuestions(root);
	            
	            
	            //saveAnswers();
	           
	            
	            
	          //case of Cancel
	        case 9002:
	        	 toast = Toast.makeText(this, "cancel", Toast.LENGTH_LONG);
	        	 
	        	break;
	        }
	}
	        
	
	/* public void saveAnswers() {
		        LinearLayout root = (LinearLayout) findViewById(R.id.ll2); //or whatever your root control is
		        loopQuestions(root);
		    }
		 
		/*    private void loopQuestions(ViewGroup parent) {
		        for(int i = 0; i < parent.getChildCount(); i++) {
		            View child = parent.getChildAt(i);
		            if(child instanceof RadioGroup ) {
		                //Support for RadioGroups
		                RadioGroup radio = (RadioGroup)child;
		                storeAnswer(radio.getId(), radio.getCheckedRadioButtonId(), i);
		            }
		            
		            
		            
		          /*  else if(child instanceof CheckBox) {
		                //Support for Checkboxes
		                CheckBox cb = (CheckBox)child;
		                int answer = cb.isChecked() ? 1 : 0;
		                storeAnswer(cb.getId(), answer);
		            }
		            else if(child instanceof EditText) {
		                //Support for EditText
		                EditText et = (EditText)child;
		                Log.w("ANDROID DYNAMIC VIEWS:", "EdiText: " + et.getText());
		            }
		            else if(child instanceof ToggleButton) {
		                //Support for ToggleButton
		                ToggleButton tb = (ToggleButton)child;
		                Log.w("ANDROID DYNAMIC VIEWS:", "Toggle: " + tb.getText());
		            }
		            else {
		                //Support for other controls
		            }*/
		 
		    /*        if(child instanceof ViewGroup) {
		                //Nested Q&A
		                ViewGroup group = (ViewGroup)child;
		                loopQuestions(group);
		            }
		        }
		    }
		    
		    
		    
		 
		   /* private void storeAnswer(int question, int answer, int index) {
		    	AnswersList.set(index, String.valueOf(answer));
		    	
		        Log.w("ANDROID DYNAMIC VIEWS:", "Question: " + String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer) );
		        
		        Toast toast = Toast.makeText(this, String.valueOf(question) + " * "+ "Answer: " + String.valueOf(answer), Toast.LENGTH_LONG);
		        toast.setGravity(Gravity.TOP, 25, 400);
		        toast.show();
		 
		        
		    }*/
		
		    
		/*    public void addListenerOnButtonNext(final int counteri, final String sondageID) {   		 
	    		final Context context = this;
	    		
	    		((Button)findViewById(R.id.sendB)).setOnClickListener(new OnClickListener(){
	    			@Override
	    			public void onClick(View arg0) {
	    				
	    				
	    				Intent intent = new Intent(context,  SondageActivity.class);
	    			    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    			    
	    			    
	    			    
	    			    intent.putExtra("sondageID",sondageID);
	    			    intent.putExtra("counteri",counteri);
	    			    
	    			   
	                                startActivity(intent);   
	     
	                                
	    			}
	    		});
	     
	    	}*/
	        
	 
		}
