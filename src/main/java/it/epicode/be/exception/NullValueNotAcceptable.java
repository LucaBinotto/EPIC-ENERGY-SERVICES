package it.epicode.be.exception;

@SuppressWarnings("serial")
public class NullValueNotAcceptable extends Exception{
	public NullValueNotAcceptable(String message,Class<?> clazz) {
		super(String.format(message, clazz.getSimpleName()));
	}
}
