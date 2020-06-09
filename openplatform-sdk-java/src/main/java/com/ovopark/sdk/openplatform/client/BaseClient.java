package com.ovopark.sdk.openplatform.client;

import com.ovopark.sdk.openplatform.config.OpenplatformConst;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;

public abstract class BaseClient {
	/**app key */
	private static String applicationKey;
	/** 秘钥 */
	private static String applicationSecret;
	
    protected BaseClient() {
        
    }
    
    public String getApplicationKey() {
		return applicationKey;
	}

	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

	public String getApplicationSecret() {
		return applicationSecret;
	}

	public void setApplicationSecret(String applicationSecret) {
		this.applicationSecret = applicationSecret;
	}

	protected void setCommonParameters(GwInitRequestHandler reqHandler) {
    	reqHandler.setGateUrl(OpenplatformConst.apigwUrl);
    	reqHandler.setApplicationKey(this.applicationKey);
    	reqHandler.setApplicationSecret(this.applicationSecret);
    }
    
}
