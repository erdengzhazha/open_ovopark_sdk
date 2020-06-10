package com.ovopark.sdk.openplatform.group.face;

public class FaceConsts {
	/** 注册人脸 */
	static final String method_open_face_addUser ="open.face.addUser";
	/** 抓拍回调 */
	static final String method_open_face_queryUserCallBack ="open.face.queryUserCallBack";
	/** 添加分组 */
	static final String method_open_face_addGroup ="open.face.addGroup";
	/** 查询分组 */
	static final String method_open_face_queryGroup ="open.face.queryGroup";
	/** 查询人脸设备 */
	static final String method_open_face_queryDevice ="open.face.queryDevice";
	/** 绑定人脸设备 */
	static final String method_open_face_bindingDevice ="open.face.bindingDevice";
	/** 更新人脸 */
	static final String method_open_face_updateUser ="open.face.updateUser";
	/** 删除用户所有人脸集合（包含历史抓拍记录） */
	static final String method_open_face_deleteUser ="open.face.deleteUser";
	/** 给某个用户添加一张人脸照片 */
	static final String method_open_face_addFaceUrl ="open.face.addFaceUrl";
	/** 根据三方用户编号及人脸编号更新人脸照片 */
	static final String method_open_face_updateFaceUrl ="open.face.updateFaceUrl";
	/** 根据三方用户编号和人脸照片编号删除指定人脸照片 */
	static final String method_open_face_deleteFaceUrl ="open.face.deleteFaceUrl";
	/** 根据三方会员编号获取所有的人脸照片信息 */
	static final String method_open_face_getFaceUrls ="open.face.getFaceUrls";
	/** 查询设备多分组场景配置 */
	static final String method_open_face_getDeviceGroupsConfig ="open.face.getDeviceGroupsConfig";
	/** 添加设备分组配置 */
	static final String method_open_face_addDeviceGroup ="open.face.addDeviceGroup";
	/** 更新设备分组配置 */
	static final String method_open_face_updateDeviceGroup ="open.face.updateDeviceGroup";
	/** 删除设备分组配置 */
	static final String method_open_face_deleteDeviceGroup ="open.face.deleteDeviceGroup";
	/** 根据照片搜索指定分组内用户 */
	static final String method_open_face_serchGroupUser ="open.face.serchGroupUser";
	/** 人脸检测 */
	static final String method_open_face_detect ="open.face.detect";
	
}
