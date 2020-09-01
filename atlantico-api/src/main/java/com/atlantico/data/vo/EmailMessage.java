package com.atlantico.data.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = EmailMessage.class)
public class EmailMessage implements Serializable {

	private static final long serialVersionUID = -4148712542411469251L;
	private List<String> emails;
	private String message;


	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isEmpty() {
		return CollectionUtils.isEmpty(this.emails);
	}
	
	
	
}
