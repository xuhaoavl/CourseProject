package com.example.mobilesafe.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilesafe.R;
//
public class MainUIAdapter extends BaseAdapter {
	
	private static final String TAG = "MainUIAdapter";
	private Context context;
	private LayoutInflater inflater;
	private static ImageView iv_icon;
	private static TextView tv_name;
	private SharedPreferences sp;
	
	public MainUIAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}

	private static String[] names = { "手机防盗", "通讯卫士", "软件管理", "任务管理", "流量管理",
			"手机杀毒", "系统优化", "高级工具", "设置中心" };
	private static int[] icons = { R.drawable.widget05, R.drawable.widget02,
			R.drawable.widget01, R.drawable.widget07, R.drawable.widget05,
			R.drawable.widget04, R.drawable.widget06, R.drawable.widget03,
			R.drawable.widget08 };

	public int getCount() {
		
		return names.length;
	}

	public Object getItem(int position) {
		
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.i(TAG,"getview "+ position);
		View view = inflater.inflate(R.layout.mainscreen_item, null);
		iv_icon =  (ImageView) view.findViewById(R.id.iv_main_icon);
		tv_name =  (TextView) view.findViewById(R.id.tv_main_name);
		iv_icon.setImageResource(icons[position]);
		tv_name.setText(names[position]);
		if(position==0){
			String name = sp.getString("lost_name", null);
			if(name!=null){
				tv_name.setText(name);
			}
		}
		
		return view;
	}

}
