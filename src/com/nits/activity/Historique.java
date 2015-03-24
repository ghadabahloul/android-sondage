package com.nits.activity;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nits.parser.JSONParser;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class Historique extends ListActivity {

		
	// url to make request
	//private static String url = "http://10.0.2.2:81/sondageJson";
	private static String url = "http://10.0.2.2:81/sondage/historiqueJson.php";

	// Sondage JSONArray
	JSONArray sondage = null ;
	public static ArrayList<HashMap<String, String>> historiqueList;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.historique, null);
		this.setContentView(viewToLoad); 
	
		
	
		 if (LoginActivity.connectBool== true)//
     	{
     		 
	
			// Creating JSON Parser instance
			JSONParser jParser = new JSONParser();

			// getting JSON string from URL
			JSONObject json = jParser.getJSONFromUrl(url);


				try {
					// Getting Array of sondage
					sondage = json.getJSONArray("sondage");
					historiqueList= new ArrayList<HashMap<String, String>>();
					// looping through All Sondage
					for(int i = 0; i < sondage.length(); i++){
						JSONObject c = sondage.getJSONObject(i);
						
						
						// Storing each json item in variable
						
						String id = c.getString("id_sondage");
						String titre = c.getString("titre_Sondage");
						String description = c.getString("description_Sondage");
						String auteur = c.getString("auteur_Sondage");
						String nb_questions = c.getString("nb_Questions");
						String points = c.getString("points");
						String id_Q = c.getString("id_Question");

						 // creating new HashMap
					    HashMap<String, String> map = new HashMap<String, String>();
					    
					    // adding each child node to HashMap key => value
					    map.put("id_sondage",id);
					    map.put("titre_Sondage",titre);
					    map.put("description_Sondage",description);
					    map.put("auteur_Sondage",auteur);
					    map.put("nb_Questions",nb_questions);
					    map.put("points",points);
					    map.put("id_Question",id_Q);
					    
					 // adding HashList to ArrayList
					    historiqueList.add(map);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
					   
				
					    /**
						 * Updating parsed JSON data into ListView
						 * */
						ListAdapter adapter = new SimpleAdapter(
								this, historiqueList, R.layout.list_item_historique,
								new String[] {"titre_Sondage", "points" }, new int[] {R.id.titre, R.id.points});
					
//						ListView listSondage=(ListView)findViewById(R.id.list);
//						listSondage.setAdapter(adapter);
						System.out.println("size of sondage list="+historiqueList.size());
						setListAdapter(adapter);

						
					
				}}
	
	
	


	}


