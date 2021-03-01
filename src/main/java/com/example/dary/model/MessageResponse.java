package com.example.dary.model;

import java.io.Serializable;

public class MessageResponse implements Serializable{
	
	private String msg;

	
	
	public MessageResponse(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
	

}
