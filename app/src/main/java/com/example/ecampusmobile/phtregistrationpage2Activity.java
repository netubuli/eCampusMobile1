package com.example.ecampusmobile;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class phtregistrationpage2Activity extends Activity {

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	// url to add new student..
	private static String url_add_student = "http://elearning2.maseno.ac.ke/masenoEcampusmobileConnect/addStudent.php";
	//private static String url_add_student="http://elearning2.maseno.ac.ke/masenoEcampusmobileConnect/addHomabaystudent.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	//List<String> list;
	private TextView reasons2TextView,courseCodeTextView;
	private ArrayAdapter admYrAdapter,yrAdapter,campusAdapter,courseCodeAdapter,programmeAdapter,facultyAdapter,attemptsAdapter,reasons1Adapter,reasons2Adapter;
	private Spinner admYrSpinner,yrSpinner,campusSpinner,facultySpinner,courseCodeSpinner,attemptsSpinner,reasons1Spinner,reasons2Spinner;

	private String []admYrItems={"select Adm-year","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
	private String [] facultyItems={"select your school","Agriculture & Food Security","Arts and Social Sciences",
			"Biological and Physical Science","Business and Economics","Communication and Media Technology","Computing",
			"Development & Strategic Studies",
			"Education","Ecotourism,Hotel & Institution Management",
			"Environment & Earth Sciences",
			"Mathematics, Applied Statistics and Actuarial Sciences","Medicine",
			"Public Health and Community Development",
			"Planning and Architecture",
			"Science"
			};

	private String [] yrItems={"select year","First","Second","Third","Fourth","Fifth","Ecampus Student"};
	private String [] campusItems={"select campus","Maseno","Kisumu","Homabay","Ecampus","Siaya"};

	//the text to appear should be(choose from the available options)
	private String [] attemptsItems={"select attempt","First Attempt","Retake/Resit"};
	//have you done ori before:[yes]give the below options[no]the below options should not work
	private String [] reasons1Items={"select your choice","Yes","No"};
	private String [] reasons2Items={"Select your reason",
									"Passed orientation to elearning but did not enroll for PHT 112",
									"Did not complete orientation to elearning",
									"Did not attain orientation to elearning pass mark"};
	String [] courseCodeItems={"PHT 112","AEN 105","PHT 112 & AEN 105"};

	private Button btnSubmit;
	private Button btnBack;
	private String admissionYear,studyYear,campus,faculty,reasons="",attempts,course="";
	//the below value are passed from the previous activity
	private String fullName,gender,dob,email,msn_email,mobilePhoneNumber,adm_no,studyProgramme;
	 private TextView informationTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phtregistrationpage2);


        informationTextView=(TextView)findViewById(R.id.informationTextView);
        informationTextView.setVisibility(View.GONE);

        reasons2TextView=(TextView)findViewById(R.id.reasons2TextView);
        reasons2TextView.setVisibility(View.GONE);

		courseCodeTextView=(TextView)findViewById(R.id.courseCodeTextView);
		courseCodeTextView.setVisibility(View.GONE);

        Drawable a = findViewById(R.id.backButton).getBackground();
        Drawable b=findViewById(R.id.submitButton).getBackground();
        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        a.setColorFilter(filter);
        b.setColorFilter(filter);
        btnBack=(Button)findViewById(R.id.backButton);
        btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
								finish();

			}
		});


        PopulateSpinners();
        BtnSubmitListener();

    }
    public void PopulateSpinners(){
        admYrSpinner=(Spinner)findViewById(R.id.admYearSpinner);
        admYrAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,admYrItems);
        admYrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        admYrSpinner.setAdapter(admYrAdapter);

        yrSpinner=(Spinner)findViewById(R.id.studyYearSpinner);
        yrAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,yrItems);
        yrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yrSpinner.setAdapter(yrAdapter);

        campusSpinner=(Spinner)findViewById(R.id.campusSpinner);
        campusAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,campusItems);
        campusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campusSpinner.setAdapter(campusAdapter);

        facultySpinner=(Spinner)findViewById(R.id.facultySpinner);
        facultyAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,facultyItems);
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(facultyAdapter);
		facultySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				// your code here
				if(facultySpinner.getSelectedItem().toString().equalsIgnoreCase("Science")){
					//Toast.makeText(getApplicationContext(), facultySpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
					courseCodeSpinner.setVisibility(View.VISIBLE);
					courseCodeTextView.setVisibility(View.VISIBLE);
				}
				else{
					//Toast.makeText(getApplicationContext(), "aen 112", Toast.LENGTH_LONG).show();
					courseCodeSpinner.setVisibility(View.GONE);
					courseCodeTextView.setVisibility(View.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}

		});

		courseCodeSpinner=(Spinner)findViewById(R.id.courseCodeSpinner);
		courseCodeAdapter =new ArrayAdapter(this,android.R.layout.simple_spinner_item,courseCodeItems);
		courseCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		courseCodeSpinner.setAdapter(courseCodeAdapter);
		courseCodeSpinner.setVisibility(View.GONE);

        reasons1Spinner=(Spinner)findViewById(R.id.reasons1Spinner);
        reasons1Adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,reasons1Items);
        reasons1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reasons1Spinner.setAdapter(reasons1Adapter);
        reasons1Spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
























































































































































































































                // your code here
            	if(reasons1Spinner.getSelectedItem().toString().equalsIgnoreCase("yes")){
            		 reasons2Spinner.setVisibility(View.VISIBLE);
            	      reasons2TextView.setVisibility(View.VISIBLE);
            	}
            	else if(reasons1Spinner.getSelectedItem().toString().equalsIgnoreCase("no")){
           		 reasons2Spinner.setVisibility(View.GONE);
       	          reasons2TextView.setVisibility(View.GONE);
       	         // reasons="never done orientation before";
            	}
            	else if(reasons1Spinner.getSelectedItem().toString().equalsIgnoreCase("select your choice")){
            		 reasons2Spinner.setVisibility(View.GONE);
          	          reasons2TextView.setVisibility(View.GONE);
            	}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        reasons2Spinner=(Spinner)findViewById(R.id.reasons2Spinner);
        reasons2Adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,reasons2Items);
       reasons2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // reasons2Adapter.setDropDownViewResource(android.R.layout.multiline_spinner_dropdown_item);
        reasons2Spinner.setAdapter(reasons2Adapter);
       reasons2Spinner.setVisibility(View.GONE);
       // reasons2TextView.setVisibility(View.GONE);

        attemptsSpinner=(Spinner)findViewById(R.id.attemptsSpinner);
        attemptsAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,attemptsItems);
        attemptsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attemptsSpinner.setAdapter(attemptsAdapter);
        //attemptsSpinner.setVisibility(View.GONE);

    }
    public void  BtnSubmitListener(){
    	btnSubmit=(Button)findViewById(R.id.submitButton);
    	btnSubmit.setOnClickListener(new OnClickListener() {

    		 public void onClick(View v) {
    			//get value from previous Activity i.e phtregistrationActivity
    		 fullName=getIntent().getStringExtra("fullName");
    		 gender=getIntent().getStringExtra("gender");
    		 dob=getIntent().getStringExtra("dob");
    		 email=getIntent().getStringExtra("email");
    		 mobilePhoneNumber=getIntent().getStringExtra("mobilePhoneNumber");
    		 adm_no=getIntent().getStringExtra("adm_no");
    		 studyProgramme=getIntent().getStringExtra("studyProgramme");
    		 msn_email=getIntent().getStringExtra("msn_email");
    		      //values gotten within this activity
    		 admissionYear=admYrSpinner.getSelectedItem().toString();
    		 faculty=facultySpinner.getSelectedItem().toString();
				 if (facultySpinner.getVisibility()==View.GONE){
					 course="PHT 112";
				 }else if(facultySpinner.getVisibility()==View.VISIBLE){
					  String tempcourse=courseCodeSpinner.getSelectedItem().toString();
					 if(tempcourse.equalsIgnoreCase("PHT 112 & AEN 105")){
						course="PHT 112|AEN 105";
					 }
					 else {
						 course=tempcourse;
					 }
					 Toast.makeText(getApplicationContext(),course,Toast.LENGTH_SHORT).show();
				 }

    		 studyYear=yrSpinner.getSelectedItem().toString();
    		 campus=campusSpinner.getSelectedItem().toString();

    		if(reasons2Spinner.getVisibility()==View.GONE){
    			 //reasons=reasons1Spinner.getSelectedItem().toString();
    			reasons="no previous orientation";
    		}
    		else if(reasons2Spinner.getVisibility()==View.VISIBLE){
    			reasons=reasons2Spinner.getSelectedItem().toString();
    		}

    		 attempts=attemptsSpinner.getSelectedItem().toString();


			 ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    		/* if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {
    		       //Not connected.        
    		       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
    		   } 
    		 else*/ if(studyYear.equalsIgnoreCase("select year")){
		    		Toast.makeText(getApplicationContext(), "select year of study", Toast.LENGTH_SHORT).show();

    		 }
    		 else if(faculty.equalsIgnoreCase("select your school")){
		    		Toast.makeText(getApplicationContext(), "You must select your", Toast.LENGTH_SHORT).show();

 		 }
    		 else if(campus.equalsIgnoreCase("select campus")){
		    		Toast.makeText(getApplicationContext(), "select campus", Toast.LENGTH_SHORT).show();

    		 } else if(reasons.equalsIgnoreCase("select your choice")){
		    		Toast.makeText(getApplicationContext(), "You must select your choice/reason", Toast.LENGTH_SHORT).show();}
    		else if(admissionYear.equalsIgnoreCase("select Adm-year")){
		    		Toast.makeText(getApplicationContext(), "You must select admission year", Toast.LENGTH_SHORT).show();

    		 }
    		else if(attempts.equalsIgnoreCase("select attempt")){
	    		Toast.makeText(getApplicationContext(), "You must say which attempt this is", Toast.LENGTH_SHORT).show();

		 }
    		 else if(connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)){
    		  new AddNewStudent().execute();
    			// Toast.makeText(getApplicationContext(), reasons, Toast.LENGTH_LONG).show();
    		 } else  if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {
  		       //Not connected.
  		       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
  		   }
            }
        });
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_phtregistrationpage2, menu);
        return true;
    }
    class AddNewStudent extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(phtregistrationpage2Activity.this);
			pDialog.setMessage("Saving your details..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**adding buyer
		 * */
		protected String doInBackground(String... args) {

		// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			//params.add(new BasicNameValuePair("value passed to php file",variable carrying value in this file));
			params.add(new BasicNameValuePair("fullName",fullName));
			params.add(new BasicNameValuePair("gender", gender));
			params.add(new BasicNameValuePair("dob", dob));
			params.add(new BasicNameValuePair("email",email));
			params.add(new BasicNameValuePair("msn_email",msn_email));
			params.add(new BasicNameValuePair("mobile",mobilePhoneNumber));
			params.add(new BasicNameValuePair("adm_no",adm_no));
			params.add(new BasicNameValuePair("studyProgramme",studyProgramme));
			params.add(new BasicNameValuePair("adm_yr",admissionYear));
			params.add(new BasicNameValuePair("yrOfStudy",studyYear));
			params.add(new BasicNameValuePair("campus",campus));
			params.add(new BasicNameValuePair("faculty",faculty));
			params.add(new BasicNameValuePair("reasons",reasons));
			params.add(new BasicNameValuePair("attempts",attempts));
			params.add(new BasicNameValuePair("course",course));


			// getting JSON Object
			// Note that add student url accepts POST method

			JSONObject json = jsonParser.makeHttpRequest(url_add_student, "POST", params);

			// check log cat for response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully added Student
					Intent i = new Intent(phtregistrationpage2Activity.this, lastActivity.class);
					startActivity(i);
					// closing this screen
					finish();
				} else if(success != 1){
					Intent i = new Intent(phtregistrationpage2Activity.this, DuplicateActivity.class);
					startActivity(i);
					//informationTextView.setVisibility(View.VISIBLE);
					finish();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
	protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}




}
