package com.example.ecampusmobile;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ParserError")
public class phtregistrationActivity extends Activity {
private Button btnNext;
private RadioGroup radioGroupGender;
private RadioButton radioBtnGender;
private EditText edtFirstName,edtLastName,edtOtherName,edtDob,edtEmail,edtMsn_emailEditText,edtMobilePhoneNumber,edtAdm_no,edtprogramme;
private Spinner salutationSpinner;
private ArrayAdapter salutationAdapter;

private String [] salutationItems={"Mr.","Ms."};
private String gender="",msn_email="N/A";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phtregistration);
        edtFirstName=(EditText)findViewById(R.id.firstNameEditText);
        edtLastName=(EditText)findViewById(R.id.lastNameEditText);
        edtOtherName=(EditText)findViewById(R.id.otherNameEditText);
        
        radioGroupGender=(RadioGroup)findViewById(R.id.genderRadioGroup);
        
        edtDob=(EditText)findViewById(R.id.dobEditText);
        edtDob.setHint("dd/mm/yyyy");
        edtEmail=(EditText)findViewById(R.id.emailEditText);
        edtEmail.setHint("..@gmail.com");
        edtMsn_emailEditText=(EditText)findViewById(R.id.msn_emailEditText);
        edtMsn_emailEditText.setHint("..@maseno.ac.ke");
        edtprogramme=(EditText)findViewById(R.id.programmeEditText);
        final int selectedGenderId =radioGroupGender.getCheckedRadioButtonId();

      //final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //THE BELOW WILL ALLOW FOR ONLY DD/MM/YYYY
        final String datePattern="(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

        final String admNumberPattern="([A-Z]{2,3})/([0-9]{4,5})/((19|20)\\d\\d)";
        final String admNumberotherPattern="([A-Z]{2})/([A-Z]{2,3})/([0-9]{4,5})/((19|20)\\d\\d)";

               
        edtMobilePhoneNumber=(EditText)findViewById(R.id.mobilephoneEditText);
        edtAdm_no=(EditText)findViewById(R.id.admissionNumberEditText);
        edtAdm_no.setHint("AA/1234/2000");
        PopulateSpinners();
        btnNext=(Button)findViewById(R.id.nxtButton);
        
         btnNext.setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
				//check that all fields are validated before moving to next window
				
		    	if( edtFirstName.getText().toString().length() == 0 )
	    		{
		    		edtFirstName.setError( "Your first name is required!" );
		    		Toast.makeText(getApplicationContext(), "Your first name is required!", Toast.LENGTH_LONG).show();
	    		}
		    	else if( edtAdm_no.getText().toString().length() == 0 ){
		    		edtAdm_no.setError( "Your admission number is required!" );
		    		Toast.makeText(getApplicationContext(), "Your admission number is required!", Toast.LENGTH_LONG).show();
		    	}
		    	else if(!
		    			((edtAdm_no.getText().toString()).matches(admNumberPattern)
		    			||(edtAdm_no.getText().toString()).matches(admNumberotherPattern))){
		    		edtAdm_no.setError( "Enter the right  admission number format e.g AA/1111/2000");
		    		Toast.makeText(getApplicationContext(), "Enter the right  admission number format e.g AA/1111/2000", Toast.LENGTH_LONG).show();
		    	}

		    	else if( edtLastName.getText().toString().length() == 0 )
	    		{
		    		edtLastName.setError( "Your last name is required!" );
		    		Toast.makeText(getApplicationContext(), "Your last name is required!", Toast.LENGTH_LONG).show();
	    		}
		    	else if( edtprogramme.getText().toString().length() == 0 )
	    		{
		    		edtprogramme.setError( "Your study programme is required!" );
		    		Toast.makeText(getApplicationContext(), "Your study programme is required!", Toast.LENGTH_LONG).show();
	    		}
		    
	    	 	//else if(!(edtEmail.getText().toString()).matches(emailPattern)){
	    	else if(!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
		    	    // e-mail is invalid
		    		edtEmail.setError( "Enter the correct email format!" );
		    		Toast.makeText(getApplicationContext(), "Enter the correct email format!", Toast.LENGTH_LONG).show();
	    		}

	    		else if(!(edtDob.getText().toString()).matches(datePattern)){
	    		edtDob.setError( "Enter the correct date and format as dd/mm/yyyy" );
	    		Toast.makeText(getApplicationContext(), "Enter the correct date and format as dd/mm/yyyy", Toast.LENGTH_LONG).show();
	    	}
	    	else if( edtMobilePhoneNumber.getText().toString().length() != 10 ){
	    		edtMobilePhoneNumber.setError( "Your Mobile No Must be 10 digits!" );
	    		Toast.makeText(getApplicationContext(), "Your Mobile No Must be 10 digits!", Toast.LENGTH_LONG).show();
	    		
	    	}
	    	else if( edtprogramme.getText().toString().length() == 0 )
    		{
	    		edtprogramme.setError( "Your study programme is required!" );
	    		Toast.makeText(getApplicationContext(), "Your study programme is required!", Toast.LENGTH_LONG).show();
    		}
	    	
	    	else
	    	{   
	    	Intent page2intent=new Intent(getApplicationContext(),phtregistrationpage2Activity.class);
	    	
	    	String fullName=salutationSpinner.getSelectedItem().toString()+" "+edtFirstName.getText().
	    						toString()+" "+edtLastName.getText().toString()+" "+edtOtherName.getText().toString();
	    	
			page2intent.putExtra("fullName", fullName);
			radioBtnGender=(RadioButton)findViewById(selectedGenderId);	
			
			if(salutationSpinner.getSelectedItem().toString().equalsIgnoreCase("Mr.")){
				gender="Male";
			}else if(salutationSpinner.getSelectedItem().toString().equalsIgnoreCase("Ms.")){
				gender="Female";
			}
			page2intent.putExtra("gender", gender);
			page2intent.putExtra("dob", edtDob.getText().toString());
			page2intent.putExtra("email", edtEmail.getText().toString());
			page2intent.putExtra("msn_email",edtMsn_emailEditText.getText().toString());
			page2intent.putExtra("mobilePhoneNumber", edtMobilePhoneNumber.getText().toString());
			page2intent.putExtra("adm_no", edtAdm_no.getText().toString());
			page2intent.putExtra("studyProgramme", edtprogramme.getText().toString());
			startActivity(page2intent);
			//clearFields();
			}
			}
		});
    }
    public int clearFields(){
    	edtFirstName.setText(null);
    	edtLastName.setText(null);
    	edtDob.setText(null);
    	edtEmail.setText(null);
    	edtMobilePhoneNumber.setText(null);
    	edtAdm_no.setText(null);
    	edtprogramme.setText(null);
    	radioGroupGender.clearCheck();
    	return 0;
    }
    public void PopulateSpinners(){

        salutationSpinner=(Spinner)findViewById(R.id.salutationSpinner);
        salutationAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,salutationItems);
        salutationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salutationSpinner.setAdapter(salutationAdapter);
    }

}
