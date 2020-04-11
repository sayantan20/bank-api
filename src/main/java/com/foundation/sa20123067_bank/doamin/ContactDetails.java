package com.foundation.sa20123067_bank.doamin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContactDetails {

	
	private String emailId;
	
	private String homePhone;

	private String workPhone;
	
	public ContactDetails(String emailId, String homePhone, String workPhone) {
		super();
		
		this.emailId=emailId;
		this.homePhone=homePhone;
		this.workPhone=workPhone;
	}
}
