package com.ovopark.sdk.openplatform.handle;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;

import com.ovopark.sdk.openplatform.config.OpenplatformConst;
import com.ovopark.sdk.openplatform.kit.CharsetKit;
import com.ovopark.sdk.openplatform.kit.PoJoKit;
import com.ovopark.sdk.openplatform.kit.StrKit;
import com.ovopark.sdk.openplatform.kit.UrlKit;
import com.ovopark.sdk.openplatform.utils.OvoParkUtils;
import com.ovopark.sdk.openplatform.utils.SignUtils;


public class RequestHandler {
	private HttpServletRequest request;
	private HttpServletResponse response;
	/** 请求的参数 */
	protected static SortedMap<String,Object> parameters;
	/** 网关地址 */
	private String gateUrl;
	/** 方法名 */
	private String method;
	/** 密匙 */
	private String applicationKey;
	/** 秘钥 */
	private String applicationSecret;
	/** debug信息 */
	private String debugInfo;
	/**是否要去除json转义 */
	private boolean jsonUnescape;
	/**版本号**/
	private String version;
	/**请求方式post,get,put,delete**/
	private String requestMode;
	
	public RequestHandler() {
		this.gateUrl = "";
		this.method = "";
		this.applicationKey = "";
		this.applicationSecret = "";
		this.parameters = new TreeMap<String,Object>();
		this.debugInfo = "";
		this.jsonUnescape=false;
	}
	
	/**
	 * 获取参数值
	 * @param parameter 参数名称
	 * @return String 
	 */
	public String getParameter(String parameter) {
		String s = (String)this.parameters.get(parameter); 
		return (null == s) ? "" : s;
	}
	
	/**
	 * 设置参数值
	 * @param parameter 参数名称
	 * @param parameterValue 参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if(null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}
	/**
	    * @Title: setPoJo
	    * @Description: TODO(PoJo类参数反射成map key value)
	    * @param @param pojo    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void setPoJo(Object pojo) {
		Class<?> clazz = pojo.getClass();
		Field[] fields = PoJoKit.getNeedFields(clazz);
		for (Field field : fields) {
		      try {
		    	String v = "";
				// 抑制Java对修饰符的检查
			    field.setAccessible(true);
			    String key=field.getName();
			    Object value=field.get(pojo);
				if(null != value) {
					v = value.toString().trim();
				}
				this.parameters.put(key, v);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public String getGateUrl() {
		return gateUrl;
	}
	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	protected void createSign(boolean unescape) {
		StringBuffer sb = new StringBuffer();
		sb.append( this.getApplicationSecret());
		parameters.put("_mt", this.getMethod());
		parameters.put("_akey", this.getApplicationKey());
		String version=this.getVersion();
		if(!StrKit.isBlank(version)) {
			parameters.put("_version",version);
		}
		String requestMode=this.getRequestMode();
		if(!StrKit.isBlank(requestMode)) {
			parameters.put("_requestMode",requestMode);
		}
		Set<Entry<String, Object>> es = this.parameters.entrySet();
		Iterator<Entry<String, Object>> it = es.iterator();
		while(it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(unescape&&k.equals(OpenplatformConst.JSON_DATA)) {
				v=StringEscapeUtils.unescapeJava(v);
				v=UrlKit.encode(v, CharsetKit.UTF_8);
			}
			
//			if(null != v && !"".equals(v) && !"_sig".equals(k)) {
//				sb.append(k + v );
//			}
			if( !"_sig".equals(k)) {
				sb.append(k + v );
			}
		}
		sb.append( this.getApplicationSecret());
		String str=sb.toString();
		String sign_method=this.getParameter("_sm");
		String sign =SignUtils.sign(sign_method, str); //签名
		this.setParameter("_sig", sign);
		//debug信息
		this.setDebugInfo(str + " => sign:" + sign);
	}
	
	
	
	/**
	 * 获取带参数的请求URL
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public String getRequestURL(){
		boolean flag=isJsonUnescape(); //判断json是否需要转义
		this.createSign(flag); //获取签名
		StringBuffer sb = new StringBuffer();
		String enc = getCharset();
		Set<Entry<String, Object>> es = this.parameters.entrySet();
		Iterator<Entry<String, Object>> it = es.iterator();
		while(it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(flag) {
				k=StringEscapeUtils.unescapeJava(k);
				v=StringEscapeUtils.unescapeJava(v);
			}
			try {
				sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		return this.getGateUrl() + "?" + reqPars;
	}
	
	protected String getCharset() {
		return OvoParkUtils.getCharacterEncoding(this.request, this.response);
	}
	/**
	*设置debug信息
	*/
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	public SortedMap<String, Object> getAllParameters() {		
		return this.parameters;
	}
	/**
	*获取debug信息
	*/
	public String getDebugInfo() {
		return debugInfo;
	}

	public boolean isJsonUnescape() {
		return jsonUnescape;
	}

	public void setJsonUnescape(boolean jsonUnescape) {
		this.jsonUnescape = jsonUnescape;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequestMode() {
		return requestMode;
	}

	public void setRequestMode(String requestMode) {
		this.requestMode = requestMode;
	}
	
	
}
