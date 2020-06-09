package com.ovopark.sdk.openplatform.group.face;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ovopark.sdk.openplatform.client.BaseClient;
import com.ovopark.sdk.openplatform.group.gateway.ApiGateway;
import com.ovopark.sdk.openplatform.group.gateway.GatewayConsts;
import com.ovopark.sdk.openplatform.handle.GwInitRequestHandler;
import com.ovopark.sdk.openplatform.http.OkClient;
import com.ovopark.sdk.openplatform.utils.OvoParkUtils;



public class ApiFace extends BaseClient{

	private  static ApiFace apiFace;
	private SortedMap<String, Object> sort = new TreeMap<String, Object>();
	private OvoParkUtils ovoParkUtils = new OvoParkUtils();
	private ApiFace() {
		
	}
	public static ApiFace getApiFace() {
		if(apiFace==null) {
			apiFace = new ApiFace();
		}
		return apiFace;
	}
	
	
	/**
	 * 作用：注册人脸
	 * @param dataUser_Json 用户数据json格式
	 * @param version  默认v1版本 ，此接口推荐v2
	 * @return
	 */
	public String addUser(String dataUser_Json,String version) {
				sort.clear();
				ovoParkUtils.setMustSort(sort, "DataUser",dataUser_Json);
				GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
				if(version!=null&&version=="v2") {
					reqHandler.setVersion(version);
				}else {
					reqHandler.setVersion("v1");
				}
				setCommonParameters(reqHandler);
				reqHandler.setHead(sort,FaceConsts.method_open_face_addUser);
				OkClient okClient = OkClient.getOkHttpClient(); 
				String resContent = null;
				try {
					resContent = okClient.doPost(reqHandler); 
				} catch (IOException e) {
					e.printStackTrace();
				}
				return resContent;
	}
	
	/**
	 * 抓拍回调
	 * @param thirdpicurl 开发者提供的公网可以访问的url地址
	 * @param wdzpicurl 万店掌摄像头抓拍后返回的图片url地址
	 * @param - username 用户姓名
	 * @param - userid 开发者用户id
	 * @param - memberType(1:顾客,2:会员,3:店员)
	 * @param gender 性别(false:男,true：女)
	 * @param age 年龄
	 * @param glass Sunglasses:墨镜, None:不戴眼镜, Normal:普通眼镜
	 * @param emotion 	anger:愤怒,disgust:厌恶,fear:恐惧,happiness:高兴,neutral:平静,sadness:伤心,surprise:惊讶
	 * @param ethnicity Asian:亚洲人,White:白人,Black:黑人
	 * @param skinstatus health:健康, stain:色斑,acne:青春痘,dark_circle:黑眼圈
	 * @param arriveTime 到店时间(yyyyMMddHHmmss格式)
	 * @param groupid 人脸分组id
	 * @param orgid 企业id
	 * @param deviceId 设备入网id
	 * @param deviceMac 设备mac
	 * @param deviceName 设备名称
	 * @param faceId 人脸id
	 * @param version 版本默认：v1
	 * @return
	 */
	public String queryUserCallBack(String thirdpicurl,String wdzpicurl,String username,
			String userid,String memberType ,boolean gender,
			Integer age,String glass,String emotion,String ethnicity,
			String skinstatus,String arriveTime,Integer groupid,Integer orgid,
			String deviceId ,String deviceMac,String deviceName,String faceId,
			String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort, "thirdpicurl",thirdpicurl);
		ovoParkUtils.setMustSort(sort,"wdzpicurl",wdzpicurl);
		ovoParkUtils.setNoMustSort(sort,"username",username);
		ovoParkUtils.setNoMustSort(sort,"userid",userid);
		ovoParkUtils.setNoMustSort(sort,"memberType",memberType);
		String gend = gender?"true":"false";
		ovoParkUtils.setMustSort(sort,"gender",gend);
		try {
			ovoParkUtils.setMustSort(sort,"age",age.toString());
			ovoParkUtils.setMustSort(sort,"groupid",groupid.toString());
			ovoParkUtils.setMustSort(sort,"orgid",orgid.toString());
		} catch (Exception e1) {
			System.out.println("注意：int类型的参数不能传递null");
		}
		ovoParkUtils.setMustSort(sort,"glass",glass);
		ovoParkUtils.setMustSort(sort,"emotion",emotion);
		ovoParkUtils.setMustSort(sort,"ethnicity",ethnicity);
		ovoParkUtils.setMustSort(sort,"skinstatus",skinstatus);
		ovoParkUtils.setMustSort(sort,"arriveTime",arriveTime);
		
		ovoParkUtils.setMustSort(sort,"deviceId",deviceId);
		ovoParkUtils.setMustSort(sort,"deviceMac",deviceMac);
		ovoParkUtils.setMustSort(sort,"deviceName",deviceName);
		ovoParkUtils.setMustSort(sort,"faceId",faceId);
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,FaceConsts.method_open_face_queryUserCallBack);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	/**
	 * 添加分组
	 * @param groupname 分组名称
	 * @param orgid 企业id
	 * @param version 默认版本 v1
	 * @return
	 */
	public String addGroup(String groupname,Integer orgid,String version) {
		sort.clear();
		ovoParkUtils.setMustSort(sort, "groupname",groupname);
		try {
			ovoParkUtils.setMustSort(sort, "orgid",orgid.toString());
		} catch (Exception e1) {
			System.out.println("注意：int类型的参数不能传递null");
		}
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,FaceConsts.method_open_face_addGroup);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	/**
	 * 查询分组
	 * @param orgid 企业id
	 * @param version 默认版本 v1
	 * @return
	 */
	public String queryGroup(Integer orgid,String version) {
		sort.clear();
		try {
			ovoParkUtils.setMustSort(sort, "orgid",orgid.toString());
		} catch (Exception e1) {
			System.out.println("注意：int类型的参数不能传递null");
		}
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,FaceConsts.method_open_face_queryGroup);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	/**
	 * 查询人脸设备
	 * @param orgid 企业id
	 * @param groupid 人脸分组id（开放平台门店id）
	 * @param deviceMac 人脸设备mac
	 * @param version 默认版本v1
	 * @return
	 */
	public String queryDevice(Integer orgid,Integer groupid,String deviceMac,String version) {
		sort.clear();
		try {
			ovoParkUtils.setMustSort(sort, "orgid",orgid.toString());
			ovoParkUtils.setMustSort(sort, "groupid",groupid.toString());
		} catch (Exception e1) {
			System.out.println("注意：int类型的参数不能传递null");
		}
		ovoParkUtils.setMustSort(sort, "deviceMac", deviceMac);
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,FaceConsts.method_open_face_queryDevice);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	/**
	 * 绑定人脸设备
	 * @param orgid 企业id
	 * @param groupid 分组id
	 * @param deviceId 设备入网流水id
	 * @param version 默认版本 v1
	 * @return
	 */
	public String bindingDevice(Integer orgid,Integer groupid,Integer deviceId,String version) {
		sort.clear();
		try {
			ovoParkUtils.setMustSort(sort, "orgid",orgid.toString());
			ovoParkUtils.setMustSort(sort, "groupid",groupid.toString());
			ovoParkUtils.setMustSort(sort, "deviceId", deviceId.toString());
		} catch (Exception e1) {
			System.out.println("注意：int类型的参数不能传递null");
		}
		
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,FaceConsts.method_open_face_bindingDevice);
		OkClient okClient = OkClient.getOkHttpClient(); 
		String resContent = null;
		try {
			resContent = okClient.doPost(reqHandler); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resContent;
	}
	
	/**
	 * 更新人脸
	 * @param orgid 企业id
	 * @param departno 人脸分组id(即groupid)
	 * @param userid 开发者提供的会员id(第三方系统自行生成)，唯一
	 * @param thirdpicurl 开发者提供的公网可以访问的图片url地址
	 * @param -username 用户姓名
	 * @param -memberType (1:顾客,2:会员,3:店员)
	 * @param -mobilephone 手机号码
	 * @param -gender 性别,主要做校准使用(0:男,1：女)
	 * @param checkrepeat 验证手机号是否重复, 需要验证为1，不需要验证为0)
	 * @param version
	 * @return
	 */
	public String updateUser(Integer orgid,Integer departno,
			String userid,String thirdpicurl,String username,
			String memberType,String mobilephone,String gender,
			Integer checkrepeat,String version) {
		sort.clear();
		try {
			ovoParkUtils.setMustSort(sort, "orgid",orgid.toString());
			ovoParkUtils.setMustSort(sort, "departno",departno.toString());
			ovoParkUtils.setMustSort(sort, "checkrepeat", checkrepeat.toString());
		} catch (Exception e1) {
			System.out.println("注意：int类型的参数不能传递null");
		}
		ovoParkUtils.setMustSort(sort, "userid", userid);
		ovoParkUtils.setMustSort(sort, "thirdpicurl", thirdpicurl);
		ovoParkUtils.setNoMustSort(sort, "username", username);
		ovoParkUtils.setNoMustSort(sort, "memberType", memberType);
		ovoParkUtils.setNoMustSort(sort, "mobilephone", mobilephone);
		if(gender.equals("0")||gender.equals("1")) {
			ovoParkUtils.setNoMustSort(sort, "gender", gender);
		}else {
			System.out.println("注意：gender参数值不合法！");
		}
		
		GwInitRequestHandler reqHandler=GwInitRequestHandler.getGwInitRequestHandler(); 
		if(version!=null&&version=="v2") {
			reqHandler.setVersion(version);
		}else {
			reqHandler.setVersion("v1");
		}
		setCommonParameters(reqHandler);
		reqHandler.setHead(sort,FaceConsts.method_open_face_updateUser);
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
