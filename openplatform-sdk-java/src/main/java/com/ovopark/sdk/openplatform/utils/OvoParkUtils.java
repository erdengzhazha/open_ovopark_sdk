package com.ovopark.sdk.openplatform.utils;

import java.io.IOException;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovopark.sdk.openplatform.client.BaseClient;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;
import com.ovopark.sdk.openplatform.http.OkClient;

public class OvoParkUtils extends BaseClient{
	/**
	 * 获取编码字符集
	 * @param request
	 * @param response
	 * @return String
	 */
	public static String getCharacterEncoding(HttpServletRequest request,
			HttpServletResponse response) {
		if(null == request || null == response) {
			return "UTF-8";
		}
		String enc = request.getCharacterEncoding();
		if(null == enc || "".equals(enc)) {
			enc = response.getCharacterEncoding();
		}
		if(null == enc || "".equals(enc)) {
			enc = "UTF-8";
		}
		return enc;
	}
	
	/**
	 * 可选参数设置
	 * @param sort
	 * @param key
	 * @param orgid
	 */
	public void setNoMustSort(SortedMap<String, Object> sort,String key,Object value) {
		if(value != null) {
			 sort.put(key, value.toString());
		}
	}
	
	/**
	 * 必填参数设置
	 * @param sort
	 * @param key
	 * @param value
	 */
	public void setMustSort(SortedMap<String, Object> sort,String key,Object value) {
		if(value== null) {
			sort.put(key, "");
		}else {
			sort.put(key, value.toString());
		}
	}
	
	/**
	 * 废弃的方法
	 * @param geGwInitRequestHandler
	 * @param authenticator
	 */
	public void isSetAuthenticator(GwInitRequestHandler geGwInitRequestHandler,String authenticator) {
		if(authenticator!=null) {
			geGwInitRequestHandler.setAuthenticator(authenticator);
		}else {
			geGwInitRequestHandler.setApplicationKey("");
		}
	}
	
	/**
	 * 公共部分的工具方法
	 * 目的：设置请求参数，并发出http请求，返回值
	 * @param version
	 * @param method
	 * @return
	 */
	public String publicUtil(String version,String method,SortedMap<String, Object>  sort) {
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,method);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
}
