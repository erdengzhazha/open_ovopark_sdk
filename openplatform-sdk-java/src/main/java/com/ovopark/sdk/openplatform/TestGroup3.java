package com.ovopark.sdk.openplatform;

import com.ovopark.sdk.openplatform.client.InitClient;
import com.ovopark.sdk.openplatform.group.face.ApiFace;
import com.ovopark.sdk.openplatform.group.openplatform_base.ApiOpenplatformBase;

public class TestGroup3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				String authenticator ="7E1AFAAA8D5E4C521C7ADD319F12BFE0D392E057CF5C659331E433EE4FF4CF5AD600EDE2CA06B280AD2B5E2705387C46";
				String applicationkey ="S107-00000183";
				String applicationSecret ="fa763b15729b35320270d326ee170bb8";
				InitClient.InitClient(applicationkey,applicationSecret,authenticator);
				ApiOpenplatformBase apiOpenplatformBase = ApiOpenplatformBase.getApiOpenplatformBase();
				System.out.println(apiOpenplatformBase.getStores(262, null));
				System.out.println(apiOpenplatformBase.getUsers(262, null, null, null, null));
				System.out.println(apiOpenplatformBase.getAgeData(262, null, null, null, null));
	}
	
}
