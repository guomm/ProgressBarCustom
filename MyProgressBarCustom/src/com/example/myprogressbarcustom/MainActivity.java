package com.example.myprogressbarcustom;

import com.myDialog.ProgressDialogCycle;
import com.myDialog.ProgressDialogHorizon;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	ProgressDialogHorizon dialog;
	int total=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//cycle dialog
//		ProgressDialogCycle.show(this, "ÕýÔÚ¼ÓÔØ...", true, new android.content.DialogInterface.OnCancelListener() {
//			
//			@Override
//			public void onCancel(DialogInterface dialog) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "cancel dialog", Toast.LENGTH_LONG).show();
//			}
//		});
		
		//horizon dialog
		 dialog=new ProgressDialogHorizon(this, "Downloading", new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "cancel dialog", Toast.LENGTH_LONG).show();
				dialog.cancel();
			}
		});
		total=100000;
		dialog.setMax(total);
		dialog.setProgress(0);
		dialog.show();
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i=0;
				while(i<total){
					i+=100;
					dialog.setProgress(i);
					if(i>=dialog.getMax()){
						dialog.cancel();
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}
}
