package com.ovopark.sdk.openplatform.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.ovopark.sdk.openplatform.kit.StrKit;




/**
 * 
    * @ClassName: ConvertUtils
    * @Description: TODO(类型转换工具类)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date 2017年8月3日 下午2:47:56
 */
public class ConvertUtils {
	public static String toStr(Object value) {
		return toStr(value, null);
	}
	
	/**
	 * 转换为String
	 * 如果给定的值为空，或者转换失败，返回默认值
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static String toStr(Object value, String defaultValue) {
		if (null == value) {
			return defaultValue;
		}
		if (value instanceof String) {
			return (String) value;
		} 
		return value.toString();
	}
	
	public static Integer toInt(Object result ){
		return toInt(result, null);
	}
	/**
	 * 转换为int
	 * 如果给定的值为空，或者转换失败，返回默认值
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Integer toInt(Object value, Integer defaultValue) {
		try {
			if(value==null)
				return defaultValue;
			if (value instanceof Integer) 
				return (Integer) value;
			if (value instanceof Number) 
				return ((Number) value).intValue();
			String strValue=(String)value;
			strValue = strValue.trim();
			if(StrKit.isBlank(strValue))
				return defaultValue;
			if (strValue.startsWith("N") || strValue.startsWith("n"))
				return -Integer.parseInt(strValue.substring(1));
			return Integer.parseInt(strValue);
		}
		catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Long toLong(Object value) {
		return toLong(value, null);
	}
	/**
	 * 转换为long<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Long toLong(Object value, Long defaultValue) {
		try {
			if (value == null) 
				return defaultValue;
			if (value instanceof Long) 
				return (Long) value;
			if (value instanceof Number) 
				return ((Number) value).longValue();
			String strValue=(String)value;
			strValue = strValue.trim();
			if(StrKit.isBlank(strValue))
				return defaultValue;
			if (strValue.startsWith("N") || strValue.startsWith("n"))
				return -Long.parseLong(strValue.substring(1));
			return Long.parseLong(strValue);
		}
		catch (Exception e) {
			return defaultValue;
		}
	}
	public static BigDecimal toBigDecimal(Object value) {
		return toBigDecimal(value, null);
	}
	/**
	 * 转换为BigDecimal<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
		try {
			if (value == null) 
				return defaultValue;
			if (value instanceof BigDecimal) 
				return (BigDecimal) value;
			if (value instanceof Long) 
				return new BigDecimal((Long) value);
			if (value instanceof Double) 
				return new BigDecimal((Double) value);
			if (value instanceof Integer) 
				return new BigDecimal((Integer) value);
			String strValue=(String)value;
			strValue = strValue.trim();
			if (StrKit.isBlank(strValue)) 
				return defaultValue;
			return new BigDecimal(strValue);
		}
		catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Double toDouble(Object value) {
		return toDouble(value, null);
	}
	/**
	 * 转换为double<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Double toDouble(Object value, Double defaultValue) {
		try {
			if (value == null) 
				return defaultValue;
			if (value instanceof Double) 
				return (Double) value;
			if (value instanceof Number) 
				return ((Number) value).doubleValue();
			String strValue=(String)value;
			strValue = strValue.trim();
			if (StrKit.isBlank(strValue)) 
				return defaultValue;
			return new BigDecimal(strValue).doubleValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Float toFloat(Object value) {
		return toFloat(value, null);
	}
	/**
	 * 转换为Float<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Float toFloat(Object value, Float defaultValue) {
		try {
			if (value == null) 
				return defaultValue;
			if (value instanceof Float) 
				return (Float) value;
			if (value instanceof Number) 
				return ((Number) value).floatValue();
			String strValue=(String)value;
			strValue = strValue.trim();
			if (StrKit.isBlank(strValue)) 
				return defaultValue;
			return Float.parseFloat(strValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	//URL编码
	public static String encode(String value,String charset){
		if (StrKit.isBlank(value))
			return StrKit.EMPTY;
		try {
			return URLEncoder.encode(value, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException( "encode  error:" + e.getMessage());
		}
	}
	//URL解码
	public static String decode(String value,String charset){
		if (StrKit.isBlank(value))
			return StrKit.EMPTY;
		try {
			return URLDecoder.decode(value, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException( "decode error:" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: toBoolean   
	 * @Description: TODO(转Boolean类型)   
	 * @param: @param value
	 * @param: @return      
	 * @return: Boolean      
	 * @throws
	 */
	public static Boolean toBoolean(Object value) {
		return toBoolean(value, null);
	}
	public static Boolean toBoolean(Object value,Boolean defaultValue) {
		try {
			if (value == null) 
				return defaultValue;
			if (value instanceof Boolean) 
				return (Boolean) value;
			String strValue=(String)value;
			strValue = strValue.trim();
			if (StrKit.isBlank(strValue)) 
				return defaultValue;
			return Boolean.parseBoolean(strValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
