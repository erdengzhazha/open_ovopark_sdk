package com.ovopark.sdk.openplatform.utils;

import java.net.InetAddress;


public class GwUtils {
	public static String createCid(){
		// *md5hash(server ip address)，用来串联_cid,_cid=address|thread|time*
		String _cid = ApiConst.SERVER_ADDRESS + serverAddress()+ ApiConst.SPLIT + ApiConst.THREADID
				+ Thread.currentThread().getId() + ApiConst.SPLIT + ApiConst.REQ_TAG + System.currentTimeMillis();
		return _cid;
	}
	
	private static String serverAddress(){
		String serverAddress="";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			serverAddress= Md5Util.computeToHex(addr.getHostAddress().getBytes("UTF-8")).substring(0, 6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverAddress;
	}
}
