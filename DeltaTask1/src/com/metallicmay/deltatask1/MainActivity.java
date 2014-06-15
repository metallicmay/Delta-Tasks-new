package com.metallicmay.deltatask1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private Button startbutton;
	private Button pausebutton;
	private Button resetbutton;
	
	private TextView timerval, lapslist;
	
	
	private long starttime=0L;
	private long time=0L;
	private long timeswap=0L;
	private long updatedtime=0L;
	private Handler handler=new Handler();
	

public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	timerval=(TextView)findViewById(R.id.timerval);
	startbutton=(Button)findViewById(R.id.startbutton);
	startbutton.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			starttime = SystemClock.uptimeMillis();
			handler.postDelayed(updateTimerMethod, 0); 
	}
	});
    
	pausebutton = (Button) findViewById(R.id.pausebutton);
	pausebutton.setOnClickListener(new View.OnClickListener() {
	public void onClick(View view) {
	timeswap += time;
	handler.removeCallbacks(updateTimerMethod);

	}
	});
	
	resetbutton= (Button) findViewById(R.id.resetbutton);
	resetbutton.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			 try{
			 
			 }catch(Exception e){e.printStackTrace();}	
			 String timetext=(String)timerval.getText();
			lapslist.append(timetext+"\r\n");
			handler.removeCallbacks(updateTimerMethod);
			 starttime = 0L;
			 time = 0L; 
			 timeswap = 0L;
			 updatedtime = 0L;
			 timerval.setText("00:00:00");
            		   
		}
	});
	

 }
private Runnable updateTimerMethod = new Runnable() {

	public void run() {
	time = SystemClock.uptimeMillis()-starttime;
	updatedtime = timeswap + time;
	int secs = (int) (updatedtime / 1000);
	int mins = secs / 60;
	secs = secs % 60;
	int milliseconds = (int) (updatedtime % 1000);
	timerval.setText(""+ mins + ":"+ String.format("%02d", secs) + ":"+ String.format("%03d", milliseconds));
	handler.postDelayed(this, 0);
	}

	};
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}	