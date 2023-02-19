package it.epicode.be.exception;

@SuppressWarnings("serial")
public class NotDuplicableEx extends Exception{
	public NotDuplicableEx(String message) {
		super(String.format(message));
	}
}
