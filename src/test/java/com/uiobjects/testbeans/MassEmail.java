package com.uiobjects.testbeans;

import com.uiobjects.uio.annotations.Combo;


public class MassEmail {

	
	@Combo(type=MailTemplate.class, forceSelection=true)
	private long mailTemplateId;
	
	public long getMailTemplateId() {
		return mailTemplateId;
	}
	
	public void setMailTemplateId(long mailTemplateId) {
		this.mailTemplateId = mailTemplateId;
	}

}
