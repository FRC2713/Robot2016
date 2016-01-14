package org.usfirst.frc.team2713.robot.input;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	private String name;
	private int port;
	
	private String attackName = "INSERT TECHNICAL NAME OF ATTACK3 HERE";
	private String xboxName = "INSERT TECHNICAL NAME OF XBOX REMOTE HERE";
	
	private AttackController attack;
	private XboxController xbox;
	
	
	public Joystick Controller(String targetControllerName){
		if (targetControllerName.equals(attackName)){
			return attack;
		} else {
			return xbox;
		}
		
	}
	
	public Joystick Controller(int targetControllerInt){
		if (targetControllerInt == RobotMap.AttackPort){
			return attack;
		} else {
			return xbox;
		}
	}

}
