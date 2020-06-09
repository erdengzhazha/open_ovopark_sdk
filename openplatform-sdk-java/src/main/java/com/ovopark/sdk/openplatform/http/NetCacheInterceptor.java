package com.ovopark.sdk.openplatform.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * 此拦截器废弃掉了
 * 作用：给okHttp 加缓存配置
 * @author chenwei
 *
 */
public class NetCacheInterceptor implements Interceptor{

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request =chain.request();
		Response orResponse = chain.proceed(request);
		
		//设置响应的缓存时间为60秒,即设置Cache-Control头，并移除pragma消息头，
		//因为pragma也是控制缓存的一个消息头属性
		orResponse = orResponse.newBuilder()
				.removeHeader("pragma")
				.header("Cache-Control", "max-age=60")
				.build();
		return orResponse;
	}

}
