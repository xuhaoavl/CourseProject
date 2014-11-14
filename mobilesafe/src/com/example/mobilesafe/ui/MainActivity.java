package com.example.mobilesafe.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesafe.R;
import com.example.mobilesafe.adapter.MainUIAdapter;

public class MainActivity extends Activity implements OnItemClickListener {

	private static final String TAG = "MainActivity";
	private GridView gv_main;
	private MainUIAdapter adapter;
	// 用来持久化一些配置信息
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
		sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
		gv_main = (GridView) this.findViewById(R.id.gv_main);
		adapter = new MainUIAdapter(this);
		gv_main.setAdapter(adapter);
		gv_main.setOnItemClickListener(this);

		gv_main.setOnItemLongClickListener(new OnItemLongClickListener() {

			// 修改功能名字
			public boolean onItemLongClick(AdapterView<?> parent,
					final View view, int position, long id) {
				if (position == 0) {
					AlertDialog.Builder buider = new Builder(MainActivity.this);
					buider.setTitle("设置");
					buider.setMessage("请输入要更改的名称");
					final EditText et = new EditText(MainActivity.this);
					et.setHint("请输入文本");
					buider.setView(et);
					buider.setPositiveButton("确定", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							String name = et.getText().toString().trim();
							if ("".equals(name)) {
								Toast.makeText(getApplicationContext(),
										"内容不能为空", 0).show();
								return;
							} else {
								Editor editor = sp.edit();
								editor.putString("lost_name", name);
								// 完成数据的提交
								editor.commit();
								TextView tv = (TextView) view
										.findViewById(R.id.tv_main_name);
								tv.setText(name);
							}

						}
					});

					buider.setNegativeButton("取消", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
						}
					});

					buider.create().show();
				}
				return false;
			}
		});
	}

	/**
	 * 当gridview的条目被点击的时候 对应的回调 parent :　gridview view : 当前被点击的条目 Linearlayout
	 * position : 点击条目对应的位置 id : 代表的行号
	 */

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.i(TAG, "点击的位置" + position);
		switch (position) {
		case 0: // 手机防盗
			//Toast.makeText(this, "手机防盗", 0).show();
			Intent lostIntent = new Intent(MainActivity.this,
					LostProtectedActivity.class);
			startActivity(lostIntent);
			break;
		case 1:// 通讯卫士
			//Toast.makeText(this, "通讯卫士", 0).show();
			Intent callsmsIntent = new Intent(MainActivity.this, CallSmsActivity.class);
			startActivity(callsmsIntent);
			break;
		case 2:// 软件管理
			Intent appmanagerIntent = new Intent(MainActivity.this, AppManagerActivity.class);
			startActivity(appmanagerIntent);
			break;
		case 3:// 任务管理
			Intent taskmanagerIntent = new Intent(MainActivity.this,
					TaskManagerActivity.class);
			startActivity(taskmanagerIntent);
			//
			break;
		case 4:// 流量管理
			Intent trafficmanagerIntent = new Intent(MainActivity.this,
					TrafficManagerActivity.class);
			startActivity(trafficmanagerIntent);
			break;
		case 5:// 手机查毒
			Intent taskmanagerIntent2 = new Intent(MainActivity.this,
					DemoActivity.class);
			startActivity(taskmanagerIntent2);
			break;
		case 6:// 系统优化
			Log.i(TAG, "进入系统优化");
			Toast.makeText(this, "系统优化", 0).show();
			Intent clearcacheIntent = new Intent(MainActivity.this,
					ClearCacheActivity.class);
			startActivity(clearcacheIntent);
			break;
		case 7:// 高级工具
			Log.i(TAG, "进入高级工具");
			Toast.makeText(this, "高级工具", 0).show();
			Intent atoolsIntent = new Intent(MainActivity.this, AtoolsActivity.class);
			startActivity(atoolsIntent);
			break;
		case 8:// 设置中心
			Log.i(TAG, "进入设置中心");
			Toast.makeText(this, "设置中心", 0).show();
			Intent settingcenterIntent = new Intent(MainActivity.this,
					SettingCenterActivity.class);
			startActivity(settingcenterIntent);
			break;
		default:
			break;

		}

	}

}
