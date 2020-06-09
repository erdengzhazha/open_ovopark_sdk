package com.ovopark.sdk.openplatform.client;

import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;

public class InitClient extends BaseClient{

	private static InitClient initClient;
	
	private InitClient(String applicationKey, String applicationSecret,String token) {
		this.setApplicationKey(applicationKey);
		this.setApplicationSecret(applicationSecret);
		// TODO Auto-generated constructor stub
		GwInitRequestHandler reqHandler = GwInitRequestHandler.getGwInitRequestHandler();
		reqHandler.setAuthenticator(token);
	}
	
	public static void InitClient(String applicationKey, String applicationSecret,String token) {
		if(initClient == null) {
			initClient = new InitClient(applicationKey, applicationSecret,token);
		}
		//return initClient;
	}
	

}
