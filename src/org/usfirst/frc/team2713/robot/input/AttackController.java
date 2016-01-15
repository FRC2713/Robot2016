package org.usfirst.frc.team2713.robot.input;

import org.usfirst.frc.team2713.robot.subsystems.exceptions.ButtonNotFoundException;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class AttackController extends Joystick {

	public AttackController(int port) {
		super(port);
	}
	
	public String getName(){
		return getName();
	}
	
	public int getButton(String b) throws ButtonNotFoundException { //b should be name of button
		if (b.equals("TEST_BUTTON")){
			return 3;
		} else {
			throw new ButtonNotFoundException("Button: " + b + " wasn't found for this controller");
		}
	}
	
	

}
