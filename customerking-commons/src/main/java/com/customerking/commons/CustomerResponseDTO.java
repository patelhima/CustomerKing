package com.customerking.commons;

import java.util.List;

public class CustomerResponseDTO {
	
	private List<String> messages;
	
	private Object data;
	
	private Boolean hasAnyError = false;

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getHasAnyError() {
		return hasAnyError;
	}

	public void setHasAnyError(Boolean hasAnyError) {
		this.hasAnyError = hasAnyError;
	}


}
