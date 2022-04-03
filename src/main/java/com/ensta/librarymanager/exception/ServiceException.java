package com.ensta.librarymanager.exception;

public class ServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServiceException(){super("Exception : ServiceException");}
	public ServiceException(String s) {super(s);}

}
