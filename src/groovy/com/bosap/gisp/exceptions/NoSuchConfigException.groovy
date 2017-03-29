package com.bosap.gisp.exceptions;

class NoSuchConfigException extends Exception {
	public NoSuchConfigException(String msg){
		super(msg)
	}
	
	public NoSuchConfigException(String msg, Throwable t){
		super(msg, t)
	}
}