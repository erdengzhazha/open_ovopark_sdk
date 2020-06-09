package com.ovopark.sdk.openplatform.http;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ovopark.sdk.openplatform.config.OpenplatformConst;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;
import com.ovopark.sdk.openplatform.utils.ApiConst;
import com.ovopark.sdk.openplatform.utils.HttpClientUtil;

/**
 * 注意：此类废弃掉了！！！！！！！！！！！！！！！！！
 * @ClassName:  OvoParkHttpClient   
 * @Description:TODO(万店掌开放平台http客户端)   
 * @author: Remiel_Mercy 
 * @date:   2019年4月30日 上午10:00:42   
 *     
 * @Copyright: 2019 www.ovopark.com Inc. All rights reserved.
 */
public class OvoParkHttpClient {
	
	private static OvoParkHttpClient ovoParkHttpClient;
	private static final String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)";
	private static final String Environment = "environment";
	/** 请求方法 */
	private String method;
	/** 错误信息 */
	private String errInfo;
	/** 字符编码 */
	private String charset;
	/** http应答编码 */
	private int responseCode;
	/** 开放环境 */
	private String OpenEnvironment;
	
	private InputStream inputStream;
	/** 超时时间,以秒为单位 */
	private int timeOut;

	/** 请求内容，无论post和get，都用get方式提供 */
	private String reqContent;
	/** 应答内容 */
	private String resContent;
	
	private Map<String,String> headers;
	
	
	private String contentType;
	
	/**
	 * 构造方法
	 */
	private OvoParkHttpClient(){
		this.reqContent = "";
		this.resContent = "";
		this.method = "POST";
		this.errInfo = "";
		this.charset = "UTF-8";
		this.responseCode = 0;
		this.timeOut = 30;//30秒
		this.inputStream = null;
		this.headers=new HashMap<String,String>();
		this.contentType=ApiConst.CONTENT_TYPE_FORM;
		headers.put(Environment,OpenplatformConst.Environment.FORMAL.getValue());
	}
	
	/**
	 * 单例获取OvoParkHttpClient对象
	 * @return
	 */
	public static OvoParkHttpClient getOvoParkHttpClient() {
		if(ovoParkHttpClient==null) {
			ovoParkHttpClient = new OvoParkHttpClient();
		}
		return ovoParkHttpClient;
	}
	/**
	 * 发送请求
	 * @param reqHandler
	 * @author chenwei
	 */
	public void request(GwInitRequestHandler reqHandler) {
		//获取请求带参数的url
		String requestUrl = reqHandler.getRequestURL();
		System.out.println("requestURL="+requestUrl);
		//获取debug信息
		String debuginfo = reqHandler.getDebugInfo();
		System.out.println("debuginfo:" + debuginfo);
		//设置请求内容
		this.setReqContent(requestUrl);
		boolean c =call();
		System.out.println(c);
		if(c){
			String resContent = this.getResContent();
			System.out.println("responseContent:" + resContent);
		}
	}
	public boolean call() {
		boolean isRet = false;
		// http
		try {
			this.callHttp();
			isRet = true;
		} catch (IOException e) {
			this.errInfo = e.getMessage();
		}
		return isRet;
	}
	
	protected void callHttp() throws IOException {
		if("POST".equals(this.method.toUpperCase())||"PUT".equals(this.method.toUpperCase())||("DELETE".equals(this.method.toUpperCase()))) {
			if(this.contentType.equals(ApiConst.CONTENT_TYPE_FORM)) {
				this.doFormUrlencoded();
			}else {
				this.doJson();
			}
		}
		else{
			this.httpGetMethod(this.reqContent);
		}
	} 
	
	/**
	    * @Title: doFormUrlencoded
	    * @Description: TODO(以post,put,delete方式通信,表单类型application/x-www-form-urlencoded)
	    * @param @param url
	    * @param @param postData
	    * @param @throws IOException    参数
	    * @return void    返回类型
	    * @throws
	 */
	protected void doFormUrlencoded()throws IOException {
		//System.out.println("\nreqContent"+this.reqContent); 
		String url = HttpClientUtil.getURL(this.reqContent); //获取的网关内容   这边的代码可以待优化  如果性能有影响了
		String queryString = HttpClientUtil.getQueryString(this.reqContent); //获取的请求体   //待优化 如果有影响了
		//System.out.println("url"+url+"\n\nqueryString"+queryString);
		byte[] postData = queryString.getBytes(this.charset); //请求体的内容utf-8形式编码
		HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);  //网关内容获取连接
		// 以post,put,delete方式通信
		conn.setRequestMethod(this.method.toUpperCase()); 
		// 设置请求默认属性
		this.setHttpRequest(conn);
		// Content-Type
		conn.setRequestProperty("Content-Type",ApiConst.CONTENT_TYPE_FORM);
		Set<String> keys=   headers.keySet();  
		Iterator<String> it= keys.iterator(); 
	    while(it.hasNext()){  
            String key=it.next();  
            String value=headers.get(key);  
            conn.setRequestProperty(key, value);
        } 
		BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
		final int len = 1024; // 1KB
		HttpClientUtil.doOutput(out, postData, len);
		// 关闭流
		out.close();
		// 获取响应返回状态码
		this.responseCode = conn.getResponseCode();
		// 获取应答输入流
		this.inputStream = conn.getInputStream();
	}
	/**
	    * @Title: httpGetMethod
	    * @Description: TODO(以http get方式通信)
	    * @param @param url
	    * @param @throws IOException    参数
	    * @return void    返回类型
	    * @throws
	 */
	protected void httpGetMethod(String url) throws IOException {
		HttpURLConnection httpConnection =
			HttpClientUtil.getHttpURLConnection(url);
		this.setHttpRequest(httpConnection);
		// 以get方式通信
		httpConnection.setRequestMethod("GET");
		Set<String> keys=   headers.keySet();  
		Iterator<String> it= keys.iterator(); 
	    while(it.hasNext()){  
            String key=it.next();  
            String value=headers.get(key);  
            httpConnection.setRequestProperty(key, value);
        } 
		this.responseCode = httpConnection.getResponseCode();
		this.inputStream = httpConnection.getInputStream();
	}
	/**
	 * 
	 * @Title: doJson   
	 * @Description: TODO(以post,put,delete方式通信,表单类型application/json)   
	 * @param: @throws IOException      
	 * @return: void      
	 * @throws
	 */
	protected void doJson() throws IOException {
		String url = HttpClientUtil.getURL(this.reqContent);
		HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);
		String jsonData = HttpClientUtil.getJson(this.reqContent);
		// 以post方式通信
		conn.setRequestMethod(this.method.toUpperCase());
		// 设置请求默认属性
		this.setHttpRequest(conn);
	    conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
		// Content-Type
		conn.setRequestProperty("Content-Type",ApiConst.CONTENT_TYPE_JSON);
		Set<String> keys=   headers.keySet();  
		Iterator<String> it= keys.iterator(); 
	    while(it.hasNext()){  
            String key=it.next();  
            String value=headers.get(key);  
            conn.setRequestProperty(key, value);
        }
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.writeBytes(jsonData);
        out.flush();
        out.close();
		// 获取响应返回状态码
		this.responseCode = conn.getResponseCode();
		// 获取应答输入流
		this.inputStream = conn.getInputStream();
	}
	
	/**
	    * @Title: setHttpRequest
	    * @Description: TODO(设置http请求默认属性)
	    * @param @param httpConnection    参数
	    * @return void    返回类型
	    * @throws
	 */
	protected void setHttpRequest(HttpURLConnection httpConnection) {
		//设置连接超时时间
		httpConnection.setConnectTimeout(this.timeOut * 1000);
		//User-Agent
		httpConnection.setRequestProperty("User-Agent", USER_AGENT_VALUE);
		//不使用缓存
		httpConnection.setUseCaches(false);
		//允许输入输出
		httpConnection.setDoInput(true);
		httpConnection.setDoOutput(true);
	}
	/**
	    * @Title: getResContent
	    * @Description: TODO(获取结果内容)
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public String getResContent() {
		try {
			this.doResponse();
		} catch (IOException e) {
			this.errInfo = e.getMessage();
			return "";
		}
		return this.resContent;
	}
	/**
	    * @Title: doResponse
	    * @Description: TODO(处理应答)
	    * @param @throws IOException    参数
	    * @return void    返回类型
	    * @throws
	 */
	protected void doResponse() throws IOException {
		if(null == this.inputStream) {
			return;
		}
		//获取应答内容
		this.resContent=HttpClientUtil.InputStreamTOString(this.inputStream,this.charset); 
		//关闭输入流
		this.inputStream.close();
	}
	/**
	 * 设置请求内容
	 * @param reqContent 表求内容
	 */
	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setHeaders(String key,String value) {
		headers.put(key, value);
	}
	public String getOpenEnvironment() {
		return OpenEnvironment;
	}
	public void setOpenEnvironment(String openEnvironment) {
		OpenEnvironment = openEnvironment;
		headers.put(Environment, OpenEnvironment);
	}
	public int getResponseCode() {
		return responseCode;
	}
	public String getErrInfo() {
		return errInfo;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
