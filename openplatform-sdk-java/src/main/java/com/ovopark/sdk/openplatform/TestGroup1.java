package com.ovopark.sdk.openplatform;


import java.util.Date;

import com.ovopark.sdk.openplatform.client.InitClient;
import com.ovopark.sdk.openplatform.group.gateway.ApiGateway;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;
import com.ovopark.sdk.openplatform.handle.RequestHandler;

public class TestGroup1 extends Thread{
	
	
	public static void main(String[] args) {
		
		//Thread thread = new test2();
		//Date nTime = new Date();
		//ApiGateway apiGateway = ApiGateway.getApiGateway("S107-00000178", "7cf181af9bb4f4e0f07121e681b4ce9e");
		String authenticator ="7E1AFAAA8D5E4C521C7ADD319F12BFE0D392E057CF5C659331E433EE4FF4CF5AD600EDE2CA06B280AD2B5E2705387C46";
		String applicationkey ="S107-00000183";
		String applicationSecret ="fa763b15729b35320270d326ee170bb8";
		//String 
		InitClient.InitClient(applicationkey,applicationSecret,authenticator);
		ApiGateway apiGateway = ApiGateway.getApiGateway();
		//根据组织机构代码获取开发者帐号详细信息
		System.out.println(apiGateway.authentication(262,"v2"));
		System.out.println("\n");
		//获取业务平台组织机构列表
		System.out.println(apiGateway.getBusinessOrg(262, null, null, null));
		System.out.println("\n");
		//获取企业下所有角色
		System.out.println(apiGateway.getRoleListByGroup(null));
		System.out.println("\n");
		//获取菜单权限
		System.out.println(apiGateway.getMenuPrivileges(null));
		System.out.println("\n");
		//同步企业代码
		System.out.println(apiGateway.getSyscEnterpriseCode(262,262,"262",null));
		System.out.println("\n");
		//获取对应角色列表对应的人员列表
		System.out.println(apiGateway.getRoleAndUsersAsConfig(7192, null));
		
	}
}
