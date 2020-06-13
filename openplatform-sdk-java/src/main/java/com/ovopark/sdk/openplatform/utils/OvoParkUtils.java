package com.ovopark.sdk.openplatform.utils;

import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;

public class OvoParkUtils {
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
}
