package com.uiobjects.testbeans;

import java.util.Date;



public class Sms {

	
	private long id;
	private String message;
	private String sender;
	private String senderName;
	private String receiver;
	
	private Date timestamp = new Date();
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderName() {
		return senderName;
	}
	
	
}
