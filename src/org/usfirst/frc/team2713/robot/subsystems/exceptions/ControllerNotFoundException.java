package org.usfirst.frc.team2713.robot.subsystems.exceptions;

public class ControllerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 763078447433828809L;
	public ControllerNotFoundException() { super(); }
	public ControllerNotFoundException(String message) { super(message); }
	public ControllerNotFoundException(String message, Throwable cause) { super(message, cause); }
	public ControllerNotFoundException(Throwable cause) { super(cause); }

}
