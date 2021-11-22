package com.librarymanagement.dataaccess.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse {

	private String message;
	private int  status;
	private Object response;
	
	public MessageResponse(String msg,int status) {
		this.message = msg;
		this.status=status;
	}
	public MessageResponse(String msg,int status,Object response) {
		this.message = msg;
		this.status=status;
		this.response=response;
	}
}
