package org.usfirst.frc.team2713.robot.subsystems.exceptions;

public class ButtonNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6079805714329132730L;
	public ButtonNotFoundException() { super(); }
	public ButtonNotFoundException(String message) { super(message); }
	public ButtonNotFoundException(String message, Throwable cause) { super(message, cause); }
	public ButtonNotFoundException(Throwable cause) { super(cause); }

}
