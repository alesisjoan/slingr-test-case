package com.github.alesisjoan.slingr.config;

public class MathException extends RuntimeException {

	public MathException(String message){
		super("Error 5001: " + message);
	}
}
