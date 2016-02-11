package org.usfirst.frc.team2713.robot.exceptions;

public class ControllerNotFound extends RuntimeException {

	private static final long serialVersionUID = 8772415862L; //LUNA
	
	public ControllerNotFound() { super(); }
	public ControllerNotFound(String message) { super(message); }
	public ControllerNotFound(String message, Throwable cause) { super(message, cause); }
	public ControllerNotFound(Throwable cause) { super(cause); }

}
