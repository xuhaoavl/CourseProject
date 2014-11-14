package com.example.mobilesafe.test;

import android.test.AndroidTestCase;

import com.example.mobilesafe.R;
import com.example.mobilesafe.domain.UpdataInfo;
import com.example.mobilesafe.engine.UpdataInfoService;

public class TestGetUpdataInfo extends AndroidTestCase {

	
	public void testGetInfo() throws Exception{
		UpdataInfoService service = new UpdataInfoService(getContext());
		UpdataInfo  info = service.getUpdataInfo(R.string.updataurl);
		assertEquals("2.0", info.getVersion());
	}
}
