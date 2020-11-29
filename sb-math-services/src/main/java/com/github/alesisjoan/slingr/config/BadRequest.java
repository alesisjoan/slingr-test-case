package com.github.alesisjoan.slingr.config;

public class BadRequest extends RuntimeException {

	public BadRequest(String message){
		super("Error 400: " + message);
	}
}
