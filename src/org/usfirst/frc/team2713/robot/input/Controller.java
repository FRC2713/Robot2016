package org.usfirst.frc.team2713.robot.input;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.exceptions.ControllerNotFoundException;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {	
	private String attackName = "Logitech Attack 3";
	private String xboxName = "Controller (Gamepad for Xbox 360)";
	
	public AttackController attack;
	public XBoxController xbox;
	
	
	public Joystick getController(String targetControllerName) throws ControllerNotFoundException {
		if (targetControllerName.equals(attackName)){
			return attack;
		} else if (targetControllerName.equals(xboxName)) {
			return xbox;
		} else {
			throw new ControllerNotFoundException("Controller "+targetControllerName+" not Found");
		}
	}
	
	public Joystick getController(int targetControllerInt) throws ControllerNotFoundException{
		if (targetControllerInt == RobotMap.AttackPort){
			return attack;
		} else if (targetControllerInt == RobotMap.XBoxPort) {
			return xbox;
		} else {
			throw new ControllerNotFoundException("Controller port "+targetControllerInt+" not Found");
		}
		
	}

}
