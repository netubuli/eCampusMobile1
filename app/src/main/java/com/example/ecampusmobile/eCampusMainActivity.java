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
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
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
import android.widget.Button;
import android.widget.Toast;

public class eCampusMainActivity extends Activity {
	

Button Btn_phtregistration,Btn_exit,Btn_website,Btn_registrationstatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_campus_main);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
      /*  Drawable d = findViewById(R.id.phtregistrationButton).getBackground();
        Drawable e = findViewById(R.id.registrationstatusButton).getBackground(); 
        Drawable f = findViewById(R.id.exitButton).getBackground(); 
        Drawable g = findViewById(R.id.websiteButton).getBackground(); 
        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);  
        d.setColorFilter(filter); 
        e.setColorFilter(filter); 
        f.setColorFilter(filter); 
        g.setColorFilter(filter); */
        Btn_phtregistration=(Button)findViewById(R.id.phtregistrationButton);
        Btn_phtregistration.setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        		if(connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)) {
    				Intent pht112intent=new Intent(getApplicationContext(),phtregistrationActivity.class);
    				startActivity(pht112intent);
    				}
        		 else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {            
       		       //response to user if internet is not connected.        
       		       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
       		   } 
       		 

			}
		});
        
        Btn_registrationstatus=(Button)findViewById(R.id.registrationstatusButton);
        Btn_registrationstatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent checkStudent=new Intent(getApplicationContext(),ReagistrationStatusActivity.class);
				startActivity(checkStudent);
			}
		});
        Btn_exit=(Button)findViewById(R.id.exitButton);
        Btn_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 finish();
		         System.exit(0);
				
			}
		});
        
        Btn_website=(Button)findViewById(R.id.websiteButton);
        Btn_website.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("http://elearning.maseno.ac.ke/");
				 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				 startActivity(intent);
				
			}
		});
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_e_campus_main, menu);
        return true;
    }


}

