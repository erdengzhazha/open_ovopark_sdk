package com.ovopark.sdk.openplatform.group.gateway;

public class GatewayConsts {
	
	/** 根据组织机构代码获取开发者账号详细信息 */
	static final String  method_open_gateway_authentication ="open.gateway.authentication";
	/** 获取业务平台组织机构列表*/
	static final String  method_open_gateway_getBusinessOrg ="open.gateway.getBusinessOrg";
	/** 获取企业下所有角色*/
	static final String  method_open_shopweb_role_getRoleListByGroup ="open.shopweb.role.getRoleListByGroup";
	/** 获取菜单权限*/
	static final String  method_open_shopweb_user_getMenuPrivileges ="open.shopweb.user.getMenuPrivileges";
	/** 同步企业代码*/
	static final String  method_open_gateway_syscEnterpriseCode ="open.gateway.syscEnterpriseCode";
	/** 获取对应角色列表对应的人员列表（请求头中必须有token参数，参数名为authenticator，该值从用户API中的登录接口获得）*/
	static final String  method_open_shopweb_role_getRoleAndUsersAsConfig ="open.shopweb.role.getRoleAndUsersAsConfig";
	
}
