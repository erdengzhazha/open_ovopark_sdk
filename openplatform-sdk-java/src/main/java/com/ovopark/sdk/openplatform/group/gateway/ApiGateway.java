package com.ovopark.sdk.openplatform.group.gateway;

import java.io.IOException;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ovopark.sdk.openplatform.client.BaseClient;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;
import com.ovopark.sdk.openplatform.http.OkClient;
import com.ovopark.sdk.openplatform.http.OvoParkHttpClient;
import com.ovopark.sdk.openplatform.utils.OvoParkUtils;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * 权限API
 * @author chenwei
 *
 */
public class ApiGateway extends BaseClient{

	private static ApiGateway apiGateway;
	private SortedMap<String, Object> sort = new TreeMap<String, Object>();
	private OvoParkUtils ovoParkUtils = new OvoParkUtils();
	private ApiGateway() {
//		super(applicationKey, applicationSecret); //设置 app key 和密钥
//		GwInitRequestHandler reqHandler = GwInitRequestHandler.getGwInitRequestHandler();
//		reqHandler.setAuthenticator(authenticator);
	}
	

	public static ApiGateway getApiGateway() {
		if(apiGateway == null) {
			apiGateway = new ApiGateway();
		}
		return apiGateway;
	}
	
	/**
	 * 作用：根据组织机构代码获取开发者账号详细信息
	 * @param * orgid 开放平台企业id
	 * @param version  默认v1版本 ，此接口推荐v2
	 * @return
	 */
	public String authentication(Integer orgid,String version) {
				//首次 0毫秒
				sort.clear(); //每次调用接口先清空
				ovoParkUtils.setMustSort(sort, "orgid",orgid.toString());
				//初始化请求头  首次4毫秒
				GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
				if(version!=null&&version=="v2") {
					reqHandler.setVersion(version);
				}else {
					reqHandler.setVersion("v1");
				}
				setCommonParameters(reqHandler);
				//设置报文头  首次操作0毫秒
				reqHandler.setHead(sort,GatewayConsts.method_open_gateway_authentication);
				//设置 用户token为空
				//reqHandler.setAuthenticator("");
				//获取okhttp请求客户端对象  首次获取638毫秒
				OkClient okClient = OkClient.getOkHttpClient(); 
				String resContent = null;
				try {
					//Date startTime = new Date();
					resContent = okClient.doPost(reqHandler);  //首次耗时 157毫秒 ，后面平均30毫秒
					//Date endTime = new Date();
					//System.out.println("doPost请求耗时："+(endTime.getTime()-startTime.getTime())+"毫秒");
					//System.out.println("resContent的信息："+resContent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resContent;
	}
	
	/**
	 * 作用：获取业务平台组织机构列表
	 * @param * orgid 开放平台企业id
	 * @param pageNumber 当前第几页（默认第1页）
	 * @param pageSize  每页多少条（默认20条）
	 * @param version 默认v1版本
	 * @return
	 */
	public String getBusinessOrg(Integer orgid,String pageNumber,String pageSize,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort, "orgid",orgid.toString());
		ovoParkUtils.setNoMustSort(sort, "pageNumber",pageNumber);
		ovoParkUtils.setNoMustSort(sort, "pageSize",pageSize);
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,GatewayConsts.method_open_gateway_getBusinessOrg);
		//reqHandler.setAuthenticator("");
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler);  //首次耗时 157毫秒 ，后面平均30毫秒
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	/**
	 * 作用：获取企业下的所有角色
	 * @param * authenticator  用户的token
	 * @param version 默认版本为v1
	 * @return
	 */
	public String getRoleListByGroup(String version) {
		sort.clear();
		//ovoParkUtils.setMustSort(sort, key,value);
		//ovoParkUtils.setNoMustSort(sort, "value",value);
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		//reqHandler.setParameter("_sig", value);
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,GatewayConsts.method_open_shopweb_role_getRoleListByGroup);
		//设置请求头 用户的token
		//ovoParkUtils.isSetAuthenticator(reqHandler, authenticator);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler);  //首次耗时 157毫秒 ，后面平均30毫秒
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	/**
	 * 作用：获取菜单权限
	 * @param * authenticator  用户的token
	 * @param version  默认版本v1 
	 * @return
	 */
	public String getMenuPrivileges(String version) {
		sort.clear();
		//ovoParkUtils.setMustSort(sort, key,value);
		//ovoParkUtils.setNoMustSort(sort, "value",value);
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		//reqHandler.setParameter("_sig", value);
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,GatewayConsts.method_open_shopweb_user_getMenuPrivileges);
		//设置请求头 用户的token
		//ovoParkUtils.isSetAuthenticator(reqHandler, authenticator);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler);  //首次耗时 157毫秒 ，后面平均30毫秒
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	
	/**
	 *  作用：同步企业代码
	 * @param * orgid  开放平台企业id
	 * @param * enterpriseId 业务平台企业id
	 * @param * enterpriseCode  业务编码
	 * @param version 默认版本v1
	 * @return
	 */
	public String getSyscEnterpriseCode(Integer orgid,Integer enterpriseId,String enterpriseCode,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort, "orgId",orgid.toString());
		ovoParkUtils.setNoMustSort(sort, "enterpriseId",enterpriseId.toString());
		ovoParkUtils.setNoMustSort(sort, "enterpriseCode",enterpriseCode);
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,GatewayConsts.method_open_gateway_syscEnterpriseCode);
		//reqHandler.setAuthenticator("");
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler);  //首次耗时 157毫秒 ，后面平均30毫秒
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	/**
	 * @param * authenticator 用户token
	 * @param * depId 门店id
	 * @param version 默认版本v1
	 * @return
	 */
	public String getRoleAndUsersAsConfig(Integer deptId,String version) {
	    sort.clear();
	    ovoParkUtils.setMustSort(sort, "deptId",deptId.toString());
	    GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
	    if(version!=null&&version=="v2") {
	        reqHandler.setVersion(version);
	    }else {
	        reqHandler.setVersion("v1");
	    }
	    setCommonParameters(reqHandler);
	    reqHandler.setHead(sort,GatewayConsts.method_open_shopweb_role_getRoleAndUsersAsConfig);
	    //ovoParkUtils.isSetAuthenticator(reqHandler, authenticator);
	    OkClient okClient = OkClient.getOkHttpClient(); 
	    String resContent = null;
	    try {
	        resContent = okClient.doPost(reqHandler);  //首次耗时 157毫秒 ，后面平均30毫秒
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return resContent;
	}
}
