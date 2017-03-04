package com.example.ecampusmobile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class lastActivity extends Activity {
private Button btnRe_registration,btnExit,btnHomePage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        Drawable a = findViewById(R.id.re_registrationButton).getBackground(); 
        Drawable b = findViewById(R.id.exitButton).getBackground(); 
        Drawable c=findViewById(R.id.homepageButton).getBackground();
        
        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);  
        
        a.setColorFilter(filter); 
        b.setColorFilter(filter); 
        c.setColorFilter(filter);
        
    	btnRe_registration=(Button)findViewById(R.id.re_registrationButton);
    	btnRe_registration.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {

				Intent pht112intent=new Intent(getApplicationContext(),phtregistrationActivity.class);
				startActivity(pht112intent);
				
			}
		});
        
    	btnExit=(Button)findViewById(R.id.exitButton);
    	btnExit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				finish();
		         System.exit(0);			
			}
		});
    	btnHomePage=(Button)findViewById(R.id.homepageButton);
    	btnHomePage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goHome=new Intent(getApplicationContext(),eCampusMainActivity.class);
				startActivity(goHome);
			}
		});
    	
    }
   

}
