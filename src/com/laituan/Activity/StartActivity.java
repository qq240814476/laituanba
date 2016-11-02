package com.laituan.Activity;

/*import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.CommonDialog;*/

import com.example.laituanba.R;
import com.example.laituanba.R.id;
import com.example.laituanba.R.layout;
import com.example.laituanba.R.menu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

/**
 * 起始页activity
 *联系作者：QQ 240814476
 */
public class StartActivity extends Activity {

	private Dialog pd;
	private boolean ready;
	private final int SPLASH_DISPLAY_LENGHT = 1000; // 延迟六秒  
	boolean isFirstIn = false;
	 Intent intent;
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState); 
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_start);  
	        //initSDK() ;

	        SharedPreferences preferences = getSharedPreferences("first_pref",
	        	    MODE_PRIVATE);
	        	isFirstIn = preferences.getBoolean("isFirstIn", true);
	        	
	        new Handler().postDelayed(new Runnable() {  
	            public void run() { 
	            	/* if (isFirstIn) {
	            	      // start guideactivity1
	            	      intent = new Intent(StartActivity.this, GuideActivity1.class);
	            	     } else {
	            	      // start TVDirectActivity
	            	      intent = new Intent(StartActivity.this, Main.class);
	            	     }*/

	            	intent = new Intent(StartActivity.this, Main.class);
	                StartActivity.this.startActivity(intent);  
	                StartActivity.this.finish();  
	            }  
	  
	        }, SPLASH_DISPLAY_LENGHT);  
	  
	    }  


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
