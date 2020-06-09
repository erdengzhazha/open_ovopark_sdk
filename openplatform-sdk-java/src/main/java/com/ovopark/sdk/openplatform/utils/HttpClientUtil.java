package com.ovopark.sdk.openplatform.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;

import com.alibaba.fastjson.JSON;

/**
    * @ClassName: HttpClientUtil
    * @Description: TODO(
    *  Http客户端工具类<br/>
    *  这是内部调用类，请不要在外部调用。)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date  2017年10月30日 上午9:41:22 
    *
 */
public class HttpClientUtil {
	/**
	 * 
	    * @Title: getURL
	    * @Description: TODO( 获取不带查询串的url)
	    * @param @param strUrl
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public static String getURL(String strUrl) {
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
	public static String getQueryString(String strUrl) {
		if(null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if(-1 != indexOf) {
				return strUrl.substring(indexOf+1, strUrl.length());
			} 
			
			return "";
		}
		return strUrl;
	}
	
	public static String getJson(String strUrl) {
		String s=getQueryString(strUrl);
		Map<String,String> map=new HashMap<String,String>();
		String[] arrys=s.split("&");
		for (int i = 0; i < arrys.length; i++) {
			String[] kv=arrys[i].split("=");
			map.put(kv[0], kv[1]);
		}
		String json=JSON.toJSONString(map);
		System.err.println(json);
//		byte[] bts = JSON.toJSONBytes(json);
//		return bts;
		return json;
	}
	
	public static HttpURLConnection getHttpURLConnection(String strUrl)
			throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		return httpURLConnection;
	}
	/**
	    * @Title: doOutput
	    * @Description: TODO( 
	    * 处理输出
	    * 注意:流关闭需要自行处理)
	    * @param @param out
	    * @param @param data
	    * @param @param len
	    * @param @throws IOException    参数
	    * @return void    返回类型
	    * @throws
	 */
	public static void doOutput(OutputStream out, byte[] data, int len)
			throws IOException {
		int dataLen = data.length;
		int off = 0;
		while (off < data.length) {
			if (len >= dataLen) {
				out.write(data, off, dataLen);
				off += dataLen;
			} else {
				out.write(data, off, len);
				off += len;
				dataLen -= len;
			}
			// 刷新缓冲区
			out.flush();
		}
	}
	/**
	 * InputStream转换成String
	 * 注意:流关闭需要自行处理
	 * @param in
	 * @param encoding 编码
	 * @return String
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in,String encoding) throws IOException{  
        return new String(InputStreamTOByte(in),encoding);
    }
	/**
	 * InputStream转换成Byte
	 * 注意:流关闭需要自行处理
	 * @param in
	 * @return byte
	 * @throws Exception
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException{  
		int BUFFER_SIZE = 4096;  
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = in.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
        data = null;  
        byte[] outByte = outStream.toByteArray();
        outStream.close();
        return outByte;  
    } 
}
