package it.epicode.be.exception;

@SuppressWarnings("serial")
public class EntityNotFoundException extends Exception{
	public EntityNotFoundException(String message,Class<?> clazz) {
		super(String.format(message, clazz.getSimpleName()));
	}
}
