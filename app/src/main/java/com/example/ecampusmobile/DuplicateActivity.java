package com.example.ecampusmobile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class DuplicateActivity extends Activity {
private Button Btn_regstatus,Btn_editadmNumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duplicate);
        
        Drawable a = findViewById(R.id.regstatusButton).getBackground(); 
        Drawable b=findViewById(R.id.changeAdm_no).getBackground();
        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);  
        a.setColorFilter(filter); 
        b.setColorFilter(filter);
        Btn_regstatus=(Button)findViewById(R.id.regstatusButton);
        Btn_regstatus.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
								Intent checkStatus=new Intent(getApplicationContext(),ReagistrationStatusActivity.class);
				startActivity(checkStatus);
				
			}
		});
        Btn_editadmNumber=(Button)findViewById(R.id.changeAdm_no);
        Btn_editadmNumber.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
								finish();
				
			}
		});
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_duplicate, menu);
        return true;
    }

   
}
