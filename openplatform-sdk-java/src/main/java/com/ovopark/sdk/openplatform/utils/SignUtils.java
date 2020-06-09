package com.ovopark.sdk.openplatform.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.ovopark.sdk.openplatform.kit.PoJoKit;





/**
 * 
* @ClassName: SignUtils
* @Description: TODO(签名工具类)
* @author xuefei
* @date 2016年12月13日 下午3:41:49
*
 */
public class SignUtils {
	public static final String sign_method_MD5 = "md5";
	public static final String sign_method_SHA = "sha1";
	/**sign 参数字符串签名**/
	public static final String signature = "_sig";
	
	public static final String _SM="_sm";
	public static final String _SIG="_sig";
	private static String  debugInfo;
	
	static{
		debugInfo="";
	}

	/**
	 * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
	 * uppercase(hex(sha1(secretkey1value1key2value2...secret))
	 *
	 * @param paramNames
	 *            需要签名的参数名
	 * @param paramValues
	 *            参数列表
	 * @param sign
	 *            签名信息
	 * @param sign_method
	 *            签名方法 SHA1,MD5
	 * @return
	 */
	public static String sign(Map<String, Object> paramValues, String sign, String sign_method) {
		return sign(paramValues, null, sign, sign_method);
	}

	public static String signPoJo(Object pojo, String secret) {
		String sign_method=sign_method_MD5;
		ConcurrentMap<String, Object> paramValues = new ConcurrentHashMap<String, Object>();
		Class<?> clazz = pojo.getClass();
		Field[] fields = PoJoKit.getNeedFields(clazz);
		for (Field field : fields) {
		      try {
				// 抑制Java对修饰符的检查
			    field.setAccessible(true);
			    String key=field.getName();
			    if(!key.equals(_SIG)){
			        Object value=field.get(pojo);
				    if(value!=null){
				    	if(key.equals(_SM)){
				    		sign_method=value.toString();
				    	}
				    	if(value instanceof List||value instanceof Map) {
				    		value=JSON.toJSONString(value);
				    	}
				    	paramValues.putIfAbsent(key, value);
				    }else{
				    	paramValues.putIfAbsent(key, "");
				    }
			    }
			
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<String>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			Collections.sort(paramNames);
			sb.append(secret);
			for (String paramName : paramNames) {
				String value=ConvertUtils.toStr(paramValues.get(paramName),"");
				sb.append(paramName).append(value);
			}
			sb.append(secret);
			debugInfo=sb.toString();
			System.err.println("pojo debugInfo:"+debugInfo);
			byte[] digests = null;
			sign_method=sign_method.toLowerCase();
			if (sign_method_MD5.equals(sign_method)) {
				digests = getMD5Digest(sb.toString());
			} else {
				digests = getSHA1Digest(sb.toString());
			}
			return byte2hex(digests);
		} catch (IOException e) {
			throw new RuntimeException( "apigetway sign error:" + e.getMessage());
		}
	}


	
	/**
	 * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
	 * 
	 * @param paramValues
	 * @param ignoreParamNames
	 * @param secret
	 * @return
	 */
	public static String sign(Map<String, Object> paramValues, List<String> ignoreParamNames, String secret,
			String sign_method) {
		try {
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<String>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
				for (String ignoreParamName : ignoreParamNames) {
					paramNames.remove(ignoreParamName);
				}
			}
			Collections.sort(paramNames);
			sb.append(secret);
			for (String paramName : paramNames) {
				String value=ConvertUtils.toStr(paramValues.get(paramName),"");
				sb.append(paramName).append(value);
			}
			sb.append(secret);
			debugInfo=sb.toString();
			System.err.println("map  debugInfo:"+debugInfo);
			byte[] digests = null;
			sign_method=sign_method.toLowerCase();
			if (sign_method_MD5.equals(sign_method)) {
				digests = getMD5Digest(sb.toString());
			} else {
				digests = getSHA1Digest(sb.toString());
			}
			return byte2hex(digests);
		} catch (IOException e) {
			throw new RuntimeException( "apigetway sign error:" + e.getMessage());
		}
	}

	public static String utf8Encoding(String value, String sourceCharsetName) {
		try {
			return new String(value.getBytes(sourceCharsetName), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static byte[] getSHA1Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	private static byte[] getMD5Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			bytes = md.digest(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	/**
	 * 二进制转十六进制字符串
	 *
	 * @param bytes
	 * @return
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase();
	}
	/**
	    * @Title: checkSign
	    * @Description: TODO(检查签名的有效性)
	    * @param @param sign 签名字符串
	    * @param @param applicationSecret 秘钥
	    * @param @param signatureMethod	签名方法
	    * @param @param request
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public static boolean checkSign(String sign, String applicationSecret, String signatureMethod,HttpServletRequest request) {
		HashMap<String, Object> needSignParams = new HashMap<String, Object>();
		Enumeration<String> enume = request.getParameterNames();
		while (enume.hasMoreElements()) {
			String name = enume.nextElement().toString();
			//添加所有除_sig的参数,用做于签名参数
			if (!name.equals(signature)) {
				String value=ConvertUtils.toStr(request.getParameter(name),"");
				needSignParams.put(name, value);
			}
		}
		// 签名
		String signValue = SignUtils.sign(needSignParams, applicationSecret,signatureMethod);
		if (signValue.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	public static String sign(String sign_method,String parmsvalue){
		try {
			byte[] digests = null;
			sign_method=sign_method.toLowerCase();
			if (sign_method_MD5.equals(sign_method)) {
				digests = getMD5Digest(parmsvalue);
			} else {
				digests = getSHA1Digest(parmsvalue);
			}
		return byte2hex(digests);
		} catch (IOException e) {
			throw new RuntimeException( "apigetway sign error:" + e.getMessage());
		}
	}
	public static String getDebugInfo() {
		return debugInfo;
	}
}
