package com.ovopark.sdk.openplatform;

import com.ovopark.sdk.openplatform.client.InitClient;
import com.ovopark.sdk.openplatform.group.face.ApiFace;

public class TestGroup2 {

	public static void main(String[] args) {
		String authenticator ="7E1AFAAA8D5E4C521C7ADD319F12BFE0D392E057CF5C659331E433EE4FF4CF5AD600EDE2CA06B280AD2B5E2705387C46";
		String applicationkey ="S107-00000183";
		String applicationSecret ="fa763b15729b35320270d326ee170bb8";
		InitClient.InitClient(applicationkey,applicationSecret,authenticator);
		ApiFace apiFace = ApiFace.getApiFace();
		String dataUser_Json="";
		
		//System.out.println(apiFace.addUser(dataUser_Json, null));
		//System.out.println(apiFace.queryUserCallBack(null, null, null, null, null, true, null, null, null, null, null, null, null, null, null, null, null, null, null));
		//System.out.println(apiFace.addGroup(null, 262, null));
		//System.out.println(apiFace.queryGroup(null, null));
		//System.out.println(apiFace.queryDevice(null, null, null, null));
		
	}
}
