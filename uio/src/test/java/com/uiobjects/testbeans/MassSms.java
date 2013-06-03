package com.uiobjects.testbeans;

import com.uiobjects.uio.annotations.Combo;


public class MassSms {
	
	@Combo(type=SmsTemplate.class, forceSelection=true)
	private long smsTemplateId;
	
	public long getSmsTemplateId() {
		return smsTemplateId;
	}
	
	public void setSmsTemplateId(long smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}
	
	
	

}
