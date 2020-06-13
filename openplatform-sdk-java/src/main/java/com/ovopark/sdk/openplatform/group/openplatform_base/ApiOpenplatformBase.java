package com.ovopark.sdk.openplatform.group.openplatform_base;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ovopark.sdk.openplatform.client.BaseClient;
import com.ovopark.sdk.openplatform.group.gateway.GatewayConsts;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;
import com.ovopark.sdk.openplatform.http.OkClient;
import com.ovopark.sdk.openplatform.utils.OvoParkUtils;

/**
 * 开放平台人脸基础数据API
 * @author wei
 *
 */
public class ApiOpenplatformBase extends BaseClient{
	
	private static ApiOpenplatformBase apiOpenplatformBase;
	private SortedMap<String, Object> sort = new TreeMap<String, Object>();
	private OvoParkUtils ovoParkUtils = new OvoParkUtils();
	private ApiOpenplatformBase() {
		
	}
	public static ApiOpenplatformBase getApiOpenplatformBase() {
		if(apiOpenplatformBase == null) {
			apiOpenplatformBase = new ApiOpenplatformBase();
		}
		return apiOpenplatformBase;
	}
	/**
	 * 获取门店列表
	 * @param orgid 企业id
	 * @param version 默认版本为v1
	 * @return
	 */
	public String getStores(Integer orgid,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort,"orgid", orgid.toString());
		return publicUtil(version, OpenplatformBaseConsts.method_open_openplatform_base_getStores);
	}
	
	/**
	 * 获取用户列表
	 * @param orgid 企业id
	 * @param depId 门店id
	 * @param stime 注册时间（从XX时候开始），yyyy-mm-dd hh:mm:ss格式
	 * @param etime 注册时间（从XX时候结束），yyyy-mm-dd hh:mm:ss格式
	 * @param version 默认版本为 v1
	 * @return
	 */
	public String getUsers(Integer orgid,Integer depId,String stime,String etime,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort,"orgid", orgid);
		ovoParkUtils.setNoMustSort(sort,"depId", depId);
		ovoParkUtils.setNoMustSort(sort, "stime", stime);
		ovoParkUtils.setNoMustSort(sort, "etime", etime);
		return publicUtil(version, OpenplatformBaseConsts.method_open_openplatform_base_getUsers);
	}
	
	/**
	 * 获取人脸客流年龄分布
	 * @param orgid 企业id 
	 * @param depId 门店id
	 * @param stime 注册时间（从XX时候开始），yyyy-mm-dd hh:mm:ss格式
	 * @param etime 注册时间（从XX时候结束），yyyy-mm-dd hh:mm:ss格式
	 * @param version 默认版本v1
	 * @return
	 */
	public String getAgeData(Integer orgid,Integer depId,String stime,String etime,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort,"orgid", orgid);
		ovoParkUtils.setNoMustSort(sort,"depId", depId);
		ovoParkUtils.setNoMustSort(sort, "stime", stime);
		ovoParkUtils.setNoMustSort(sort, "etime", etime);
		return publicUtil(version, OpenplatformBaseConsts.method_open_openplatform_base_getAgeData);
	}
	
	/**
	 * 获取人脸客流性别分布
	 * @param orgid 企业id
	 * @param depId 门店id
	 * @param stime 注册时间（从XX时候开始），yyyy-mm-dd hh:mm:ss格式
	 * @param etime 注册时间（从XX时候结束），yyyy-mm-dd hh:mm:ss格式
	 * @param version 默认版本 v1
	 * @return
	 */
	public String getGenderData(Integer orgid,Integer depId,String stime,String etime,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort,"orgid", orgid);
		ovoParkUtils.setNoMustSort(sort,"depId", depId);
		ovoParkUtils.setNoMustSort(sort, "stime", stime);
		ovoParkUtils.setNoMustSort(sort, "etime", etime);
		return publicUtil(version, OpenplatformBaseConsts.method_open_openplatform_base_getGenderData);
	}
	
	/**
	 * 获取设备最新抓拍的照片
	 * @param orgid 开放平台企业id
	 * @param device_mac 设备mac
	 * @param size 返回多少条数据	
	 * @param version 默认版本v1
	 * @return
	 */
	public String getDeviceLatestPhotos(Integer orgid,Integer device_mac,Integer size,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort,"orgid", orgid);
		ovoParkUtils.setNoMustSort(sort,"device_mac", device_mac);
		ovoParkUtils.setNoMustSort(sort, "size", size);
		return publicUtil(version, OpenplatformBaseConsts.method_open_openplatform_base_getDeviceLatestPhotos);
	}
	
	/**
	 * 公共部分的工具方法
	 * 目的：设置请求参数，并发出http请求，返回值
	 * @param version
	 * @param method
	 * @return
	 */
	public String publicUtil(String version,String method) {
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,method);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	
	
}
