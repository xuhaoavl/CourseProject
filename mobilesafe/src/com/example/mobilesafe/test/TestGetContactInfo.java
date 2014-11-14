package com.example.mobilesafe.test;

import java.util.List;

import android.test.AndroidTestCase;

import com.example.mobilesafe.domain.ContactInfo;
import com.example.mobilesafe.engine.ContactInfoService;

public class TestGetContactInfo extends AndroidTestCase {

	public void testGetContacts() throws Exception{
		ContactInfoService service = new ContactInfoService(getContext());
		List<ContactInfo> infos =  service.getContactInfos();
		for(ContactInfo info : infos ){
			System.out.println(info.getName());
			System.out.println(info.getPhone());
			
		}
	}
}
