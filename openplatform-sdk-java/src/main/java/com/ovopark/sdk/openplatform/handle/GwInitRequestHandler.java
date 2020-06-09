package com.ovopark.sdk.openplatform.handle;


import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import com.ovopark.sdk.openplatform.config.OpenplatformConst;
import com.ovopark.sdk.openplatform.kit.DateKit;



public class GwInitRequestHandler extends RequestHandler{
	private static GwInitRequestHandler gwInitRequestHandler;
	private String authenticator="";
	private  GwInitRequestHandler() {
		init();
		
	}
	
	public String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}

	/**
	 * 初始化基础公共请求参数
	 * 固定不变的值
	 */
	 void init() {
		this.setParameter("_aid", OpenplatformConst.AID);
		this.setParameter("_sm",OpenplatformConst.Sm.MD5.getValue());
		this.setParameter("_requestMode", OpenplatformConst.RequestMode.POST.getValue());
		this.setParameter("_version", OpenplatformConst.VERSION);
		//this.setParameter("_timestamp", DateKit.DateTime2Str(new Date(), DateKit.getSampleTimeFormat()));
		//签名
		this.setParameter("_sig", "");
		this.setParameter("_format", "json");
	}
	
	public static GwInitRequestHandler getGwInitRequestHandler() {
		if(gwInitRequestHandler==null) {
			gwInitRequestHandler = new GwInitRequestHandler();
		}
		
		return gwInitRequestHandler;
	} 
	
	/**
	 * 设置报文头
	 * @param sortedMap
	 * @param key
	 * @param secret
	 * @param GateUrl
	 * @param method
	 */
	public void setHead(SortedMap<String,Object> sortedMap,String method) {
		this.setMethod(method);
		for(Map.Entry<String, Object> a:sortedMap.entrySet()) {
			String parameter =a.getKey();
			String value=(String)a.getValue();
			String v = "";
			if(null != value) {
			v = value.trim();
			}
			this.parameters.put(parameter, v);
		}
	}
	
	public void createSign() {
		super.createSign(false);
		this.setParameter("_sig", getParameter("_sig").toUpperCase());
	}
}
