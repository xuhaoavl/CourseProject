package com.example.mobilesafe.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.example.mobilesafe.domain.SmsInfo;
import com.example.mobilesafe.engine.SmsInfoService;

public class TestSmsInfoService extends AndroidTestCase {

	
	public void testGetSmsInfos() throws Exception{
		SmsInfoService service  = new SmsInfoService(getContext());
		List<SmsInfo>  smsinfos = service.getSmsInfos();
		assertEquals(5, smsinfos.size());
	}
}
