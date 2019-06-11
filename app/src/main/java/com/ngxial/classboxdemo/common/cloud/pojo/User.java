package com.ngxial.classboxdemo.common.cloud.pojo;

public class User {
	private String _id;

	private String accessToken;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "ClassPojo [_id = " + _id + ", accessToken = " + accessToken + "]";
	}
}