package com.ovopark.sdk.openplatform.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.ovopark.sdk.openplatform.config.OpenplatformConst;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;
import com.ovopark.sdk.openplatform.handle.RequestHandler;
import com.ovopark.sdk.openplatform.kit.DateKit;
import com.ovopark.sdk.openplatform.utils.ApiConst;
import com.ovopark.sdk.openplatform.utils.OkHttpUtil;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkClient {
	private OkHttpUtil okHttpUtil;
	//private static OvoParkHttpClient ovoParkHttpClient;
	//private static final String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)";
	private static final String Environment = "environment";
	/** 请求方法 */
	//private String method;
	/** 错误信息 */
	//private String errInfo;
	/** 字符编码 */
	//private String charset;
	/** http应答编码 */
	//private int responseCode;
	/** 开放环境 */
	//private String OpenEnvironment;
	
	//private InputStream inputStream;
	/** 超时时间,以秒为单位 */
	//private int timeOut;

	/** 请求内容，无论post和get，都用get方式提供 */
	//private String reqContent;
	/** 应答内容 */
	//private String resContent;
	
	private Map<String,String> headers;
	
	
	private String contentType;
	//网络缓存时间为7天
	private static final int CACHE_STALE_LONG =60*60*24*7;
	//查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
	private static final String CACHE_CONTROL_CACHE="only-if-cached,max-stale="+CACHE_STALE_LONG;
	//查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
	public static final String CACHE_CONTROL_NETWORK="max-age=0";
	
	private static OkClient okClient;
	private OkHttpClient okHttpClient;
	
	private OkClient() {
		//Cache cache = new Cache(new File("C:\\Users\\chenwei\\Desktop\\cache","HttpCache"),1024 * 1024 *10 );
		//Date startTime = new Date();
		this.okHttpClient = new OkHttpClient.Builder()
							.retryOnConnectionFailure(true) //重连超时
							.connectTimeout(15,TimeUnit.SECONDS) //连接超时
							.writeTimeout(20, TimeUnit.SECONDS)  //写超时
							.readTimeout(20, TimeUnit.SECONDS)   //读超时
							//.cache(cache)
							//.addNetworkInterceptor(new NetCacheInterceptor())
							.build();
		//Date endTime = new Date();
		//System.out.println("实例化对象需要的时间"+(endTime.getTime()-startTime.getTime())+"毫秒");
		this.okHttpUtil = new OkHttpUtil();
		//this.reqContent = "";
		//this.resContent = "";
		//this.method = "POST";
		//this.errInfo = "";
		//this.charset = "UTF-8";
		//this.responseCode = 0;
		//this.timeOut = 30;//30秒
		//this.inputStream = null;
		this.headers=new HashMap<String,String>();
		//this.contentType=ApiConst.CONTENT_TYPE_FORM;
		headers.put(Environment,OpenplatformConst.Environment.FORMAL.getValue());
	}
	
	/**
	 * 单例获取OkHttpClient对象
	 * @return
	 */
	public static OkClient getOkHttpClient() {
		
		if(okClient == null) {
			okClient = new OkClient();
		}
		return okClient;
	}
	public static final MediaType JSON = MediaType.get("application/x-www-form-urlencoded;charset=utf-8");
	
	/**
	 * 发送请求
	 * @param url
	 * @param reqContent
	 * @param okClient
	 * @return
	 * @throws IOException
	 */
	public	String doPost(GwInitRequestHandler reqHandler) throws IOException {
			
			//设置时间戳
		    reqHandler.setParameter("_timestamp", DateKit.DateTime2Str(new Date(), DateKit.getSampleTimeFormat()));
			//获取请求带参数的url， 对数据进行处理
			String requestUrl = reqHandler.getRequestURL();
			//System.out.println("requestURL="+requestUrl);
			//获取debug信息
			String debuginfo = reqHandler.getDebugInfo();
			// System.out.println("debuginfo:" + debuginfo);
			
			//设置请求内容
		    RequestBody body = RequestBody.create(okHttpUtil.getQueryString(requestUrl), JSON);
		    Request request = new Request.Builder()
		    	.addHeader("authenticator", reqHandler.getAuthenticator())
		        .url(okHttpUtil.getURL(requestUrl))
		        .post(body)
		        .build();
		    try (Response response = okHttpClient.newCall(request).execute()) {
		    	
		      return response.body().string();
		    }
		    
		  }
	
	
	/**
	 * Get请求
	 * @param reqHandler
	 * @return
	 */
	public String doGet(GwInitRequestHandler reqHandler) {
		  Request request = new Request.Builder()
		      .url(this.okHttpUtil.getURL(reqHandler.getRequestURL()))  //设置网关
		      .build();
		  try (Response response = this.okHttpClient.newCall(request).execute()) {
		    return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
}

