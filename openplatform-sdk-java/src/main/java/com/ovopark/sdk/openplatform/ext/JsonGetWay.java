package com.ovopark.sdk.openplatform.ext;

import java.io.Serializable;

public class JsonGetWay extends GetWayHeader implements Serializable{
	private static final long serialVersionUID = 5622403846309372212L;

	private String data;

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	
	
}
