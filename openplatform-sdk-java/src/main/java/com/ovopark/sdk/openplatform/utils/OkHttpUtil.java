package com.ovopark.sdk.openplatform.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * okHttp工具类
 * @author chenwei
 *
 */
public class OkHttpUtil{
	
	/**
	 * 
	    * @Title: getURL
	    * @Description: TODO( 获取不带查询串的url)
	    * @param @param strUrl
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public  String getURL(String strUrl) {
		if(null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if(-1 != indexOf) {
				return strUrl.substring(0, indexOf);
			} 
			
			return strUrl;
		}
		return strUrl;
	}
	/**
	 * 
	    * @Title: getQueryString
	    * @Description: TODO( 获取查询串)
	    * @param @param strUrl
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public  String getQueryString(String strUrl) {
		if(null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if(-1 != indexOf) {
				return strUrl.substring(indexOf+1, strUrl.length());
			} 
			
			return "";
		}
		return strUrl;
	}
	
	
	
}
