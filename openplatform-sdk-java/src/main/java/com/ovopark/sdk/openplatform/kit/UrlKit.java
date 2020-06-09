package com.ovopark.sdk.openplatform.kit;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
    * @ClassName: UrlKit
    * @Description: TODO(Url操作工具类)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date 2017年9月15日 下午3:40:14
 */
public class UrlKit {
	/**
	    * @Title: encode
	    * @Description: TODO(编码URL)
	    * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。
	    * @param @param url
	    * @param @param charset	编码
	    * @return String    编码后的URL
	    * @throws
	 */
	public static String encode(String url, String charset) {
		try {
			return URLEncoder.encode(url, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException( "URL encode error:" + e.getMessage());
		}
	}
	/**
	    * @Title: decode
	    * @Description: TODO(解码URL)
	    * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。
	    * @param @param url
	    * @param @param charset	编码
	    * @return String    解码后的URL
	    * @throws
	 */
	public static String decode(String url, String charset) {
		try {
			return URLDecoder.decode(url, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException( "URL decode error:" + e.getMessage());
		}
	}

}
