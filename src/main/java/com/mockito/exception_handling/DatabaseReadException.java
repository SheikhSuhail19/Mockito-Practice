package com.mockito.exception_handling;

public class DatabaseReadException extends RuntimeException {
	public DatabaseReadException(String s) {
		super(s);
	}
}
