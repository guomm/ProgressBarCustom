This progrem is about custom progressbar.There are two forms,one is circular progressbar,the other is horizontal progressbar.
Here is directions.(自定义进度条，有两种格式，分别是水平方向的和圆形的)

// for circular 圆形的进度条
```
  ProgressDialogCycle.show(this, "loading...", true, new android.content.DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "cancel dialog", Toast.LENGTH_LONG).show();
			}
		});
```
![image](https://github.com/guomm/ProgressBarCustom/circular.jpg)

//for horizontal 水平方向的进度条
```
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
```	
		
![image](https://github.com/guomm/ProgressBarCustom/horizontal.jpg)

More detailed information please see the project.
