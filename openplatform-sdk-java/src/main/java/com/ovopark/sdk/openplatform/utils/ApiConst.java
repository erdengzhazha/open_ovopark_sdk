package com.ovopark.sdk.openplatform.utils;
/**
    * @ClassName: ApiConst
    * @Description: TODO(网关常量)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date 2017年9月11日 下午4:19:40
 */
public interface ApiConst {
	//debug 模式下识别http header中dubbo.version参数,将请求路由到指定的dubbo服务上
    static final String               DEBUG_DUBBOVERSION      = "DUBBO-VERSION";
   //debug 模式下识别http header中dubbo.service.ip参数,将请求路由到指定的dubbo服务上
    static final String               DEBUG_DUBBOSERVICE_URL  = "DUBBO-SERVICE-URL";
    static final String               FORMAT_XML              = "xml";
    static final String               FORMAT_JSON             = "json";
    static final String               FORMAT_PLAINTEXT        = "plaintext";
    static final String               SERVER_ADDRESS          = "a:";
    static final String               THREADID                = "t:";
    static final String               SPLIT                   = "|";
    static final String               REQ_TAG                 = "s:";
	static final String               CONTENT_TYPE_FORM       = "application/x-www-form-urlencoded;charset=utf-8";
    static final String               CONTENT_TYPE_XML        = "application/xml;charset=utf-8";
    static final String               CONTENT_TYPE_JSON       = "application/json;charset=utf-8";
    static final String               CONTENT_TYPE_JAVASCRIPT = "application/javascript;charset=utf-8";
    static final String               CONTENT_TYPE_PLAINTEXT  = "text/plain";
    static final String               JSONARRAY_PREFIX        = "[";
    static final String               JSONARRAY_SURFIX        = "]";
    static final String               USER_AGENT              = "User-Agent";
    static final String               REFERER                 = "Referer";
    static final String               DEBUG_AGENT             = "coco.tester";
}
