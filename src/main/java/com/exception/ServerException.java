package com.exception;

public class ServerException extends RuntimeException {
	
	private static final long serialVersionUID=1L;
	public ServerException(Exception e) {
		super(e);
	}

}
