package com.example.ecampusmobile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ReagistrationStatusActivity extends Activity {
	String adm_no,name;
	InputStream is=null;
	String result=null;
	String line=null;	
	
	private Button statusButton;
	private EditText adm_noEditText;
	private TextView responseTextView;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagistration_status);
        
        Drawable a = findViewById(R.id.statusButton).getBackground(); 
        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);  
        a.setColorFilter(filter); 


        responseTextView=(TextView)findViewById(R.id.responseTextView);
        responseTextView.setVisibility(View.GONE);
        adm_noEditText=(EditText)findViewById(R.id.adm_noEditText);
        statusButton=(Button)findViewById(R.id.statusButton);
        statusButton.setOnClickListener(new OnClickListener() {
		@Override
			public void onClick(View v) {
			
			ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			   if (connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)){
				   adm_no=adm_noEditText.getText().toString();
					select();

			   }else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {            
			       //Not connected.        
			       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
			   } 
				 			
				 
			}
		} );
      
    }

    public void select()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 
	nameValuePairs.add(new BasicNameValuePair("adm_no",adm_no));
    	
    	try
    	{
		HttpClient httpclient = new DefaultHttpClient();
	      //  HttpPost httppost = new HttpPost("http://10.0.2.2/masenoEcampusmobileConnect/getstudentdetails.php");
	        HttpPost httppost = new HttpPost("http://elearning2.maseno.ac.ke/masenoEcampusmobileConnect/getstudentdetails.php");;

	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	}
        catch(Exception e)
	{
        	Log.e("Fail 1", e.toString());
	    	Toast.makeText(getApplicationContext(), "Invalid IP Address",Toast.LENGTH_LONG).show();
	    	//Toast.makeText(getApplicationContext(), "Your internet connection is down",Toast.LENGTH_LONG).show();
	}     
        
        try
        {
         	BufferedReader reader = new BufferedReader
				(new InputStreamReader(is,"iso-8859-1"),8);
            	StringBuilder sb = new StringBuilder();
            	while ((line = reader.readLine()) != null)
		{
       		    sb.append(line + "\n");
           	}
            	is.close();
            	result = sb.toString();
	        Log.e("pass 2", "connection success ");
	}
        catch(Exception e)
    	{
		Log.e("Fail 2", e.toString());
	}     
       
   	try
    	{
        	JSONObject json_data = new JSONObject(result);
        	name=(json_data.getString("name"));
        	if(!name.equalsIgnoreCase("not registered")){
        	String email=(json_data.getString("email"));
		    //Toast.makeText(getBaseContext(), "You are registered as: "+name+" with the email address: "+email,Toast.LENGTH_LONG).show();
        	responseTextView.setText("You are registered as: "+name+" with the email address: "+email);
    		responseTextView.setVisibility(View.VISIBLE);
		    }
        	else {
			 //   Toast.makeText(getBaseContext(), "You are not registered",Toast.LENGTH_LONG).show();
        		responseTextView.setText("You are not registered");
        		responseTextView.setVisibility(View.VISIBLE);

		    }
    	}
        catch(Exception e)
    	{
        	Log.e("Fail 3", e.toString());
    	}
    }  
    
    
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reagistration_status, menu);
        return true;
    }
  

}
