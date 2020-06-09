package com.ovopark.sdk.openplatform.kit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
    * @ClassName: DateKit
    * @Description: TODO(日期转化工具类)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date 2017年8月30日 下午4:47:29
 */
public class DateKit {
	static String TIME = "yyyy-MM-dd HH:mm:ss";
	static	String OTHER_TIME="yyyy/MM/dd HH:mm:ss";
	static	String DATE ="yyyy-MM-dd";
	static	String OTHER_DATE="yyyy/MM/dd";
	static	String SAMPLE_TIME="yyyyMMddHHmmss";
	static	String SAMPLE_DATE="yyyyMMdd";
	static	String HOUR_TIME = "yyyyMMddHH";
	static	String SAMPLE_Month="yyyy-MM";
	 
	public static SimpleDateFormat getTimeFormat(){ 
		return new SimpleDateFormat(TIME);
	}
	public static SimpleDateFormat getOtherTimeFormat() {
		return new SimpleDateFormat(OTHER_TIME);
	}
	public static SimpleDateFormat getOtherDateFormat() {
		return new SimpleDateFormat(OTHER_DATE);
	}
	public static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat(DATE);
	}
	public static SimpleDateFormat getSampleTimeFormat() {
		return new SimpleDateFormat(SAMPLE_TIME);
	}
	public static SimpleDateFormat getSampleDateFormat() {
		return new SimpleDateFormat(SAMPLE_DATE);
	}
	public static SimpleDateFormat getHourDateFormat(){
		return new SimpleDateFormat(HOUR_TIME);
	}
	public static SimpleDateFormat getMonthDateFormat(){
		return new SimpleDateFormat(SAMPLE_Month);
	}
	
	public static String DateTime2Str(Date date,SimpleDateFormat format) {
		return format.format(date);
	}
	
	public static Date Str2DateTime(String date,SimpleDateFormat format) {
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
