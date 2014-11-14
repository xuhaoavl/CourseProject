package com.example.mobilesafe.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mobilesafe.R;
import com.example.mobilesafe.util.MD5Encoder;

public class DemoActivity extends Activity {
	protected static final int STOP = 100;
	private ImageView iv;
	private ProgressBar pb;
	private LinearLayout ll;
	private AnimationDrawable anim;
	private ScrollView sv;
	private SQLiteDatabase db;
	private boolean flagscanning = false;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			 if(msg.what==STOP){
			     ll.removeAllViews();
				 anim.stop();
				 
				 }
			String str = (String) msg.obj;
			 TextView tv = new TextView(getApplicationContext());
			 tv.setText(str);
			 ll.setOrientation(LinearLayout.VERTICAL);
			 ll.addView(tv);
			 sv.scrollBy(0, 20);

			System.out.println(str);

		}

	};

	private void copyfile() {
		AssetManager manager = getAssets();
		try {
			InputStream is = manager.open("antivirus.db");
			File file = new File("/sdcard/antivirus.db");
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;

			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shadu);
		
		File file = new File("/sdcard/antivirus.db");
		if (!file.exists()) {
			copyfile();
		}
		//String path = getAssets().toString() + "antivirus.db";
		db = SQLiteDatabase.openDatabase("/sdcard/antivirus.db", null,
				SQLiteDatabase.OPEN_READONLY);
		iv = (ImageView) this.findViewById(R.id.iv);
		pb = (ProgressBar) this.findViewById(R.id.progressBar1);
		ll = (LinearLayout) this.findViewById(R.id.ll);
		iv.setBackgroundResource(R.drawable.anti_anim);
		sv = (ScrollView) this.findViewById(R.id.sv);
		anim = (AnimationDrawable) iv.getBackground();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(flagscanning)
		return false;
			
		if (event.getAction() == MotionEvent.ACTION_UP) {
			flagscanning= true;
			anim.start();
			new Thread() {
				public void run() {
					List<PackageInfo> infos = getPackageManager()
							.getInstalledPackages(
									PackageManager.GET_UNINSTALLED_PACKAGES
											| PackageManager.GET_SIGNATURES);
					// 获取每一个应用程序的签名 获取到这个签名后 需要在数据库里面查询
					pb.setMax(infos.size());
					int total = 0;
					int virustotal = 0;
					for (PackageInfo info : infos) {
						total++;
						try {
							sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Message msg = Message.obtain();
						msg.obj = "正在扫描" + info.packageName;
						handler.sendMessage(msg);
						Signature[] signs = info.signatures;
						String str = signs[0].toCharsString();
						String md5 = MD5Encoder.encode(str);
						
						Cursor cursor = db.rawQuery(
								"select desc from datable where md5=?",
								new String[] { md5 });
						if (cursor.moveToFirst()) {
							String desc = cursor.getString(0);
							msg = Message.obtain();
							msg.obj = info.packageName + ": " + desc;
							
							handler.sendMessage(msg);
							virustotal++;
						}
						cursor.close();
						pb.setProgress(total);

					}
					Message msg = Message.obtain();
					msg.what = STOP;
					msg.obj = "扫描完毕 ,共发现" + virustotal + "个病毒";
					handler.sendMessage(msg);
					flagscanning = false;
					pb.setProgress(0);
				};
			}.start();
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDestroy() {
		if (db.isOpen())
			db.close();
		super.onDestroy();
	}

}