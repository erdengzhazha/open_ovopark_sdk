package com.ovopark.sdk.openplatform.ext;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.ovopark.sdk.openplatform.annotation.IgnoreSign;
import com.ovopark.sdk.openplatform.kit.DateKit;

/**
    * @ClassName: GetWayHeader
    * @Description: TODO(报文头)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date  2017年12月4日 下午2:10:51 
    *
 */
public class GetWayHeader{
	@JSONField(name="_aid")
	private String _aid;
	
	@JSONField(name="_akey")
	private String _akey;
	
	@JSONField(name="_mt")
	private String _mt;
	
	@JSONField(name="_sm")
	private String _sm;
	
	@JSONField(name="_requestMode")
	private String _requestMode;
	
	@JSONField(name="_version")
	private String _version;
	
	@JSONField(name="_timestamp")
	private String _timestamp;
	
	@IgnoreSign
	@JSONField(name="_sig")
	private String _sig;
	
	@JSONField(name="_format")
	private String _format;
	
	public GetWayHeader() {
		super();
		this._aid = "S107";
		this._sm="md5";
		this._requestMode = "post";
		this._version = "v1";
		
		this._timestamp = DateKit.DateTime2Str(new Date(), DateKit.getSampleTimeFormat());
		this._format = "json";
	}
	public String get_aid() {
		return _aid;
	}
	public void set_aid(String _aid) {
		this._aid = _aid;
	}
	public String get_akey() {
		return _akey;
	}
	public void set_akey(String _akey) {
		this._akey = _akey;
	}
	public String get_mt() {
		return _mt;
	}
	public void set_mt(String _mt) {
		this._mt = _mt;
	}
	public String get_sm() {
		return _sm;
	}
	public void set_sm(String _sm) {
		this._sm = _sm;
	}
	public String get_requestMode() {
		return _requestMode;
	}
	public void set_requestMode(String _requestMode) {
		this._requestMode = _requestMode;
	}
	public String get_version() {
		return _version;
	}
	public void set_version(String _version) {
		this._version = _version;
	}
	public String get_timestamp() {
		return _timestamp;
	}
	public void set_timestamp(String _timestamp) {
		this._timestamp = _timestamp;
	}
	public String get_sig() {
		return _sig;
	}
	public void set_sig(String _sig) {
		this._sig = _sig;
	}
	public String get_format() {
		return _format;
	}
	public void set_format(String _format) {
		this._format = _format;
	}
	
	
}
